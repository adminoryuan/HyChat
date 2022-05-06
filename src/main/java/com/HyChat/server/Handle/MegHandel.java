package com.HyChat.server.Handle;

import com.HyChat.server.Handle.Message.Message;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 处理消息中转
 */
public  abstract class MegHandel{
    /**
     * 保存在线用户
     */
    protected static Map<String,SelectionKey> OnlineUser=new ConcurrentHashMap<>();
    /**
     * 定义处理消息
     * BodyType
     * 1 为 登录消息
     * 2 为 文本消息
     * 3 位 二进制消息
     * @param body
     */
    public void DoHandel(Message.MegBody body, SelectionKey _ch){

        System.out.println("处理消息");
        System.out.println(body.getMegType());
        switch (body.getMegType()){
            case 1:
                LoginHandle(body,_ch);
                break;
            case 2:
                TexHandle(body);
                break;
            case 3:
                BinaryHandle(body);
                break;
        }
    }

    /**
     * 处理文本消息
     * @param body
     */
    abstract void TexHandle(Message.MegBody body);

    /**
     * 处理登录消息
     * @param body
     * @param channel
     */
    abstract void LoginHandle(Message.MegBody body,SelectionKey channel);

    /**
     * 处理二进制消息 文件等。
     * @param body
     */
    abstract void BinaryHandle(Message.MegBody body);
}
