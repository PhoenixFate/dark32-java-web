package com.phoenix.transfer.web;

import com.phoenix.transfer.service.TransferService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TransferServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //接受转账的参数
        String out = request.getParameter("out");
        String in=request.getParameter("in");
        String moneyString=request.getParameter("money");
        double money=Double.parseDouble(moneyString);

        //调用业务层的转账方法
        TransferService transferService=new TransferService();
        boolean isTransferSuccess = transferService.transfer(out, in, money);
        System.out.println("isTransferSuccess: "+isTransferSuccess);
        if(isTransferSuccess){
            response.getWriter().write("转账成功！");
        }else {
            response.getWriter().write("转账失败！");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
