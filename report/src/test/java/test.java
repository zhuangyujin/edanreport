import com.edan.report.utils.TokenUtils;
import org.junit.jupiter.api.Test;

public class test {
    @Test
    public void test(){
        String userId = TokenUtils.getUserId("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsImV4cCI6MTY4MjkwODQ3MCwidXNlcmlkIjozfQ.s_wAn-xmfQ5JdgCdxZUvgkuihxKvueihOnwcx2dA8LY");
        System.out.println(userId);
    }
}
