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

    //��ȡһ�����ݿ����Ӳ����ظ�������
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

            //1.ע����������
            Class.forName(driver);

            //2.��ȡ���ݿ�����
            conn = DriverManager.getConnection
                    (url, user, password);

        } catch (ClassNotFoundException e) {
            System.out.println("���ݿ���ص�jar�����س�������..");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("��ȡMySQL����ʱ�������ô���....");
            e.printStackTrace();
        }


        return conn;

    }

    //�ر����ݿ�����
    public static void closeConnection(ResultSet rs, Statement stat, Connection conn) {
        try {
            if (rs != null)
                rs.close();
            if (stat != null)
                stat.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.out.println("�ر����ݿ�����ʱ��������");
            e.printStackTrace();
        }

    }

    //�ر����ݿ�����,��ɾ���͸��²������п���û�н����
    public static void closeConnection(PreparedStatement prep, Connection conn) {
        try {
            if (prep != null)
                prep.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.out.println("�ر����ݿ�����ʱ��������");
            e.printStackTrace();
        }

    }

    //�ر����ݿ�����
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
            System.out.println("�ر����ݿ�����ʱ��������");
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
            System.out.println("�ر����ݿ�ʱ���ֵ�����");
            e.printStackTrace();
        }
    }
}
