package com.HyChat.server.Service;

import com.HyChat.server.Entity.User;
import com.HyChat.server.untity.JwtUntity;

import java.nio.channels.SelectionKey;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟一个用户的登录注册等。。
 */
public class ChatUserService {

    /**
     * 保存当前在线用户
     */
    public static Map<String, SelectionKey> OnLineMap;

    /**
     * 模拟数据库 存储用户 为了快速命中用户
     */

    private static Map<String,User> chatUserMap;

    static {
        chatUserMap=new ConcurrentHashMap<>();
        chatUserMap.put("admin",new User("admin","passwod","张三",true));

        chatUserMap.put("admin1",new User("admin1","passwod","李四",true));
        OnLineMap=new HashMap<>();
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
    public static User GetUser(String admin){
        return chatUserMap.get(admin);
    }
}
