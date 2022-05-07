package com.HyChat.server.Handle;

import com.HyChat.server.Entity.User;
import com.HyChat.server.Message.Message;
import com.HyChat.server.Message.OnLineUser;
import com.HyChat.server.Message.ResultMessageOuterClass;
import com.HyChat.server.Message.UserMessageOuterClass;
import com.HyChat.server.Service.ChatUserService;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class MegHandelimpl extends MegHandel{

    static class MessageForard{
        /**
         * 群发消息
         * @param bodys
         * @throws IOException
         */
        void  Mass (byte[] bodys) throws IOException {
            System.out.println("群发消息");
            System.out.println(OnlineUser.values().size());
            for (SelectionKey key : OnlineUser.values()) {
                WriteMessage(key,"jell".getBytes());
            }
        }
    }

    @Override
    void TexHandle(Message.MegBody body) {
        if (body.getIsMass()){
            try {
                //群发消息
                new MessageForard().Mass(body.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        byte[] meg= body.getBody().toByteArray();

        System.out.println(new String(meg));

    }

    /**
     * 登录消息
     * @param body
     * @param channel
     */
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
            resultMessage.setResult(!res.equals("账户或者密码错误"));

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

    @Override
    void GetOnLine(SelectionKey channel) throws IOException {
        OnLineUser.OnlineUserMeg.Builder Meg=OnLineUser.OnlineUserMeg.newBuilder();
        System.out.printf("当前在线用户数%d",OnlineUser.size());
        for (String s : OnlineUser.keySet()) {
            User user = ChatUserService.GetUser(s);
            OnLineUser.user.Builder User=OnLineUser.user.newBuilder();
            User.setAdmin(s);
            User.setSex(user.isSex());
            User.setAge(12);
            User.setName(user.getName());
            Meg.addOnlineProson(User);
        }
        WriteMessage(channel,Meg.build().toByteArray());
    }
}
