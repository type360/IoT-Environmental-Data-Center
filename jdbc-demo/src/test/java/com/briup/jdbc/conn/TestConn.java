package com.briup.jdbc.conn;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @Auther: if
 * @Date:
 * @Description：使用java连接数据库
 */
public class TestConn {
    @Test
    public void conn() throws Exception {
        // 1.注册驱动 [可以省略] com.mysql.cj.jdbc.Driver
//        DriverManager.registerDriver(new Driver());
        Class.forName("com.mysql.cj.jdbc.Driver");//这里运用了反射，好处是这里参数写的是字符串没有明显的类导入。反射机制是只有在运行的时候加载驱动类
        // 2.获取连接 http://www.baidu.com
        String url = "jdbc:mysql://localhost:3306/briup"; // 参考idea图形化连接
        String username = "root";
        String password = "root";
        // 左边是接口 右边是mysql提供的实现类
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println("conn = " + conn);
        // 3.获取执行器
        String sql = "insert into dept(id, name) values(100,'销售')";
        Statement stmt = conn.createStatement();
        // 4.执行sql
        stmt.execute(sql); // ddl/dml boolean
//        stmt.executeUpdate(sql); // ddl/dml int
//        stmt.executeQuery(sql); // 查询
        // 5.处理结果集
        // 6.关闭资源
        stmt.close();
        conn.close();
    }
}
