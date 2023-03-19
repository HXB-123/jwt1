package com.atguigu.jwt;

import io.jsonwebtoken.*;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

public class JwtTest {

    private static long tokenExpiration = 1000 * 60 * 60 * 24;
    private static String tokenSignKey = "atguigu123";
    @Test
    public void testCreatedToken(){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder.setHeaderParam("alg","HS256")
                  .setHeaderParam("typ","JWT")
                .claim("nickname","kevin")
                .claim("role","admin")
                .setSubject("srb-user")
                .setIssuer("atguigu")
                .setAudience("atguigu")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .setNotBefore(new Date(System.currentTimeMillis() + 1000 * 20))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256,tokenSignKey)
                .compact();

        System.out.println(jwtToken);
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
         System.out.println("HELLO WORLD1");
    }

    @Test
    public void testGetUserInfo(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6ImtldmluIiwicm9sZSI6ImFkbWluIiwic3ViIjoic3JiLXVzZXIiLCJpc3MiOiJhdGd1aWd1IiwiYXVkIjoiYXRndWlndSIsImlhdCI6MTY0ODY0ODU5NiwiZXhwIjoxNjQ4NzM0OTk2LCJuYmYiOjE2NDg2NDg2MTYsImp0aSI6ImZmNTEwY2EyLTZhMzEtNDM2MS04MWZiLTUxZmFlMDM1YjA1NiJ9.dbQhU3n4so0d7dAG_KsInkAd_KvYBj1wL55by2xrafQ";

        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(tokenSignKey).parseClaimsJws(token);

        Claims claims = claimsJws.getBody();
        String nickname = (String) claims.get("nickname");
        System.out.println(nickname);
        String role = (String) claims.get("role");
        System.out.println(role);
        String id = claims.getId();
        System.out.println(id);
    }
}
