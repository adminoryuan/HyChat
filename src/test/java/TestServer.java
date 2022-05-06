import com.google.protobuf.ByteString;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;



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

}
