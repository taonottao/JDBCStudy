package com.attao.jdbc;

import com.attao.pojo.Account;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC  API 详解：ResultSet
 * @version 1.0
 * @Author T-WANG
 * @Date 2023/3/29 22:54
 */
public class JDBCDemo4_ResultSet1 {
    /**
     * 查询account账户数据表，封装为account对象中，并储存到ArrayList集合中
     * 1.定义实体类Account
     * 2.查询数据，封装到Account对象中
     * 3.将Account对象存储到ArrayList集合中
     *
     * @throws Exception
     */
    @Test
    public void testResultSet() throws Exception {
        //1.注册驱动
        //Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接:如果连接的是本机mysql并且端口是默认的3306，可以简化书写
        //String url = "jdbc:mysql://127.0.0.1:3306/testdb";
        String url = "jdbc:mysql:///testdb?useSSL=false";
        String username = "root";
        String password = "wangtao";
        Connection conn = DriverManager.getConnection(url, username, password);

        //3.定义sql
        String sql = "select * from account";

        //4.获取statement对象
        Statement stmt = conn.createStatement();
        //5.执行sql
        ResultSet rs = stmt.executeQuery(sql);
        //创建一个集合
        List<Account>  list = new ArrayList<>();
        Account account = new Account();
        //6.1光标向下移动一行，并且判断当前行是否有数据
        while(rs.next()){
            //6.2获取数据:getXxx()
            int id = rs.getInt("id");
            String name = rs.getString("name");
            double money = rs.getDouble("money");
            //赋值
            account.setId(id);
            account.setName(name);
            account.setMoney(money);

            list.add(account);
        }

        System.out.println(list);
        //7.释放资源
        rs.close();
        stmt.close();
        conn.close();

    }

}
