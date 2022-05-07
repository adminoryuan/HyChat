package com.HyChat.server.Handle;

import com.HyChat.server.Message.Message;
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
     * 定义处理消息
     * BodyType
     * 1 为 登录消息
     * 2 为 文本消息
     * 3 位 二进制消息
     * @param body
     */
    public void DoHandel(Message.MegBody body, SelectionKey _ch) throws IOException {


        if (body.getMegType()==1){
            LoginHandle(body,_ch);
            return;
        }
        String admin=untity.VerifToken(body.getToken());
        if (admin==null){
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
        SocketChannel socketChannel = (SocketChannel) CurrKey.channel();
        socketChannel.write(ByteBuffer.wrap(body));
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

    abstract void GetOnLine(SelectionKey channel) throws IOException;

}
