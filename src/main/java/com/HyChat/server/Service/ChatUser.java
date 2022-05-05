package com.HyChat.server.User;

import com.HyChat.server.Entity.User;
import com.HyChat.server.untity.JwtUntity;

import javax.jws.soap.SOAPBinding;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟一个用户的登录注册等。。
 */
public class ChatUser {
    /**
     * 模拟数据库 存储用户 为了快速命中用户
     */
    private static Map<String,User> chatUserMap;

    static {
        chatUserMap=new ConcurrentHashMap<>();
        chatUserMap.put("admin",new User());
    }

    /**
     * 用户登录返回鉴权
     * @return
     */
    public String Login(String admin, String Password){
        if (chatUserMap.containsKey(admin)){
            User user=chatUserMap.get(admin);
            if (user.getAdmin().equals(admin)&&user.getPassword().equals(Password)){
                return JwtUntity.EncoderJwt(user);
            }
        }
        return "账户或者密码错误";

    }
}
