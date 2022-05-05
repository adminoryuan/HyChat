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
}
