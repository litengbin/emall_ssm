package com.ltb.emall.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * ClassName: TestOrderItem
 * Description: orderitem测试类
 * User: litengbin
 * Date: 2018/2/17 23:49
 * Version: 1.0.0
 */
public class TestOrderItem {
    public static void main(String[] args) {
        //准备订单项测试数据：
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/emall_ssm?userUnicode=true&characterEncoding=utf8", "root", "123456");
            Statement s = c.createStatement();
            for (int i = 1; i <= 15; i++) {
                String sqlFormat = "insert into orderitem values(null,%d,%d,%d,%d)";
                String sql = String.format(sqlFormat, i, i, i, i);
                s.execute(sql);
            }
            System.out.println("已经成功创建15条订单项测试数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
