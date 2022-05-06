package com.HyChat.server;

import com.HyChat.server.Handle.MegHandel;
import com.HyChat.server.Handle.MegHandelimpl;
import com.HyChat.server.Handle.Message.Message;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 启动类 基于rector模型
 */
public class HyChatServer {
    public static BlockingDeque<SelectionKey> keyBlockingDeque=new LinkedBlockingDeque<>();

    private Selector selector;

    private final int MaxFollwer=3;

    private ServerSocketChannel socketChannel;

    private MegHandel megHandel;

    private int keys=0;

    private FollowerServer[] followerServer=new FollowerServer[MaxFollwer];

    private ExecutorService factory= Executors.newFixedThreadPool(3);
    /**
     * 启动server
     */
    public void Start() throws IOException {
        init();
        HandelConn();
    }

    /**
     * 初始化
     * @throws IOException
     */
    private void init() throws IOException {
       socketChannel= ServerSocketChannel.open();
       selector=Selector.open();
       //设置为非阻塞
       socketChannel.configureBlocking(false);
       socketChannel.socket().bind(new InetSocketAddress("127.0.0.1",8888));

       socketChannel.register(selector, SelectionKey.OP_ACCEPT);//注册链接事件

        megHandel=new MegHandelimpl();
        //初始化子rector
        for (int i=0;i<MaxFollwer;i++){
            followerServer[i]=new FollowerServer();
        }
    }

    /**
     * 处理链接请求
     */
    private void HandelConn() throws IOException {
        System.out.println("链接启动");
        int index=0;
        while (true) {
            keys = selector.select();//会阻塞直到有链接请求进来
            if (keys>0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while(iterator.hasNext()){
                    SelectionKey currKey=iterator.next();

                    iterator.remove();

                    if (currKey.isAcceptable()){

                        socketChannel= (ServerSocketChannel) currKey.channel();

                        SocketChannel channel = socketChannel.accept();

                        channel.configureBlocking(false);

                        //注册读事件

                        followerServer[0].Regist(channel);


                        System.out.println("收到链接");
                    }
                }
            }
        }

    }
    public static void main(String[] args) {
        try {
            new HyChatServer().Start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
