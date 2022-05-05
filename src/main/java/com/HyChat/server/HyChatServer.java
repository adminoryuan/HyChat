package com.HyChat.server;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * 启动类
 */
public class HyChatServer {

    private Selector selector;
    private ServerSocketChannel socketChannel;
    private int keys=0;
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
       socketChannel.configureBlocking(false);
       socketChannel.socket().bind(new InetSocketAddress("127.0.0.1",8888));
       socketChannel.register(selector, SelectionKey.OP_ACCEPT);//链接时触发

    }

    /**
     * 处理链接请求
     */
    private void HandelConn() throws IOException {
        System.out.println("链接启动");
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
                        channel.write(ByteBuffer.wrap(new String("Hello Client.").getBytes()));

                        //注册读事件
                        channel.register(selector, SelectionKey.OP_READ);

                        System.out.println("收到链接");
                    }else if (currKey.isReadable()){
                        SocketChannel channel = (SocketChannel) currKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);

                        int length = channel.read(buffer);
                        String msg = "server receive msg:" + new String(buffer.array(), 0, length);
                        System.out.println(msg);

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
