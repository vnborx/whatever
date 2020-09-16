import com.vnborx.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        User user = (User)context.getBean("user");
//        user.show();

        User u1 = (User)context.getBean("user");
        User u2 = (User)context.getBean("user");
        System.out.println(u1 == u2); // true

    }
}
