import com.HyChat.server.Entity.User;
import com.HyChat.server.untity.JwtUntity;
import org.junit.Test;

public class UntityTest {
    @Test
    public void TestJwtUntity(){
       String jwt= JwtUntity.EncoderJwt(new User("admin","pwd","张三",true));
        System.out.println(jwt);
        System.out.println(JwtUntity.DecodingJwt(jwt.trim()));
    }

}
