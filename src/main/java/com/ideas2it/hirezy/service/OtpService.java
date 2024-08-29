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

/**
 * This is the service class that is used
 * for generation of the otp
 *verification of the otp and account takes place
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