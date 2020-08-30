package com.phoenix.content;

import org.apache.commons.codec.binary.Base64;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class DownloadServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决中文乱码
        //文件名称是中文的，下载

        //获得要下载文件的名称
        String filename=request.getParameter("filename");
        System.out.println(filename);
//        filename=new String(filename.getBytes("ISO8859-1"),"UTF-8");
        String filenameEncoder=filename;
        System.out.println(filename);
        System.out.println("看一下能否打中文？？？");
        System.out.println(this.getServletContext().getMimeType(filename));

        //告诉客户端要下载文件的类型--客户端通过文件的MIME类型去区分类型
        response.setContentType(this.getServletContext().getMimeType(filename));

        //获取请求中的user-agent
        String agent = request.getHeader("User-Agent");
        //根据不同的客户端，进行不同的编码
        System.out.println("User-Agent: "+agent);
        if(agent.contains("MSIE")){
            //IE浏览器
            System.out.println("ie 浏览器");
            filenameEncoder= URLEncoder.encode(filename,"UTF-8");
            filenameEncoder=filenameEncoder.replace("+"," ");
            response.setHeader("Content-Disposition","attachment;filename="+filenameEncoder);
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            System.out.println("Firefox 浏览器");
            Base64 base64=new Base64();
            filenameEncoder = "=?utf-8?B?"
                    + Arrays.toString(base64.encode(filename.getBytes(StandardCharsets.UTF_8))) + "?=";
            response.setHeader("Content-Disposition","attachment;filename="+filenameEncoder);
        } else if (agent.contains("Safari")){
            System.out.println("Safari 浏览器");
            response.setHeader("content-disposition","attachment;filename*=UTF-8''" + URLEncoder.encode(filename,"UTF-8"));
        }
        else {
            // 其它浏览器
            System.out.println("其他 浏览器");
            filenameEncoder = URLEncoder.encode(filename, "utf-8");
            response.setHeader("Content-Disposition","attachment;filename="+filenameEncoder);
        }
        System.out.println("filenameEncoder: "+filenameEncoder);


        //告诉客户端，该文件不解析，而是以附件形式打开（即：下载）




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
