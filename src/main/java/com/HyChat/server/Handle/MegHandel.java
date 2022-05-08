package com.HyChat.server.Handle;

import com.HyChat.server.Message.ReqMessage;
import com.HyChat.server.ThreadPool.TaskPool;
import com.HyChat.server.untity.Messageuntity;
import com.HyChat.server.untity.VerifUntity;

import java.io.IOException;
import java.nio.ByteBuffer;
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

    private VerifUntity untity=new VerifUntity();

    /**
     * 当用户下线时
     */
    public static void Offline(SelectionKey key){
        OnlineUser.values().remove(key);
    }
    /**
     * 定义处理消息
     * BodyType
     * 1 为 登录消息
     * 2 为 文本消息
     * 3 位 二进制消息
     * @param body
     */
    public void DoHandel(ReqMessage.MegBody body, SelectionKey _ch) throws IOException {


        if (body.getMegType()==1){
            LoginHandle(body,_ch);
            return;
        }
        String admin=untity.VerifToken(body.getToken());
        if (admin==null){
            System.out.println("当前用户token 已经失效");
            try {
                WriteMessage(_ch,Messageuntity.Unauthorized().build().toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        switch (body.getMegType()){
            case 2:
                TexHandle(body);
                break;
            case 3:
                BinaryHandle(body);
                break;
            case 4:
                GetOnLine(_ch);
                break;
        }
    }

    /**
     * 发送响应到客户端
     * @param CurrKey
     * @param body
     * @throws IOException
     */
    protected static void WriteMessage(SelectionKey CurrKey, byte[] body) throws IOException {
        TaskPool.Sumit(new Runnable() {
            @Override
            public void run() {

                SocketChannel socketChannel = (SocketChannel) CurrKey.channel();
                try {
                    socketChannel.write(ByteBuffer.wrap(body));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * 处理文本消息
     * @param body
     */
    abstract void TexHandle(ReqMessage.MegBody body);

    /**
     * 处理登录消息
     * @param body
     * @param channel
     */
    abstract void LoginHandle(ReqMessage.MegBody body, SelectionKey channel);

    /**
     * 处理二进制消息 文件等。
     * @param body
     */
    abstract void BinaryHandle(ReqMessage.MegBody body);

    abstract void GetOnLine(SelectionKey channel) throws IOException;

}
