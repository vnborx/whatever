import com.vnborx.mapper.DealerMapper;
import com.vnborx.pojo.Dealer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DealerMapper dealerMapper = context.getBean("dealerMapper", DealerMapper.class);
        for (Dealer dealer : dealerMapper.selectDealer()) {
            System.out.println(dealer);
        }
//        dealerMapper.deleteDealer(0);
    }
}
