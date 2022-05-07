import com.google.protobuf.ByteString;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;


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
        Socket socket=new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1",8888));

        Message.MegBody.Builder mBody= Message.MegBody.newBuilder();
        mBody.setMegType(2);
        mBody.setTarget("zhangsan");
        mBody.setToken("lisi");
        mBody.setBody(ByteString.copyFrom("hello".getBytes()));
        while (true) {
            byte[] bodys=mBody.build().toByteArray();
            System.out.println(bodys.length);
            socket.getOutputStream().write(bodys);
            break;
        }
        while (true);
    }

    @Test
    public void Cli1() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 8888));

        Message.MegBody.Builder mBody = Message.MegBody.newBuilder();
        mBody.setMegType(2);
        mBody.setIsMass(true);
        mBody.setToken(Login());
        mBody.setBody(ByteString.copyFrom("我是客户端1".getBytes()));

        socket.getOutputStream().write(mBody.build().toByteArray());

        while (true) {
            byte[] bodys = new byte[1024];

            int len = socket.getInputStream().read(bodys);

            System.out.println("收到消息");
            System.out.println(new String(bodys));
        }
    }
    @Test
    public void T2() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 8888));

        Message.MegBody.Builder mBody = Message.MegBody.newBuilder();
        mBody.setMegType(2);
        mBody.setIsMass(true);
        mBody.setToken(Login());
        mBody.setBody(ByteString.copyFrom("我是客户端2".getBytes()));


        while (true) {
            byte[] bodys = new byte[1024];

            int len = socket.getInputStream().read(bodys);


            System.out.println("收到消息");
            System.out.println(new String(bodys));
        }
    }

    public String Login() throws IOException {

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 8888));

        Message.MegBody.Builder mBody = Message.MegBody.newBuilder();
        mBody.setMegType(1);
        UserMessageOuterClass.UserMessage.Builder userMessage = UserMessageOuterClass.UserMessage.newBuilder();
        userMessage.setAdmin("admin1");
        userMessage.setPassWord("passwod");
        mBody.setBody(ByteString.copyFrom(userMessage.build().toByteArray()));

        socket.getOutputStream().write(mBody.build().toByteArray());


        byte[] bodys = new byte[1024];

        int len = socket.getInputStream().read(bodys);

        ResultMessageOuterClass.ResultMessage resultMessage = ResultMessageOuterClass.ResultMessage.parseFrom(Arrays.copyOfRange(bodys, 0, len));

        socket.close();
        return resultMessage.getData();

    }

}
