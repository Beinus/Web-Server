package com.example.beinus.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // Get a key with hex from encryption key generating web page. (This one is 256 bit)
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    // private static final String SECRET_KEY = "";

    // Generate a new Token with 'extraClaims' and 'userDetails'.
    public String generateToken(
            Map<String, Object> extraClaims,    // It's for additional custom claims, so it's not essential.
            UserDetails userDetails             // This will be matched with the new token.
    ) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())     // Subject in JWT is similar to ID, Username.
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))  // 24 hours
                .signWith(getSignInKey())
                .compact();     // Generate and return the token (use at the end)
    }

    // Generate a new Token only with 'userDetails'.
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // Check if the token is valid.
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        // return true if the username is equal to the one in the token and if the token is not expired.
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // Check if the token is expired.
    private boolean isTokenExpired(String token) {
        // return true if the expiration date of the token is before the new Date().
        return extractExpiration(token).before(new Date());
    }

    // Get the expiration date of the token.
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract the Username from the token.
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject); // getSubject(JWT ver) is similar to getId, getUsername.
    }

    // Extract only one single claim.
    // Function<T, R> -> T: type of the input, R: type of the result
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);  // Get all the claims like iss, sub, etc.
        return claimsResolver.apply(claims);            // Apply getSubject to get the ID or username of the user.
    }

    // Extract all from the token.
    // Claims is consisted of iss, sub, aud, exp, nbf, iat, jti etc. It's not a list of Claims.
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                // Need to put 'build()' cuz it's builder.
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Get the signin key using SECRET_KEY
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
