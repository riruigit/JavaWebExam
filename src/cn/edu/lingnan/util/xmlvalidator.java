package cn.edu.lingnan.util;

import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class xmlvalidator {

    public static boolean validate(String xsdPath, String xmlPath) {
        boolean flag = false;
        SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File f = new File(xsdPath);
        try {
            Schema s = sf.newSchema(f);
            Validator v = s.newValidator();
            Source sc = new StreamSource(xmlPath);
            v.validate(sc);
            flag = true;
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return flag;
    }


}
