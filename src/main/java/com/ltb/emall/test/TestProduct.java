package com.ltb.emall.test;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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
            for (int i = 1; i <= 17; i++) {
                for (int j = 1; j <= 20; j++) {
                    String sqlFormat = "insert into product values(null,'测试产品%d','测试标题%d',%f,%f,%d,'%s',%d)";
                    String sql = String.format(sqlFormat, j, j, 10.00, 8.75, 100, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime() - RandomUtils.nextInt(1000*60*60*24*100)), i);
//                    System.out.println(sql);
                    s.execute(sql);
                }
            }
            System.out.println("已经成功创建340条产品测试数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void set(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/emall_ssm?userUnicode=true&characterEncoding=utf8", "root", "123456");
            PreparedStatement ps = c.prepareStatement("update product set initialPrice=?,promotePrice=?,inventory=?,name=?,title=? where id = ?");
            for (int i = 1; i <= 340; i++) {
                Random r = new Random();
                ps.setFloat(1,r.nextFloat()*1000);
                ps.setFloat(2,r.nextFloat()*100);
                ps.setInt(3,r.nextInt(1000));
                ps.setString(4,"测试商品"+i);
                ps.setString(5,"测试标题最最热门商品"+i);
                ps.setInt(6,i);
//                    String sql = String.format(sqlFormat, j, j, 10.00, 8.75, 100, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime() - RandomUtils.nextInt(1000*60*60*24*100)), i);
//                    System.out.println(sql);
//                    s.execute(sql);
                ps.executeUpdate();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setPrice(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/emall_ssm?userUnicode=true&characterEncoding=utf8", "root", "123456");
            PreparedStatement ps = c.prepareStatement("update product set initialPrice=?,promotePrice=? where id = ?");
            for (int i = 1; i <= 340; i++) {
                Random r = new Random();
                DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                String p=decimalFormat.format(r.nextFloat()*900+100);//format 返回的是字符串
                ps.setFloat(1,Float.parseFloat(p));
                String p1=decimalFormat.format(r.nextFloat()*100);//format 返回的是字符串
                ps.setFloat(2,Float.parseFloat(p1));
                ps.setInt(3,i);
//                    String sql = String.format(sqlFormat, j, j, 10.00, 8.75, 100, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime() - RandomUtils.nextInt(1000*60*60*24*100)), i);
//                    System.out.println(sql);
//                    s.execute(sql);
                ps.executeUpdate();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
