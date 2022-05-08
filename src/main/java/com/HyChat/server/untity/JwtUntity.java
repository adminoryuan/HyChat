package com.HyChat.server.untity;

import com.HyChat.server.Entity.User;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 增加jwt 校验 生成jwt
 */
public class JwtUntity {

    public static String PrivateKey="jaslkdjalksjdlaksjdlkasjdlkajsldkjas";

    /**
     * 生成jwt
     * @param user
     * @return
     */
    public static String EncoderJwt(User user){
        JwtBuilder Jwtbuilder = Jwts.builder();
        Map<String,Object> hashMap=new HashMap<>();
        hashMap.put("admin",user.getAdmin());
        return Jwtbuilder.setSubject("HyServer.")
                .setIssuedAt(new Date())
                .setId("1")
                .setClaims(hashMap)
                .setExpiration(new Date(System.currentTimeMillis()+60*10000))
                .signWith(SignatureAlgorithm.HS256,PrivateKey).compact();
    }

    /**
     * 从jwt中解析出用户名
     * @param jwt
     * @return
     */
    public static String DecodingJwt(String jwt){
        JwtParser jwtParser=Jwts.parser();

        jwtParser.setSigningKey(PrivateKey);
        try {

            Jws<Claims> headerClaimsJwt = jwtParser.parseClaimsJws(jwt);
            return headerClaimsJwt.getBody().get("admin").toString();

        }catch (Exception e){
            return null;
        }
    }
}
