package com.HyChat.server.Handle;

import com.HyChat.server.Entity.User;
import com.HyChat.server.Message.OnLineUser;
import com.HyChat.server.Message.ReqMessage;
import com.HyChat.server.Message.ResultMessageOuterClass;
import com.HyChat.server.Message.UserMessageOuterClass;
import com.HyChat.server.Service.ChatUserService;
import com.HyChat.server.ThreadPool.TaskPool;
import com.HyChat.server.untity.LoggerUntity;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Timestamp;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class MegHandelimpl extends MegHandel{


        /**
         * 群发消息
         * @param bodys
         * @throws IOException
         */
     public  void  Mass (byte[] bodys) throws IOException {
            ResultMessageOuterClass.ResultMessage.Builder res=ResultMessageOuterClass.ResultMessage.newBuilder();
            res.setResult(true);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            res.setData(ByteString.copyFrom(bodys));
            res.setMessType(3);

           LoggerUntity.LogWaring("群发消息");
           for (SelectionKey key : OnlineUser.values()) {
                WriteMessage(key,res.build().toByteArray());
            }
        }


    @Override
    void TexHandle(ReqMessage.MegBody body) {
        if (body.getIsMass()){

                TaskPool.Sumit(new Runnable() {
                    @Override
                    public void run() {
                        //群发消息
                        try {
                            Mass(body.getBody().toByteArray());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            return;
        }
        ResultMessageOuterClass.ResultMessage.Builder meg= ResultMessageOuterClass.ResultMessage.newBuilder();

        meg.setMessType(3);
        meg.setData(body.getBody());
        meg.setResult(true);

        try {
            WriteMessage(OnlineUser.get(body.getTarget()),meg.build().toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 登录消息
     * @param body
     * @param channel
     */
    @Override
    void LoginHandle(ReqMessage.MegBody body, SelectionKey channel) {
        try {
            System.out.println("登录....");
            UserMessageOuterClass.UserMessage userMessage = UserMessageOuterClass.UserMessage.parseFrom(body.getBody().toByteArray());

            String res= new ChatUserService().Login(userMessage.getAdmin(),userMessage.getPassWord());

            ResultMessageOuterClass.ResultMessage.Builder resultMessage= ResultMessageOuterClass.ResultMessage.newBuilder();
            resultMessage.setMessType(1);
            if (!res.equals("账户或者密码错误")){
                //添加用户和selectionkey 的绑定关系

                OnlineUser.put(userMessage.getAdmin(),channel);
            }

            //jwt 为空
            resultMessage.setResult(!res.equals("账户或者密码错误"));

            resultMessage.setData(ByteString.copyFrom(res.getBytes()));

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
    void BinaryHandle(ReqMessage.MegBody body) {

    }

    @Override
    void GetOnLine(SelectionKey channel) throws IOException {
        OnLineUser.OnlineUserMeg.Builder Meg= OnLineUser.OnlineUserMeg.newBuilder();

        ResultMessageOuterClass.ResultMessage.Builder res=ResultMessageOuterClass.ResultMessage.newBuilder();


        LoggerUntity.LogInfo(String.format("当前在线用户数%d", OnlineUser.size()));

        for (String s : OnlineUser.keySet()) {
            User user = ChatUserService.GetUser(s);
            OnLineUser.user.Builder User=OnLineUser.user.newBuilder();
            User.setAdmin(s);
            User.setSex(user.isSex());
            User.setAge(12);
            User.setName(user.getName());
            Meg.addOnlineProson(User);
        }
        res.setData(Meg.build().toByteString());
        res.setResult(true);
        res.setSendTime(Timestamp.newBuilder().build());
        res.setMessType(2);

        WriteMessage(channel,res.build().toByteArray());
    }
}
