package com.ltb.emall.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

/**
 * ClassName: TestProduct
 * Description: Product测试
 * User: litengbin
 * Date: 2018/2/13 0:05
 * Version: 1.0.0
 */
public class TestProduct {
    public static void main(String[] args) {
        //准备产品测试数据：
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/emall_ssm?userUnicode=true&characterEncoding=utf8", "root", "123456");
            Statement s = c.createStatement();
            for (int i = 1; i <= 15; i++) {
                for (int j = 1; j <= 5; j++) {
                    String sqlFormat = "insert into product values(null,'测试产品%d','测试标题%d',%f,%f,%d,'2018-02-18',%d)";
                    String sql = String.format(sqlFormat, j, j, 10.00, 8.75, 100, i);
                    s.execute(sql);
                }
            }
            System.out.println("已经成功创建75条产品测试数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
