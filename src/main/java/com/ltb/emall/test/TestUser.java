package com.ltb.emall.test;

import com.ltb.emall.util.RandomStringUtil;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;

/**
 * ClassName: TestUser
 * Description: user测试类
 * User: litengbin
 * Date: 2018/2/17 20:02
 * Version: 1.0.0
 */
public class TestUser {
    @Test
    public static void main(String[] args) {
        //准备用户测试数据：
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/emall_ssm?userUnicode=true&characterEncoding=utf8", "root", "123456");
            Statement s = c.createStatement();
            for (int i = 1; i <= 50; i++) {

                String sqlFormat = "insert into user values(null,'" + RandomStringUtil.getRandomCode(5,5) +"','"+ RandomStringUtil.getRandomCode(8,6)+ "')";
//                String sql = String.format(sqlFormat, i, i);
                s.execute(sqlFormat);
            }
            System.out.println("已经成功创建50条用户测试数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
