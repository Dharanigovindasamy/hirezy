package com.ideas2it.hirezy.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;


import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * This is the service class that is used
 * for generation of the otp
 * verification of the otp and account takes place
 *
 * @author audhithiyah
 */
@Service
public class OtpService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private final Logger logger = LogManager.getLogger(OtpService.class);

    private static final String OTP_PREFIX = "otp:";
    private static final String RATE_LIMIT_PREFIX = "rate_limit:";
    private static final int RATE_LIMIT = 5; //number of attempts for a hirezy user
    private static final long RATE_LIMIT_PERIOD_SECONDS = 300; //expiration time for a hirezy user

    /**
     * <p>
     * Generating otp and valid with specific time interval along with OTP code.
     * If time limit is exceed, try again otherwise send otp to mail
     * </p>
     *
     * @param email - sending mail
     * @return String - OTP status to the mail
     */
    public String generateOTP(String email) throws MessagingException {
        if (isRateLimited(email)) {
            logger.warn("cannot generate otp as the number of attempts has exceeded 5");
            return "Rate limit exceeded. Please try again later.";
        }

        String otpCode = generateRandomOTP();
        String otpKey = OTP_PREFIX + email;
        redisTemplate.opsForValue().set(otpKey, otpCode, Duration.ofMinutes(5));

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("OTP for Hirezy");

        ClassPathResource file = new ClassPathResource("/static/Hirezy1.png");
        String cid = "logoImage";
        helper.addInline(cid, file, "image/png");

        String content = "<html><body>" + "Greetings from Hirezy<br>This otp is valid for 5 minutes,<br>please use this 6 digit otp for login<br>DO NOT SHARE THIS OTP FOR SECURITY REASONS" + "<br>Your Hirezy OTP is: " + otpCode + "<br><img alt=\"logo\" src=\"cid:logoImage\" />" + "</body></html>";
        System.out.println(content);
        helper.setText(content, true);

        javaMailSender.send(message);

        return "OTP generated and sent to " + email;
    }

    /**
     * <p>
     * Verifying OTP status
     * </p>
     *
     * @param email   - email id of the user
     * @param otpCode - otp sent from mail
     * @return boolean - if verifies return true. otherwise, return false
     */
    public boolean verifyOTP(String email, String otpCode) {
        String otpKey = OTP_PREFIX + email;
        String storedOTP = redisTemplate.opsForValue().get(otpKey);
        if (storedOTP != null && storedOTP.equals(otpCode)) {
            redisTemplate.delete(otpKey);
            redisTemplate.opsForValue().set("verified:" + email, "true");
            logger.info("the otp has been successfully verified");
            return true;
        }
        logger.warn("the otp is not valid");
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
        if (1 == currentCount) {
            redisTemplate.expire(rateLimitKey, RATE_LIMIT_PERIOD_SECONDS, TimeUnit.SECONDS);
        }
        return currentCount > RATE_LIMIT;
    }

    /**
     * <p>
     * Generating OTP with random numbers of 6 digit length
     * </p>
     *
     * @return String - OTP generated
     */
    private String generateRandomOTP() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    /**
     * <p>
     * Check the account is verified or not by entering OTP
     * </p>
     *
     * @param email - User mail id
     * @return true if account is verified, otherwise false
     */
    public boolean isAccountVerified(String email) {
        String verifiedKey = "verified:" + email;
        return Boolean.TRUE.equals(redisTemplate.hasKey(verifiedKey));
    }
}