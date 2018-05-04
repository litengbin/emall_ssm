package com.ltb.emall.test;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * ClassName: TestPropertyValue
 * Description: PropertyValue测试
 * User: litengbin
 * Date: 2018/2/13 0:05
 * Version: 1.0.0
 */
public class TestPropertyValue {

    public static void main(String[] args) {
        //准备属性值测试数据：
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/emall_ssm?userUnicode=true&characterEncoding=utf8", "root", "123456");
            Statement s = c.createStatement();
            int count = 0;

            for (int j = 1, i = 1; i <= 340; j++) {
                if (j % 10 != 0) {
                    String sqlFormat = "insert into propertyvalue values(null,%d,%d,'测试属性值%d')";
                    String sql = String.format(sqlFormat, j, i, i);
                    s.execute(sql);
                } else {
                    count++;
                    if (count % 20 != 0) {
                        String sqlFormat = "insert into propertyvalue values(null,%d,%d,'测试属性值%d')";
                        String sql = String.format(sqlFormat, j, i, i);
                        s.execute(sql);
                        j = j - 10;
                    } else {
                        String sqlFormat = "insert into propertyvalue values(null,%d,%d,'测试属性值%d')";
                        String sql = String.format(sqlFormat, j, i, i);
                        s.execute(sql);
                    }
                    i++;
                }
            }
            System.out.println("已经成功创建750条产品测试数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update(){
        //准备属性值测试数据：
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/emall_ssm?userUnicode=true&characterEncoding=utf8", "root", "123456");
            PreparedStatement ps = c.prepareStatement("update propertyvalue set value = ? where id = ?");
            int count = 0;
            for (int j = 1; j <= 3670; j++) {
                    ps.setString(1,"测试属性值"+j);
                    ps.setInt(2,j);
                   ps.executeUpdate();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
