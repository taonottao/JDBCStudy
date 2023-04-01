package com.attao.jdbc;

import org.junit.Test;

import java.sql.*;

/**
 * API详解：PreparedStatement
 * @version 1.0
 * @Author T-WANG
 * @Date 2023/3/29 22:54
 */
public class JDBCDemo5_PreparedStatement {

    @Test
    public void testPreparedStatement() throws Exception {
        //2.获取连接:如果连接的是本机mysql并且端口是默认的3306，可以简化书写
        //String url = "jdbc:mysql://127.0.0.1:3306/test";
        String url = "jdbc:mysql:///test?useSSL=false";
        String username = "root";
        String password = "wangtao";
        Connection conn = DriverManager.getConnection(url, username, password);

        //接受用户输入用户名和密码
        String name = "zhangsan";
        String pwd = "'or ' 1 ' = ' 1";

        //定义sql
        String sql = "select * from tb_user where username = ? and password = ?";

        //获取PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //设置?的值
        pstmt.setString(1, name);
        pstmt.setString(2, pwd);

        //执行sql
        ResultSet rs = pstmt.executeQuery();

        //判断登录是否成功
        if(rs.next()){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }

        //7.释放资源
        rs.close();
        pstmt.close();
        conn.close();

    }


    /**
     * 演示sql注入
     * @throws Exception
     */
    @Test
    public void testLogin_Inject() throws Exception {
        //2.获取连接:如果连接的是本机mysql并且端口是默认的3306，可以简化书写
        //String url = "jdbc:mysql://127.0.0.1:3306/test";
        String url = "jdbc:mysql:///test?useSSL=false";
        String username = "root";
        String password = "wangtao";
        Connection conn = DriverManager.getConnection(url, username, password);

        //接受用户输入用户名和密码
        String name = "dfaefetgae";
        String pwd = " 'or ' 1 ' = ' 1 ";

        String sql = "select * from tb_user where username = '"+name+"' and password = '"+pwd+"'";
        System.out.println(sql);

        //获取statement对象
        Statement stmt = conn.createStatement();

        //执行sql
        ResultSet rs = stmt.executeQuery(sql);

        //判断登录是否成功
        if(rs.next()){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }

        //7.释放资源
        rs.close();
        stmt.close();
        conn.close();

    }

    /**
     * PreparedStatement原理
     * @throws Exception
     */
    @Test
    public void testPreparedStatement2() throws Exception {
        //2.获取连接:如果连接的是本机mysql并且端口是默认的3306，可以简化书写
        //String url = "jdbc:mysql://127.0.0.1:3306/test";
        //useServerPrepStmts=true 参数开启预编译功能
        String url = "jdbc:mysql:///test?useSSL=false&useServerPrepStmts=true";
        String username = "root";
        String password = "wangtao";
        Connection conn = DriverManager.getConnection(url, username, password);

        //接受用户输入用户名和密码
        String name = "zhangsan";
        String pwd = "'or ' 1 ' = ' 1";

        //定义sql
        String sql = "select * from tb_user where username = ? and password = ?";

        //获取PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //设置?的值
        pstmt.setString(1, name);
        pstmt.setString(2, pwd);

        ResultSet rs = null;
        //执行sql
        rs = pstmt.executeQuery();

        //设置?的值
        pstmt.setString(1, "aaa");
        pstmt.setString(2, "bbb");

        //执行sql
        rs = pstmt.executeQuery();

        //判断登录是否成功
        if(rs.next()){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }

        //7.释放资源
        rs.close();
        pstmt.close();
        conn.close();

    }


}
