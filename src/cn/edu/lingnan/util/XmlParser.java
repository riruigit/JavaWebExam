package cn.edu.lingnan.util;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class XmlParser {
    public static HashMap<String, String> parser(String xmlPath) {
        HashMap<String, String> hm = new HashMap<String, String>();
        try {
            //1.实例化一个SAXParseFactory对象
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //2.通过factory获得一个SAXParser对象，即SAX解析器
            SAXParser saxParser = factory.newSAXParser();
            //3.saxParser对象调用parse方法解析xml文件
            File f = new File(xmlPath);//事件源,p70的第十七行改成这个
            XmlHandler xh = new XmlHandler();//事件处理器
            saxParser.parse(f, xh);
            hm = xh.getHashMap();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return hm;
    }
}