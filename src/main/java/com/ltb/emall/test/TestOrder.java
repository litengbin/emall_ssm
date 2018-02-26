package com.ltb.emall.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * ClassName: TestOrder
 * Description: order测试类
 * User: litengbin
 * Date: 2018/2/17 20:02
 * Version: 1.0.0
 */
public class TestOrder {
    public static void main(String[] args) {
        //准备订单测试数据：
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/emall_ssm?userUnicode=true&characterEncoding=utf8", "root", "123456");
            Statement s = c.createStatement();
            for (int i = 1; i <= 15; i++) {
                String sqlFormat = "insert into order_ values(null,'20180218%d',%d,'广州市天河区中山大道西29%d号','00000%d','1881329979%d','李腾彬%d','备注信息','2018-02-18','2018-02-18',null,null,'waitDelivery')";
                String sql = String.format(sqlFormat, i, i, i, i, i, i);
                s.execute(sql);
            }
            System.out.println("已经成功创建15条订单测试数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
