package com.phoenix.item.freemarker;

import com.phoenix.item.dto.StudentTest;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class FreemarkerTest {

    @Test
    public void testHello() throws IOException, TemplateException {
        //1。创建一个模板文件
        //webapp/WEB-INF/ftl/hello.ftl
        //2。创建一个Configuration对象
        Configuration configuration=new Configuration(Configuration.getVersion());
        //3。设置模板文件保存到目录
        configuration.setDirectoryForTemplateLoading(new File("src/main/webapp/WEB-INF/ftl"));

        //4。设置模板文件到编码格式 utf-8
        configuration.setDefaultEncoding("utf-8");
        //5。通过Configuration加载一个模板文件，创建一个模板对象
        Template template = configuration.getTemplate("hello.ftl");
        //6。创建一个数据集，可以是pojo，也可以是map，推荐使用map
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("hello","hello freemarker");
        //7。创建一个writer对象，指定输出文件到路径以及文件名
        Writer writer=new FileWriter(new File("src/main/webapp/temp/hello.txt"));
        //8。生成静态页面
        template.process(map,writer);
        //9。关闭各种流
        writer.close();
    }

    @Test
    public void testStudent() throws IOException, TemplateException {
        //1。创建一个模板文件
        //webapp/WEB-INF/ftl/hello.ftl
        //2。创建一个Configuration对象
        Configuration configuration=new Configuration(Configuration.getVersion());
        //3。设置模板文件保存到目录
        configuration.setDirectoryForTemplateLoading(new File("src/main/webapp/WEB-INF/ftl"));
        //4。设置模板文件到编码格式 utf-8
        configuration.setDefaultEncoding("utf-8");
        //5。通过Configuration加载一个模板文件，创建一个模板对象
        Template template = configuration.getTemplate("student.ftl");
        //6。创建一个数据集，可以是pojo，也可以是map，推荐使用map
        Map<String,Object> map=new HashMap<String,Object>();
        StudentTest studentTest=new StudentTest(1,"萧瑟",18,"南京鼓楼");
        map.put("hello","hello freemarker");
        map.put("student",studentTest);

        //7。创建一个writer对象，指定输出文件到路径以及文件名
        Writer writer=new FileWriter(new File("src/main/webapp/temp/student.html"));
        //8。生成静态页面
        template.process(map,writer);
        //9。关闭各种流
        writer.close();
    }

    @Test
    public void testStudentList() throws IOException, TemplateException {
        //1。创建一个模板文件
        //webapp/WEB-INF/ftl/hello.ftl
        //2。创建一个Configuration对象
        Configuration configuration=new Configuration(Configuration.getVersion());
        //3。设置模板文件保存到目录
        configuration.setDirectoryForTemplateLoading(new File("src/main/webapp/WEB-INF/ftl"));
        //4。设置模板文件到编码格式 utf-8
        configuration.setDefaultEncoding("utf-8");
        //5。通过Configuration加载一个模板文件，创建一个模板对象
        Template template = configuration.getTemplate("studentList.ftl");
        //6。创建一个数据集，可以是pojo，也可以是map，推荐使用map
        Map<String,Object> map= new HashMap<>();
        StudentTest studentTest1=new StudentTest(123,"萧瑟",18,"南京鼓楼");
        StudentTest studentTest2=new StudentTest(254,"色弱2",20,"南京玄武");
        StudentTest studentTest3=new StudentTest(323,"微软3",24,"南京建邺");
        StudentTest studentTest4=new StudentTest(467,"顾问4",28,"南京新街口");

        map.put("hello","hello freemarker");
        List<StudentTest> studentList =new ArrayList<>();
        studentList.add(studentTest1);
        studentList.add(studentTest2);
        studentList.add(studentTest3);
        studentList.add(studentTest4);
        map.put("studentList",studentList);

        //日期类型测试
        map.put("today",new Date());
        //空值测试
        map.put("myNull",null);

        //7。创建一个writer对象，指定输出文件到路径以及文件名
        Writer writer=new FileWriter(new File("src/main/webapp/temp/studentList.html"));
        //8。生成静态页面
        template.process(map,writer);
        //9。关闭各种流
        writer.close();
    }


}
