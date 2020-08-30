package com.phoenix.content;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得要下载文件的名称
        String filename=request.getParameter("filename");
        System.out.println(this.getServletContext().getMimeType(filename));

        //告诉客户端要下载文件的类型--客户端通过文件的MIME类型去区分类型
        response.setContentType(this.getServletContext().getMimeType(filename));

        //告诉客户端，该文件不解析，而是以附件形式打开（即：下载）
        response.setHeader("Content-Disposition","attachment;filename="+filename);



        //获得filename资源的绝对路径
        String realPath = this.getServletContext().getRealPath("download/"+filename);
        //获得该文件的输入路径
        InputStream in=new FileInputStream(realPath);

        //获得输出流，用于向客户端输出内容
        ServletOutputStream outputStream = response.getOutputStream();
        //文件拷贝的模板代码
        int len=0;
        byte[] buffer=new byte[1024];
        while((len=in.read(buffer))>0){
            outputStream.write(buffer,0,len);
        }
        //关闭资源
        in.close();;
        //out.close()可以不写，web容器会自动关闭
        //outputStream.close();;
    }
}
