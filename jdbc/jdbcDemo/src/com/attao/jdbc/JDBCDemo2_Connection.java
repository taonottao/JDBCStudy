package com.attao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC  API 详解：Connection
 * @version 1.0
 * @Author T-WANG
 * @Date 2023/3/29 22:54
 */
public class JDBCDemo2_Connection {
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
        String sql1 = "UPDATE employee SET salary = 6100 WHERE id = 2";
        String sql2 = "UPDATE employee SET salary = 12000 WHERE id = 3";

        //4.获取执行sql的对象Statement
        Statement stmt = conn.createStatement();



        try {
            //开启事务
            conn.setAutoCommit(false);
            //5.执行sql
            int count1 = stmt.executeUpdate(sql1);//受影响的行数

            //6.处理结果
            System.out.println(count1);
            //int i = 3/0;
            //5.执行sql
            int count2 = stmt.executeUpdate(sql2);//受影响的行数

            //6.处理结果
            System.out.println(count2);

            //提交事务
            conn.commit();
        } catch (Exception e) {
            //回滚事务
            conn.rollback();
            throw new RuntimeException(e);
        }



        //7.释放资源
        stmt.close();
        conn.close();

    }
}
