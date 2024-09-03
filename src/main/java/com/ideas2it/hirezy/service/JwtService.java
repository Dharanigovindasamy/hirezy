package com.ideas2it.hirezy.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * This class is where the token and signature is generated
 * and the operations of extracting all and
 * specific claims take place
 * @author audhithiyah
 */
@Service
public class JwtService {

    private static final String SECRET_KEY = System.getenv("SECRET_KEY");

    /**
     * This method is used to obtain the username from the claims in the token.
     * It extracts the subject (username) from the token's claims.
     *
     * @param token the JWT token
     * @return the username extracted from the token
     */
    public String extractUsername(String token) {

        return extractClaim(token,Claims::getSubject);
    }

    /**
     * This method is used to get all details from the token.
     * It extracts the claims from the token and applies the claims resolver function.
     *
     * @param token the JWT token
     * @param claimsResolver a function to resolve the claims
     * @param <T> the type of the claim
     * @return the resolved claim
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractALlClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * This method is used to generate a token for the given user details.
     * It creates a token with default claims.
     *
     * @param userDetails the user details
     * @return the generated JWT token
     */
    public String generateToken(UserDetails userDetails) {

        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * This method is used to generate a token with additional claims.
     * It creates a token with the specified extra claims and user details.
     *
     * @param extraClaims additional claims to be included in the token
     * @param userDetails the user details
     * @return the generated JWT token
     */
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
            ) {
              return Jwts
                      .builder()
                      .setClaims(extraClaims)
                      .setSubject(userDetails.getUsername())
                      .setIssuedAt(new Date(System.currentTimeMillis()))
                      .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 *24))
                      .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                      .compact();

    }

    /**
     * This method checks if the token is valid for the given user details.
     * It verifies that the username in the token matches the user's username and that the token is not expired.
     *
     * @param token the JWT token
     * @param userDetails the user details
     * @return true if the token is valid, false otherwise
     */

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * This method checks if the token has expired.
     * It compares the token's expiration date with the current date.
     *
     * @param token the JWT token
     * @return true if the token is expired, false otherwise
     */
    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }

    /**
     * This method extracts the expiration date from the token.
     * It retrieves the expiration claim from the token.
     *
     * @param token the JWT token
     * @return the expiration date of the token
     */
    private Date extractExpiration(String token) {

        return extractClaim(token,Claims::getExpiration);
    }

    /**
     * This method extracts all claims from the token.
     * It parses the token to retrieve its claims.
     *
     * @param token the JWT token
     * @return the claims extracted from the token
     */
    private Claims extractALlClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * This method retrieves the signing key used to sign the token.
     * It decodes the secret key and returns the signing key.
     *
     * @return the signing key
     */
    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
