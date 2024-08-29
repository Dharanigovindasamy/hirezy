package com.ideas2it.hirezy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *     This class used for OTP generate and verify within the exceed time
 *   and send to mail and verify the otp status
 * </p>
 *
 * @author dharani.govindhasamy
 * @version 1
 */
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

    /**
     * <p>
     *     Generating otp and valid with specific time interval along with OTP code.
     *    If time limit is exceed, try again otherwise send otp to mail
     * </p>
     * @param email - sending mail
     * @return String - OTP status to the mail
     */
    public String generateOTP(String email) {
        if (isRateLimited(email)) {
            return "Rate limit exceeded. Please try again later.";
        }

        String otpCode = generateRandomOTP(6);
        String otpKey = OTP_PREFIX + email;
        redisTemplate.opsForValue().set(otpKey, otpCode, Duration.ofMinutes(5));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("OTP for Hirezy");
        message.setText("Your OTP is: " + otpCode);
        javaMailSender.send(message);

        return "OTP generated and sent to " + email;
    }

    /**
     * <p>
     *     Verifying OTP status
     * </p>
     * @param email - email id of the user
     * @param otpCode - otp sent from mail
     * @return boolean - if verifies return true. otherwise, return false
     */
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

    /**
     * Checks if the given email has exceeded the rate limit for OTP requests.
     *
     * @param email the email address to check
     * @return true if the rate limit is exceeded, false otherwise
     */
    private boolean isRateLimited(String email) {
        String rateLimitKey = RATE_LIMIT_PREFIX + email;
        Long currentCount = redisTemplate.opsForValue().increment(rateLimitKey);
        if (currentCount == 1) {
            redisTemplate.expire(rateLimitKey, RATE_LIMIT_PERIOD_SECONDS, TimeUnit.SECONDS);
        }
        return currentCount > RATE_LIMIT;
    }

    /**
     * <p>
     *     Generating OTP with random numbers of 6 digit length
     * </p>
     * @param length - size of the OTP
     * @return String - OTP generated
     */
    private String generateRandomOTP(int length) {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    /**
     * <p>
     *     Check the account is verified or not by entering OTP
     * </p>
     * @param email - User mail id
     * @return true if account is verified, otherwise false
     */
    public boolean isAccountVerified(String email) {
        String verifiedKey = "verified:" + email;
        return Boolean.TRUE.equals(redisTemplate.hasKey(verifiedKey));
    }
}