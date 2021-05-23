package com.phoenix.shop.web.servlet;

import com.phoenix.shop.domain.Product;
import com.phoenix.shop.service.ProductService;
import com.phoenix.shop.utils.CommonsUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class AddProductServlet extends HttpServlet {

    private ProductService productService=new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、创建磁盘文件项工厂
        //作用：设置缓存文件的大小  设置临时文件存储的位置
        String path_temp = this.getServletContext().getRealPath("temp");
        DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024, new File(path_temp));
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        factory.setSizeThreshold(1024*1024);
//        factory.setRepository(new File(path_temp));
        //2、创建文件上传的核心类
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置上传文件的名称的编码
        upload.setHeaderEncoding("UTF-8");

        if(upload.isMultipartContent(req)){//判断表单是否是文件上传的表单
            //是文件上传的表单
            //***解析request获得文件项集合 !!!!!!!!!!!!!!!!!!!!!!1
            List<FileItem> parseRequest = null;
            try {
                parseRequest = upload.parseRequest(req);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            if(parseRequest!=null){
                Product product=new Product();
                Map<String,Object> map=new HashMap<String,Object>();

                for(FileItem item : parseRequest){
                    //判断是不是一个普通表单项
                    boolean formField = item.isFormField();
                    if(formField){
                        //username=zhangsan
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString("UTF-8");
                        map.put(fieldName,fieldValue);
//                        if("pname".equals(fieldName)){
//                            product.setPname(item.getString("UTF-8"));
//                        }else if("is_hot".equals(fieldName)){
//                            product.setIs_hot(Integer.parseInt(item.getString("UTF-8")));
//                        }else if("market_price".equals(fieldName)){
//                            product.setMarket_price(Double.parseDouble(item.getString("UTF-8")));
//                        }else if("shop_price".equals(fieldName)){
//                            product.setShop_price(Double.parseDouble(item.getString("UTF-8")));
//                        }else if("cid".equals(fieldName)){
//                            product.setCid(item.getString("UTF-8"));
//                        } else if("pdesc".equals(fieldName)){
//                            product.setPdesc(item.getString("UTF-8"));
//                        }

                        //当表单是enctype="multipart/form-data(多部分上传)"时 request.getParameter相关的方法都失效！！！！
                        //String parameter = request.getParameter("username");// 该方法不对，返回的都是null
                    }else{
                        //文件上传项
                        String fieldName = item.getFieldName();
                        if("pimage".equals(fieldName)){
                            //文件的名
                            String fileName = item.getName();
                            String[] split = fileName.split(Matcher.quoteReplacement(File.separator));
                            //获得上传文件的内容
                            InputStream in = item.getInputStream();
                            String path_store = this.getServletContext().getRealPath("upload");
                            OutputStream out = new FileOutputStream(path_store+"/"+split[split.length-1]);
                            IOUtils.copy(in, out);
                            in.close();
                            out.close();
                            product.setPimage("upload"+File.separator+split[split.length-1]);
                            //删除临时文件
                            item.delete();
                        }
                    }
                }

                try {
                    System.out.println(product);
                    BeanUtils.populate(product,map);
                    product.setPid(CommonsUtils.getUUID());
                    product.setPdate(new Date());
                    Integer count = productService.addProduct(product);
                    if(count>=1){
                        //更新成功
                        System.out.println("product insert success");
                        resp.sendRedirect(req.getContextPath()+"/productServlet?method=listByPage");
                        return;
                    }else {
                        //更新失败
                        System.out.println("product insert failed");
                        resp.sendRedirect(req.getContextPath()+"/admin/error.jsp");
                        return;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }else{
            //不是文件上传表单
            //使用原始的表单数据的获得方式 request.getParameter();
        }
        resp.sendRedirect(req.getContextPath()+"/productServlet?method=listByPage");

//        super.doPost(req, resp);
    }
}
