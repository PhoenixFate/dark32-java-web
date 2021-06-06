package com.phoenix.content;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //使用response获得字节输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //获取服务器上的图片
        //获得某个资源的绝对路径
        String realPath = this.getServletContext().getRealPath("p.jpg");
        InputStream in=new FileInputStream(realPath);
        int len=0;
        byte[] buffer=new byte[1024];
        while((len=in.read(buffer))>0){
            outputStream.write(buffer,0,len);
        }
        in.close();;
        outputStream.close();;
    }
}
