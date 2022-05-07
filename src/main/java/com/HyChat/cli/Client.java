package com.HyChat.cli;

import com.HyChat.server.Message.OnLineUser;
import com.HyChat.server.Message.ReqMessage;
import com.HyChat.server.Message.ResultMessageOuterClass;
import com.HyChat.server.Message.UserMessageOuterClass;
import com.google.protobuf.ByteString;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 简易客户端
 */
public class Client {
    private Scanner scanner=new Scanner(System.in);

    private Socket socket=new Socket();
    {
        try {
            socket.connect(new InetSocketAddress("127.0.0.1",8888));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String token="";
    /**
     * 功能列表
     */
    public void FuncList(){
        System.out.println("1- 登录");
        System.out.println("2- 查看所有在线用户");
        System.out.println("3- 私发消息");
        System.out.println("4- 群发消息");
    }

    /**
     * 私发消息
     */
    public void Hair(){

    }

    /**
     * 群发消息
     */
    public void Mass() throws IOException {
        ReqMessage.MegBody.Builder megBody= ReqMessage.MegBody.newBuilder();
        megBody.setMegType(1);
        megBody.setIsMass(true);
        megBody.setBody(ByteString.copyFrom( "a".getBytes()));
        socket.getOutputStream().write(megBody.build().toByteArray());
    }

    /**
     * 显示当前所有在线用户
     * @throws IOException
     */
    public void OnLine() throws IOException {
        ReqMessage.MegBody.Builder mBody = ReqMessage.MegBody.newBuilder();
        mBody.setMegType(4);
        mBody.setToken(token);
        socket.getOutputStream().write(mBody.build().toByteArray());

        byte[] bodys = new byte[1024];

        int len = socket.getInputStream().read(bodys);

        OnLineUser.OnlineUserMeg resultMessage = OnLineUser.OnlineUserMeg.parseFrom(Arrays.copyOfRange(bodys,0,len));

        System.out.println("================当前在线用户==============================");
        for (OnLineUser.user user : resultMessage.getOnlineProsonList()) {
            System.out.printf("用户名-%s  姓名:%s 性别:%s 年龄:%d \n",user.getAdmin(),user.getName(),user.getSex()?"男":"女",user.getAge());
        }
        System.out.println("=========================================================");

    }
    /**
     * 登录
     * @throws IOException
     */
    public void Login() throws IOException {
        System.out.println("请输入你的账户\n");
        String admin= scanner.next();
        System.out.println("请输入你的密码");
        String password=scanner.next();

        ReqMessage.MegBody.Builder mBody = ReqMessage.MegBody.newBuilder();
        mBody.setMegType(1);
        UserMessageOuterClass.UserMessage.Builder userMessage = UserMessageOuterClass.UserMessage.newBuilder();
        userMessage.setAdmin(admin);
        userMessage.setPassWord(password);
        mBody.setBody(ByteString.copyFrom(userMessage.build().toByteArray()));
        socket.getOutputStream().write(mBody.build().toByteArray());

        byte[] bodys = new byte[1024];

        int len = socket.getInputStream().read(bodys);

        ResultMessageOuterClass.ResultMessage resultMessage = ResultMessageOuterClass.ResultMessage.parseFrom(Arrays.copyOfRange(bodys, 0, len));

        if (!resultMessage.getResult()){
            System.out.println("账户或者密码错误，请重新登录");
        }else{
            System.out.println("登录成功！");
            this.token=resultMessage.getData().toString();
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.Login();

            client.OnLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Scanner(System.in).next();
    }
}
