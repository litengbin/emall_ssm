package com.ltb.emall.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * ClassName: TestReview
 * Description: review测试
 * User: litengbin
 * Date: 2018/2/20 11:05
 * Version: 1.0.0
 */
public class TestReview {
    public static void main(String[] args) {
        //准备评价测试数据：
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/emall_ssm?userUnicode=true&characterEncoding=utf8", "root", "123456");
            Statement s = c.createStatement();
            for (int i = 1; i <= 15; i++) {
                for (int j = 1; j <= 75; j++) {
                    String sqlFormat = "insert into review values(null,'测试评价%d',%d,%d,'2018-02-20')";
                    String sql = String.format(sqlFormat, j, i, j);
                    s.execute(sql);
                }
            }
            System.out.println("已经成功创建1125条产品评价测试数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
