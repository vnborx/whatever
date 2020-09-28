import com.vnborx.mapper.DealerMapper;
import com.vnborx.pojo.Dealer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {
    @Test
    public void Test() throws IOException {
        String resources = "mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(resources);

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sessionFactory.openSession(true);

        DealerMapper mapper = sqlSession.getMapper(DealerMapper.class);
        List<Dealer> dealerList = mapper.selectDealer();

        for (Dealer dealer : dealerList) {
            System.out.println(dealer);
        }
    }
}
