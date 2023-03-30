package com.attao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * JDBC  API 详解：DriverManager
 * @version 1.0
 * @Author T-WANG
 * @Date 2023/3/29 22:54
 */
public class JDBCDemo1_DriverManager {
    public static void main(String[] args) throws Exception {
        //1.注册驱动
        //Class.forName("com.mysql.jdbc.Driver");

        //2.获取连接:如果连接的是本机mysql并且端口是默认的3306，可以简化书写
        //String url = "jdbc:mysql://127.0.0.1:3306/testdb";
        String url = "jdbc:mysql:///testdb?useSSL=false";
        String username = "root";
        String password = "wangtao";
        Connection conn = DriverManager.getConnection(url, username, password);

        //3.定义sql
        String sql = "UPDATE employee SET salary = 6000 WHERE id = 2";

        //4.获取执行sql的对象Statement
        Statement stmt = conn.createStatement();

        //5.执行sql
        int count = stmt.executeUpdate(sql);//受影响的行数

        //6.处理结果
        System.out.println(count);

        //7.释放资源
        stmt.close();
        conn.close();

    }
}
