package cn.edu.lingnan.dao;

import cn.edu.lingnan.util.XmlParser;
import cn.edu.lingnan.util.xmlvalidator;


import java.sql.*;
import java.util.HashMap;

public class DataAccess {
    public static String driver = null;
    public static String url = null;
    public static String user = null;
    public static String password = null;
    public static String xsdPath = "database.conf.xsd";
    public static String xmlPath = "database.conf.xml";
    private static xmlvalidator XmlValidator;

    static {
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        xsdPath = basePath + xsdPath;
        xmlPath = basePath + xmlPath;

    }

    //获取一个数据库连接并返回给调用者
    public static Connection getConnection() {
        Connection conn = null;
        if (XmlValidator.validate(xsdPath, xmlPath)) {
            HashMap<String, String> hm = XmlParser.parser(xmlPath);
            driver = hm.get("driver");
            url = hm.get("url");
            user = hm.get("user");
            password = hm.get("password");
        }
        try {

            //1.注册驱动程序
            Class.forName(driver);

            //2.获取数据库连接
            conn = DriverManager.getConnection
                    (url, user, password);

        } catch (ClassNotFoundException e) {
            System.out.println("数据库加载的jar包加载出现问题..");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("获取MySQL连接时参数设置错误....");
            e.printStackTrace();
        }


        return conn;

    }

    //关闭数据库连接
    public static void closeConnection(ResultSet rs, Statement stat, Connection conn) {
        try {
            if (rs != null)
                rs.close();
            if (stat != null)
                stat.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.out.println("关闭数据库连接时出现问题");
            e.printStackTrace();
        }

    }

    //关闭数据库连接,在删除和更新操作中有可能没有结果集
    public static void closeConnection(PreparedStatement prep, Connection conn) {
        try {
            if (prep != null)
                prep.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.out.println("关闭数据库连接时出现问题");
            e.printStackTrace();
        }

    }

    //关闭数据库连接
    public static void closeConnection(ResultSet rs, ResultSet rs1, PreparedStatement prep, Connection conn) {
        try {
            if (rs != null)
                rs.close();
            if (rs1 != null)
                rs1.close();
            if (prep != null)
                prep.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.out.println("关闭数据库连接时出现问题");
            e.printStackTrace();
        }

    }


    public static void closeConnection(ResultSet rs1, ResultSet rs2, ResultSet rs3, PreparedStatement prep, Connection conn) {
        try {
            if (rs1 != null)
                rs1.close();
            if (rs2 != null)
                rs2.close();
            if (rs3 != null)
                rs3.close();
            if (prep != null)
                prep.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.out.println("关闭数据库时出现的问题");
            e.printStackTrace();
        }
    }
}
