package cn.edu.lingnan.util;

import java.util.HashMap;

public class xmltest {
    public static void main(String[] args) {
        String xsdPath = "src//database.conf.xsd";
        String xmlPath = "src//database.conf.xml";
        if (xmlvalidator.validate(xsdPath, xmlPath)) {
            System.out.println("�ɹ�");
        } else System.out.println("ʧ��");

        HashMap<String, String> hm = new HashMap<String, String>();
        hm = XmlParser.parser(xmlPath);
        System.out.println(hm.get("driver"));
        System.out.println(hm.get("url"));
        System.out.println(hm.get("user"));
        System.out.println(hm.get("password"));


    }
}
