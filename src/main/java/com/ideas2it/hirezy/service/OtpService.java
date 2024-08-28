package com.ideas2it.hirezy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String OTP_PREFIX = "otp:";
    private static final String RATE_LIMIT_PREFIX = "rate_limit:";
    private static final int RATE_LIMIT = 5;
    private static final long RATE_LIMIT_PERIOD_SECONDS = 300;

    public String generateOTP(String email) {
        // Check rate limiting
        if (isRateLimited(email)) {
            return "Rate limit exceeded. Please try again later.";
        }

        // Generate OTP
        String otpCode = generateRandomOTP(6);
        String otpKey = OTP_PREFIX + email;
        redisTemplate.opsForValue().set(otpKey, otpCode, Duration.ofMinutes(5));

        // Send OTP via email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("OTP for Hirezy");
        message.setText("Your OTP is: " + otpCode);
        javaMailSender.send(message);

        return "OTP generated and sent to " + email;
    }

    public boolean verifyOTP(String email, String otpCode) {
        String otpKey = OTP_PREFIX + email;
        String storedOTP = redisTemplate.opsForValue().get(otpKey);
        if (storedOTP != null && storedOTP.equals(otpCode)) {
            redisTemplate.delete(otpKey);
            redisTemplate.opsForValue().set("verified:" + email, "true");
            return true;
        }
        return false;
    }

    private boolean isRateLimited(String email) {
        String rateLimitKey = RATE_LIMIT_PREFIX + email;
        Long currentCount = redisTemplate.opsForValue().increment(rateLimitKey);
        if (currentCount == 1) {
            redisTemplate.expire(rateLimitKey, RATE_LIMIT_PERIOD_SECONDS, TimeUnit.SECONDS);
        }
        return currentCount > RATE_LIMIT;
    }

    private String generateRandomOTP(int length) {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
    public boolean isAccountVerified(String email) {
        String verifiedKey = "verified:" + email;
        return Boolean.TRUE.equals(redisTemplate.hasKey(verifiedKey));
    }

}