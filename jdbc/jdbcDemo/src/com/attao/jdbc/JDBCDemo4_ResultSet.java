package com.attao.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * JDBC  API 详解：ResultSet
 * @version 1.0
 * @Author T-WANG
 * @Date 2023/3/29 22:54
 */
public class JDBCDemo4_ResultSet {
    /**
     * 执行DQL语句
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
        String sql = "select * from employee";

        //4.获取statement对象
        Statement stmt = conn.createStatement();
        //5.执行sql
        ResultSet rs = stmt.executeQuery(sql);
        //6.处理结果，遍历rs中的所有数据
        //6.1光标向下移动一行，并且判断当前行是否有数据
        //方法一
//        while(rs.next()){
//            //6.2获取数据:getXxx()
//            int id = rs.getInt(1);
//            String name = rs.getString(2);
//            Double salary = rs.getDouble(3);
//            String birthday = rs.getString(4);
//            int points = rs.getInt(5);
//            String phone =  rs.getString(6);
//
//            System.out.println(id);
//            System.out.println(name);
//            System.out.println(salary);
//            System.out.println(birthday);
//            System.out.println(points);
//            System.out.println(phone);
//            System.out.println("----------------");
//        }
        //方法二
        while(rs.next()){
            //6.2获取数据:getXxx()
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Double salary = rs.getDouble("salary");
            String birthday = rs.getString("birthday");
            int points = rs.getInt("points");
            String phone =  rs.getString("phone");

            System.out.println(id);
            System.out.println(name);
            System.out.println(salary);
            System.out.println(birthday);
            System.out.println(points);
            System.out.println(phone);
            System.out.println("----------------");
        }

        //7.释放资源
        rs.close();
        stmt.close();
        conn.close();

    }

}
