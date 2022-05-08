import com.HyChat.server.Message.ReqMessage;
import com.HyChat.server.Message.UserMessageOuterClass;
import com.google.protobuf.ByteString;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestServer {

    @Test
    public void CliConn() throws IOException, InterruptedException {
        Socket socket=new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1",8888));

        while (true){

            socket.getOutputStream().write("hello".getBytes());
            Thread.sleep(1000);
        }
    }

    @Test
    public void CliSend() throws IOException {

    }

    @Test
    public void Cli1() throws IOException {

    }
    @Test
    public void T2() throws IOException {

    }


    public String Login() throws IOException {
        Socket socket=new Socket();
        try {
            socket.connect(new InetSocketAddress("127.0.0.1",8888));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random random=new Random();
        ReqMessage.MegBody.Builder mBody = ReqMessage.MegBody.newBuilder();
        mBody.setMegType(1);
        UserMessageOuterClass.UserMessage.Builder userMessage = UserMessageOuterClass.UserMessage.newBuilder();
        userMessage.setAdmin(String.format("admin",random.nextInt(10000)));

        userMessage.setPassWord("adkj");

        mBody.setBody(ByteString.copyFrom(userMessage.build().toByteArray()));

        socket.getOutputStream().write(mBody.build().toByteArray());

        System.out.println("发送");

        return "";
    }

    /**
     * 测试
     */
    @Test
   public void ab(){

        ExecutorService service = Executors.newFixedThreadPool(1000);
         CountDownLatch latch=new CountDownLatch(1000);
        for (int i=0;i<10000;i++){
            service.execute(new Runnable() {
                @Override
                public void run() {

                    try {
                        Login();
                        latch.countDown();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
