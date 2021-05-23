package com.phoenix.shop.web.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;

public class FileuploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        /////////////////////原理
        //服务器端获得上传的文件
        //1.通过request获得请求体的内容

        //2.解析请求体
        // 根据分隔符将请求体的文本内容分割成数组，数组中的每一部分是一个表单项

        //3.遍历数组，分清哪个是普通表单项，哪个是文件上传项

        //4.普通表单项：获得name和name对应的value

        //5. 文件上传项：
        // filename="aaa.txt"
        // 文件的内容
        // 使用io将文件的内容上传到服务器中
        //////////////////////////end

        try {
            // 二，使用api进行上传: apache-commons-fileupload    (依赖commons-io)
            // 1. 创建磁盘文件项工厂
            DiskFileItemFactory diskFileItemFactory=new DiskFileItemFactory();

            // 2. 创建文件上传的核心类
            ServletFileUpload servletFileUpload=new ServletFileUpload(diskFileItemFactory);

            // 3. 解析request 获得文件项集合
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);

            // 4. 遍历文件项集合
            for(FileItem fileItem: fileItems){
                // 5. 判断普通表单项和 文件上传项
                boolean formField = fileItem.isFormField(); //是否是一个普通表单项目  true：普通表单项
                if(formField){
                    // 普通表单项
                    String fieldName = fileItem.getFieldName();
                    String fieldValue = fileItem.getString();
                    System.out.println("fieldName: "+fieldName+" ;     "+fieldValue);
                }else {
                    // 文件上传项
                    // 获得文件名称 --- 表单中的 filename
                    String filename = fileItem.getName();
                    System.out.println("filename:  "+filename);
                    String[] split = filename.split(Matcher.quoteReplacement(File.separator));
                    // 获得文件的内容
                    InputStream inputStream = fileItem.getInputStream();
                    // 将inputStream 中的内容 存储到服务器
                    String path=this.getServletContext().getRealPath("upload");
                    File file=new File(path + "/" + split[split.length-1]);
                    FileOutputStream fileOutputStream = new FileOutputStream(path + "/" + split[split.length-1]);
                    int len=0;
                    byte[] buffer=new byte[1024];
                    while((len=inputStream.read(buffer))>0){
                        fileOutputStream.write(buffer,0,len);
                    }
                    inputStream.close();
                    fileOutputStream.close();
                }
            }

            resp.getWriter().write("upload success");

        } catch (FileUploadException e) {
            e.printStackTrace();
        }
//        super.doPost(req, resp);
    }
}
