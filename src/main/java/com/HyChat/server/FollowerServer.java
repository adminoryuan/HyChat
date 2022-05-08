package com.HyChat.server;

import com.HyChat.server.Handle.MegHandel;
import com.HyChat.server.Handle.MegHandelimpl;
import com.HyChat.server.Message.ReqMessage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.*;

/**
 * reactor模型 的子reactor
 * 监听读写事件
 */
public class FollowerServer {
    private Selector selector;

    /**
     * 消息转发器
     */
    private MegHandel megHandel;

    private ExecutorService factory;

    public  FollowerServer() throws IOException {
        this.selector = Selector.open();
        factory= Executors.newCachedThreadPool();
        megHandel=new MegHandelimpl();
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                ReadPoll();
            }
        }).start();
    }
    public  void Regist(SocketChannel channel) throws ClosedChannelException {

        channel.register(selector, SelectionKey.OP_READ);
    }
    private void ReadPoll() throws IOException {
        while (true){
            int selectLen=0;
            //设置一个超时时间不然无法注册事件
            selectLen=selector.select(1000);

            if (selectLen>0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey currKey = iterator.next();
                    iterator.remove();
                    if (currKey.isReadable()){

                        SocketChannel channel = (SocketChannel) currKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int length = channel.read(buffer);
                        if (length==-1){
                            //下线了
                            MegHandelimpl.Offline(currKey);
                            currKey.cancel();
                            //selector.keys().remove(currKey);
                            continue;
                        }
                        try {
                            factory.execute(new Runnable() {
                                @SneakyThrows
                                @Override
                                public void run() {

                                   ReqMessage.MegBody megBody = ReqMessage.MegBody.parseFrom(Arrays.copyOfRange(buffer.array(),0,length));

                                   megHandel.DoHandel(megBody,currKey);

                                }
                            });

                        }
                        catch (Exception e){


                        }
                    }else if (currKey.isWritable()){

                    }

                }
            }
        }
    }

}
