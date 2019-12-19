import com.ltj.gateway.SecurityGatewayApplication;
import com.ltj.gateway.cache.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

/**
 * @describe： TODO
 * @author: Liu Tian Jun
 * @Date: 2019-12-19 13:03
 * @version: 1.0
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = SecurityGatewayApplication.class)
//@Slf4j
public class SpringTest {

    /*@Autowired
    private IRedisService redisService;

    @Test
    public void test() {
        for (int i=0;i<10;i++) {
            redisService.deleteHashKeys("abc", Collections.singleton("a" + i)).subscribe(System.out::println);
        }
    }*/
}
