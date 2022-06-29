import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ResourceBundle;

public class DataSourceTest {

    /**
     * 通过spring配置连接
     */
    @Test
    public void test4() throws Exception{
        //获取配置文件
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = (DataSource) app.getBean("dataSource");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    /**
     * 测试手动创建c3p0连接池（加载properties文件）
     */
    @Test
    public void test3() throws Exception{
        //获取配置文件
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        //读取配置文件参数
        String driver = rb.getString("jdbc.driver");
        String url = rb.getString("jdbc.url");
        String username = rb.getString("jdbc.username");
        String password = rb.getString("jdbc.password");

        //创建c3p0连接池
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        //设置连接参数
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    /**
     * 测试手动配置c3p0数据连接池
     */
    @Test
    public void test1() throws Exception{
        //创建c3p0连接池
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        //设置连接参数,mysql8.0连接要加 cj
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        //获得连接对象
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        //释放或归还连接
        connection.close();
    }

    /**
     * 测试druid连接池
     */
    @Test
    public void test2() throws Exception{
        //创建druid连接池
        DruidDataSource dataSource = new DruidDataSource();
        //设置连接参数
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        //获取连接
        DruidPooledConnection connection = dataSource.getConnection();
        System.out.println(connection);
        //关闭连接
        connection.close();
    }
}
