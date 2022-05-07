package com.HyChat.server.untity;

import com.HyChat.server.Message.ResultMessageOuterClass;
import com.google.protobuf.ByteString;

/**
 * 封装一些常用消息
 */
public class Messageuntity {

    public static ResultMessageOuterClass.ResultMessage.Builder Unauthorized(){
        ResultMessageOuterClass.ResultMessage.Builder res=ResultMessageOuterClass.ResultMessage.newBuilder();
        res.setResult(false);
        res.setData(ByteString.copyFrom("未授权!".getBytes()));
        return res;
    }
}
