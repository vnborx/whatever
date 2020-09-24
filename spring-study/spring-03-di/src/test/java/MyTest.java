import com.vnborx.pojo.Student;
import com.vnborx.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        Student student = (Student) context.getBean("student");
//        System.out.println(student.toString());
    }

    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("userBeans.xml");
        User user1 = context.getBean("user", User.class);
        User user2 = context.getBean("user", User.class);
        User user3 = context.getBean("user3", User.class);
        User user4 = context.getBean("user3", User.class);
        System.out.println(user1 == user2);
        System.out.println(user3 == user4);
    }
      
}
