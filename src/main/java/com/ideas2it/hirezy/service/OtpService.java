package com.ideas2it.hirezy.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
/**
 * This is the service class that is used
 * for generation of the otp
 *verification of the otp and account takes place
 */
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
     *
     * This method is used to check if the number of attempts has exceeded
     *and then generate the otp
     * @param email
     * @return
     * @throws MessagingException
     */
    public String generateOTP(String email) throws MessagingException {
        if (isRateLimited(email)) {
            return "Rate limit exceeded. Please try again later.";
        }

        String otpCode = generateRandomOTP(6);
        String otpKey = OTP_PREFIX + email;
        redisTemplate.opsForValue().set(otpKey, otpCode, Duration.ofMinutes(5));

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true); // true indicates multipart message

        helper.setTo(email);
        helper.setSubject("OTP for Hirezy");

        ClassPathResource file = new ClassPathResource("static/Hirezy1.png");
        helper.addInline("logoImage", file);

        String content = "<html><body>" +
                "Greetings from Hirezy<br>This otp is valid for 5 minutes,please use this 6 digit otp for login<br>DO NOT SHARE THIS OTP FOR SECURITY REASONS" +
                "<br>Your Hirezy OTP is: " + otpCode +
                "<br><img alt=\"logo\" src=\"static/Hirezy1.png\"/>" +
                "</body></html>";
        System.out.println(content);
        helper.setText(content, true);

        javaMailSender.send(message);

        return "OTP generated and sent to " + email;
    }

    /**
     * This method is used to check if otp sent by the user matches
     * with the otp stored in redis
     * @param email
     * @param otpCode
     * @return
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
     * This method is used to limit the number of otp generation attempts
     * @param email
     * @return
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
     * This is the method used to create a unique random otp
     * for a user
     * @param length
     * @return
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
     * verification of the email takes place
     * to check if the account is verified
     * @param email
     * @return
     */
    public boolean isAccountVerified(String email) {
        String verifiedKey = "verified:" + email;
        return Boolean.TRUE.equals(redisTemplate.hasKey(verifiedKey));
    }

}