package com.example.security.jwt;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by libin on 25/12/2016.
 */

public class TokenAuthenticationService {
    private long EXPIRATION = 1000*60*60*24*10; // 10 day
    private String secret = "Secret";
    private String tokenPrefix = "Bearer";
    private String headerString = "Authorization";

    public void addAuthentication(HttpServletResponse response, String username){

        String jwtToken = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        response.addHeader(headerString, tokenPrefix + " " + jwtToken);

    }

    public Authentication getAuthentication(HttpServletRequest request){
        String token = request.getHeader(headerString);
        if(token != null){
            String username = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            if(username != null){
                return new AuthenticatedUser(username);
            }
        }
        return null;
    }
}
