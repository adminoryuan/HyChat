package com.HyChat.server.untity;

/**
 * 验证类
 */
public class VerifUntity {

    /**
     * 验证jwt
     * @param token
     * @return 错误返回null 正确返回用户名
     */
    public String VerifToken(String token){
       return JwtUntity.DecodingJwt(token);
    }
}
