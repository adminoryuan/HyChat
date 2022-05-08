package com.HyChat.cli;

import com.HyChat.server.Message.OnLineUser;
import com.HyChat.server.Message.ReqMessage;
import com.HyChat.server.Message.ResultMessageOuterClass;
import com.HyChat.server.Message.UserMessageOuterClass;
import com.google.protobuf.ByteString;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 简易客户端
 */
public class Client {

    private BlockingDeque< ResultMessageOuterClass.ResultMessage> blockingDeque=new LinkedBlockingDeque<>();

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
    public void FuncList() throws IOException, InterruptedException {

        System.out.println("1- 登录");
        System.out.println("2- 查看所有在线用户");

        System.out.println("3- 群发消息");
        System.out.println("4- 私发消息");

       int cmd= scanner.nextInt();

       switch (cmd){
           case 1:
               Login();break;
           case 2:
               OnLine();break;
           case 3:Mass();break;
           case 4:
               Hair();break;

       }
       FuncList();
    }


    public void OnRead() throws IOException, InterruptedException {
        while (true) {
            byte[] bodys = new byte[1024];

            int len = socket.getInputStream().read(bodys);

            ResultMessageOuterClass.ResultMessage resultMessage = ResultMessageOuterClass.ResultMessage.parseFrom(Arrays.copyOfRange(bodys, 0, len));

            if (resultMessage.getResult() && resultMessage.getMessType()==3){

                System.out.println(new String(resultMessage.getData().toByteArray()));
                continue;
            }

            blockingDeque.push(resultMessage);
        }

    }
    /**
     * 私发消息
     */
    public void Hair() throws IOException {
        System.out.println("输入发送用户名");
        String Target= scanner.next();
        System.out.println("输入发送内容");

        String bodys= scanner.next();

        ReqMessage.MegBody.Builder megBody=ReqMessage.MegBody.newBuilder();

        megBody.setToken(token);

        megBody.setTarget(Target);

        megBody.setMegType(2);

        megBody.setBody(ByteString.copyFrom(bodys.getBytes()));

        socket.getOutputStream().write(megBody.build().toByteArray());
    }

    /**
     * 群发消息
     */
    public void Mass() throws IOException {
        ReqMessage.MegBody.Builder megBody= ReqMessage.MegBody.newBuilder();
        megBody.setMegType(2);
        megBody.setToken(this.token);
        megBody.setIsMass(true);
        megBody.setBody(ByteString.copyFrom("客户端 ：ajhkjhkjhkhkhkh".getBytes()));
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


        ResultMessageOuterClass.ResultMessage takeMeg=null;

        while (takeMeg==null){
            try {
                takeMeg= blockingDeque.take();
                System.out.printf("锁 %d",takeMeg.getMessType());
                if (takeMeg.getMessType()==2) break;


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            blockingDeque.push(takeMeg);
        }
        if (!takeMeg.getResult()){
            System.out.println("请先登录！！！");
            return;
        }

        OnLineUser.OnlineUserMeg resultMessage = OnLineUser.OnlineUserMeg.parseFrom(takeMeg.getData().toByteArray());
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
    public void Login() throws IOException, InterruptedException {
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

        ResultMessageOuterClass.ResultMessage takeMeg=null;

        while (takeMeg==null ){
             takeMeg= blockingDeque.take();
             if ( takeMeg.getMessType()==1) break;
             blockingDeque.push(takeMeg);

        }

        if (!takeMeg.getResult()){
            System.out.println("账户或者密码错误");
            return;
        }
        token=new String(takeMeg.getData().toByteArray());
        System.out.println(token);
        System.out.println("登录成功");
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Client client = new Client();
        try {
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    client.OnRead();
                }
            }).start();

          client.FuncList();


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        new Scanner(System.in).next();
    }
}
