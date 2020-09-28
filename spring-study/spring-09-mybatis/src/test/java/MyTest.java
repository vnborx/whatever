import com.vnborx.mapper.DealerMapper;
import com.vnborx.pojo.Dealer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {
    @Test
    public void Test() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        DealerMapper dealerMapper = context.getBean("dealerMapper", DealerMapper.class);
        for (Dealer dealer : dealerMapper.selectDealer()) {
            System.out.println(dealer);
        }
    }
}
