package com.HyChat.server.untity;

import com.HyChat.server.Handle.Message.ResultMessageOuterClass;

/**
 * 封装一些常用消息
 */
public class Messageuntity {

    public static ResultMessageOuterClass.ResultMessage.Builder Unauthorized(){
        ResultMessageOuterClass.ResultMessage.Builder res=ResultMessageOuterClass.ResultMessage.newBuilder();
        res.setResult(false);
        res.setData("未授权!");
        return res;
    }
}
