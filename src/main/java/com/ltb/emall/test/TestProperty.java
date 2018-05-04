package com.ltb.emall.test;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * ClassName: TestProperty
 * Description: property测试类
 * User: litengbin
 * Date: 2018/2/15 20:25
 * Version: 1.0.0
 */
public class TestProperty {
    @Test
    public static void main(String[] args) {
        //准备属性测试数据：
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/emall_ssm?userUnicode=true&characterEncoding=utf8", "root", "123456");
            Statement s = c.createStatement();
            for (int i = 1; i <= 17; i++) {
                for (int j = 1; j <= 10; j++) {
                    String sqlFormat = "insert into property values(null,%d,'测试属性%d')";
                    String sql = String.format(sqlFormat, i, j);
                    s.execute(sql);
                }
            }
            System.out.println("已经成功创建170条属性测试数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
