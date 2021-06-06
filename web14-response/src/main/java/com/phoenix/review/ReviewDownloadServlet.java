package com.phoenix.review;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

public class ReviewDownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String filename=req.getParameter("filename");
        System.out.println("filename: "+filename);
//       设置content-type头
        String mimeType = getServletContext().getMimeType(filename);
        System.out.println("mime type: "+mimeType);
        resp.setHeader("Content-Type",mimeType);
        //2.2 设置Content-Disposition 头
        resp.setHeader("Content-Disposition","attachment;filename="+filename);
        String readPath=getServletContext().getRealPath("/download/"+filename);
        FileInputStream fileInputStream = new FileInputStream(readPath);
        ServletOutputStream outputStream = resp.getOutputStream();
        int len=0;
        byte[] b=new byte[1024];
        while((len=fileInputStream.read(b))!=-1){
            outputStream.write(b,0,len);
        }
        outputStream.close();

//        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



//        super.doPost(req, resp);
    }
}
