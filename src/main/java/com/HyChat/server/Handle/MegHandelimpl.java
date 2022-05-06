package com.HyChat.server.Handle;

import com.HyChat.server.Handle.Message.Message;
import com.HyChat.server.Handle.Message.ResultMessageOuterClass;
import com.HyChat.server.Handle.Message.UserMessageOuterClass;
import com.HyChat.server.Service.ChatUserService;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class MegHandelimpl extends MegHandel{

    static class MessageForard{
        /**
         * 群发消息
         * @param bodys
         * @throws IOException
         */
        void  Mass(ByteBuffer bodys) throws IOException {
            for (SelectionKey key : OnlineUser.values()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                socketChannel.write(bodys);
            }
        }
    }

    @Override
    void TexHandle(Message.MegBody body) {

        byte[] meg= body.getBody().toByteArray();

        System.out.println(new String(meg));

    }

    @Override
    void LoginHandle(Message.MegBody body, SelectionKey channel) {
        try {
            UserMessageOuterClass.UserMessage userMessage = UserMessageOuterClass.UserMessage.parseFrom(body.getBody().toByteArray());

            String res= new ChatUserService().Login(userMessage.getAdmin(),userMessage.getPassWord());

            ResultMessageOuterClass.ResultMessage.Builder resultMessage= ResultMessageOuterClass.ResultMessage.newBuilder();

            if (!res.equals("账户或者密码错误")){
                //添加用户和selectionkey 的绑定关系
                OnlineUser.put(userMessage.getAdmin(),channel);
            }

            //jwt 为空
            resultMessage.setResult(res.equals("账户或者密码错误"));

            resultMessage.setData(res);

            SocketChannel channel1 =(SocketChannel) channel.channel();

            //回复
            channel1.write(ByteBuffer.wrap(resultMessage.build().toByteArray()));

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void BinaryHandle(Message.MegBody body) {

    }
}
