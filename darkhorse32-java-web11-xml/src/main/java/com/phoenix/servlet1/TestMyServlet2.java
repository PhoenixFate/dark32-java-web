package com.phoenix.servlet1;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMyServlet2 {

    private Map<String,String> data=new HashMap<>();

    @Before
    public void test(){
        SAXReader saxReader=new SAXReader();
        try {
            Document document = saxReader.read("src/com/phoenix/servlet1/web.xml");
            Element rootElement =  document.getRootElement();
            List<Element> elements = rootElement.elements();
            for(Element element:elements){
                if("servlet".equals(element.getName())){
                    System.out.println("---------");
                    String servletName=element.element("servlet-name").getText();
                    String servletClass=element.element("servlet-class").getText();
                    data.put(servletName,servletClass);
                }
                if("servlet-mapping".equals(element.getName())){
                    String servletName=element.element("servlet-name").getText();
                    String url=element.element("url-pattern").getText();
//                    data.put(servletName,url);
                    data.put(url,servletName);
                }
            }
            System.out.println(data);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        String url="/myServlet2";
        String className=data.get(data.get(url));
        System.out.println(className);
        try {
            Class<MyServlet2> aClass = (Class<MyServlet2>)Class.forName(className);
            Object o = aClass.newInstance();
            Method method = aClass.getMethod("service");
            method.invoke(o);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
