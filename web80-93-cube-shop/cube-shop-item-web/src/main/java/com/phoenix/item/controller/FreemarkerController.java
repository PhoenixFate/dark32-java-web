package com.phoenix.item.controller;

import com.phoenix.item.dto.StudentTest;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

/**
 * 生成静态页面测试controller
 */
@Controller
@RequestMapping("freemarker")
public class FreemarkerController {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @RequestMapping("/student")
    @ResponseBody
    public String getStudent() throws IOException, TemplateException {
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        //加载模板
        Template template = configuration.getTemplate("studentList.ftl");
        //创建一个数据集
        Map<String,Object> map=new HashMap<>();
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
        map.put("today",new Date());
        map.put("myNull",null);

        //指定文件输出路径及文件名
        Writer writer=new FileWriter(new File("src/main/webapp/temp/studentList2.html"));
        //输出文件
        template.process(map,writer);
        //关闭流
        writer.close();
        return "ok";
    }
}
