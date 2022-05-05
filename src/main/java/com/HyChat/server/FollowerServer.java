package com.HyChat.server;

import com.HyChat.server.Handle.MegHandel;
import com.HyChat.server.Handle.Message.Message;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

/**
 * 监听读事件
 */
public class ReadEvent {

    private Selector selector;
    private MegHandel megHandel;

    private ExecutorService factory;

    private void ReadEvent(SocketChannel channel) throws IOException {
        channel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
        this.selector = Selector.open();
        factory= Executors.newCachedThreadPool();
        ReadPoll();
    }
    private void ReadPoll() throws IOException {
        while (true){
            int selectLen = selector.select();
            if (selectLen>0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey currKey = iterator.next();
                    iterator.remove();
                    if (currKey.isReadable()){
                        SocketChannel channel = (SocketChannel) currKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int length = channel.read(buffer);
                        try {
                            factory.execute(new Runnable() {
                                @SneakyThrows
                                @Override
                                public void run() {
                                    Message.MegBody megBody = Message.MegBody.parseFrom(Arrays.copyOfRange(buffer.array(),0,length));
                                    megHandel.DoHandel(megBody,channel);
                                }
                            });

                        }
                        catch (Exception e){

                        }
                    }else if (currKey.isWritable()){
                        //可写
                    }

                }
            }
        }
    }

}
