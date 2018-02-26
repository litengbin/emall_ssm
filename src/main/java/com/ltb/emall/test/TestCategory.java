package com.ltb.emall.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * ClassName: TestCategory
 * Description: category测试
 * User: litengbin
 * Date: 2018/2/13 0:05
 * Version: 1.0.0
 */
public class TestCategory {
    public static void main(String[] args) {
        //准备分类测试数据：
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/emall_ssm?userUnicode=true&characterEncoding=utf8", "root", "123456");
            Statement s = c.createStatement();
            for (int i = 1; i <= 15; i++) {
                String sqlFormat = "insert into category values(null,'测试分类%d')";
                String sql= String.format(sqlFormat,i);
                s.execute(sql);
            }
            System.out.println("已经成功创建15条分类测试数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
