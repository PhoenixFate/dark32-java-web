package com.phoenix.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import java.util.List;

public class TestDom4j {

    @Test
    public void test1(){
        SAXReader saxReader=new SAXReader();
        try {
            Document document = saxReader.read("src/main/java/com/phoenix/schema/web.xml");
            System.out.println(document);
            Element rootElement = document.getRootElement();
            System.out.println(rootElement);

            List<Element> elements = rootElement.elements();
            System.out.println(elements);
            for(Element element:elements){
                if("servlet".equals(element.getName())){
                    Element element1 = element.element("servlet-name");
                    Element element2 = element.element("servlet-class");
                    System.out.println(element1.getText());
                    System.out.println(element2.getText());
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


}
