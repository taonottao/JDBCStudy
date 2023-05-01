import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @version 1.0
 * @Author T-WANG
 * @Date 2023/5/1 16:52
 */
public class JDBCSelect {
    public static void main(String[] args) throws SQLException {
        //1.创建并初始化数据源
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/testdb2?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource) dataSource).setUser("root");
        ((MysqlDataSource) dataSource).setPassword("wangtao");
        //2.创建连接
        Connection connection = dataSource.getConnection();
        //3.构造SQL
        String sql = "select * from student2";
        PreparedStatement statement = connection.prepareStatement(sql);
        //4.执行SQL语句
        ResultSet resultSet = statement.executeQuery();
        //5.遍历结果集合
        while(resultSet.next()){
            //把resultSet想象成一个表格,同时表格这里有一个光标,初始情况下光标指向表最上面
            //每次调用next ,光标向下走一行
            //当光标指向某一行的时候,就可以通过getXxx来获取到当前这行里的数据
            int id = resultSet.getInt("id");
            String name  = resultSet.getString("name");
            System.out.println("id = " + id + ", name = " +  name);
        }
        //6.释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
