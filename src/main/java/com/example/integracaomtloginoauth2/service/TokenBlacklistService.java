package com.example.integracaomtloginoauth2.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.Date;

@Service
public class TokenBlacklistService {

    private final StringRedisTemplate redisTemplate;
    private final JwtTokenService jwtTokenService;

    public TokenBlacklistService(StringRedisTemplate redisTemplate, JwtTokenService jwtTokenService) {
        this.redisTemplate = redisTemplate;
        this.jwtTokenService = jwtTokenService;
    }

    /**
     * Adiciona um token à blacklist no Redis.
     * O token ficará na blacklist apenas até a sua data de expiração original.
     * @param token O token JWT a ser invalidado.
     */
    public void blacklistToken(String token) {
        Date expirationDate = jwtTokenService.getExpirationDateFromToken(token);
        long remainingTime = expirationDate.getTime() - System.currentTimeMillis();

        if (remainingTime > 0) {
            // A chave será o próprio token, e o valor pode ser qualquer coisa.
            // O importante é o TTL (Time-To-Live).
            redisTemplate.opsForValue().set(token, "blacklisted", remainingTime, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * Verifica se um token está na blacklist.
     * @param token O token JWT a ser verificado.
     * @return true se o token estiver na blacklist, false caso contrário.
     */
    public boolean isTokenBlacklisted(String token) {
        return redisTemplate.hasKey(token);
    }
}