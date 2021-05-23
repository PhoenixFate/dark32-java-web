package com.phoenix.shop.web.servlet;

import com.phoenix.shop.domain.Order;
import com.phoenix.shop.domain.OrderItem;
import com.phoenix.shop.domain.User;
import com.phoenix.shop.service.OrderService;
import com.phoenix.shop.utils.CommonsUtils;
import com.phoenix.shop.utils.PaymentUtil;
import com.phoenix.shop.vo.Cart;
import com.phoenix.shop.vo.CartItem;
import com.phoenix.shop.vo.PageBean;
import com.phoenix.shop.web.base.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

public class OrderServlet extends BaseServlet {

    private OrderService orderService=new OrderService();

    public void applyOrder(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        // 判断用户是否登陆
        User user = (User) session.getAttribute("user");

        //privilegeFilter do this;
//        if(user==null){
//            // 没有登陆
//            try {
//                response.sendRedirect(request.getContextPath()+"/userServlet?method=loginUI");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return;
//        }
        Cart cart = (Cart)session.getAttribute("cart");
        if(cart!=null){
            // 封装成一个Order对象，并传递给service层
            Order order=new Order();
            order.setOid(CommonsUtils.getUUID());
            order.setOrdertime(new Date());
            order.setTotal(cart.getTotalMoney());
            order.setState(0);
            order.setUser(user);
            Map<String, CartItem> cartItems = cart.getCartItems();
            List<OrderItem>  orderItemList=new ArrayList<>();
            for(Map.Entry<String, CartItem> entry:cartItems.entrySet()){
                OrderItem orderItem=new OrderItem();
                orderItem.setItemid(CommonsUtils.getUUID());
                orderItem.setCount(entry.getValue().getCount());
                orderItem.setOrder(order);
                orderItem.setProduct(entry.getValue().getProduct());
                orderItem.setSubtotal(entry.getValue().getSubTotal());
                orderItemList.add(orderItem);
            }
            order.setOrderItems(orderItemList);
            System.out.println(order);
            //传递数据到serivce层
            orderService.submit(order);
            // 将order存到session中
            request.setAttribute("order",order);
            // session中到购物车相关数据应该清除
            session.removeAttribute("cart");
            try {
                request.getRequestDispatcher("/jsp/order_info.jsp").forward(request,response);
                return;
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                response.sendRedirect(request.getContextPath()+"/indexServlet");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 更新收货人信息 + 在线支付
     * @param request
     * @param response
     */
    public void confirmOrder(HttpServletRequest request, HttpServletResponse response){
        //1. 更新数据
        Order order=new Order();
        try {
            BeanUtils.populate(order,request.getParameterMap());
            Integer count = orderService.updateOrder(order);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //2，在线支付
        //获得银行名称
        String bank = request.getParameter("pd_FrpId");

//        switch (bank){
//            case BankConstant.ABC :
//                break;
//        }
        // 只接入一个接口，该接口已经集成大部分银行到接口，这个接口由第三方提供

        // 易宝支付平台
        // 获得 支付必须基本数据
        String orderid = order.getOid();
        String money = "0.01";
        // 银行
        String pd_FrpId = request.getParameter("pd_FrpId");
        // 发给支付公司需要哪些数据
        String p0_Cmd = "Buy";
        String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
        String p2_Order = orderid;
        String p3_Amt = money;
        String p4_Cur = "CNY";
        String p5_Pid = "";
        String p6_Pcat = "";
        String p7_Pdesc = "";
        // 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
        // 第三方支付可以访问网址
        String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("callback");
        String p9_SAF = "";
        String pa_MP = "";
        String pr_NeedResponse = "1";
        // 加密hmac 需要密钥
        String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
                "keyValue");
        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
                p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
                pd_FrpId, pr_NeedResponse, keyValue);

        String url = "https://www.yeepay.com/app-merchant-proxy/node?pd_FrpId="+pd_FrpId+
                "&p0_Cmd="+p0_Cmd+
                "&p1_MerId="+p1_MerId+
                "&p2_Order="+p2_Order+
                "&p3_Amt="+p3_Amt+
                "&p4_Cur="+p4_Cur+
                "&p5_Pid="+p5_Pid+
                "&p6_Pcat="+p6_Pcat+
                "&p7_Pdesc="+p7_Pdesc+
                "&p8_Url="+p8_Url+
                "&p9_SAF="+p9_SAF+
                "&pa_MP="+pa_MP+
                "&pr_NeedResponse="+pr_NeedResponse+
                "&hmac="+hmac;
        //重定向到第三方支付平台
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findByPage(HttpServletRequest request, HttpServletResponse response){
        // 是否登录
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        //privilegeFilter do this;
//        if(user==null){
//            try {
//                response.sendRedirect(request.getContextPath()+"/userServlet?method=loginUI");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        String pageNumberStr=request.getParameter("pageNumber");
        Integer pageNumber=1;
        if(pageNumberStr!=null){
            pageNumber=Integer.parseInt(pageNumberStr);
        }
        Integer pageSize=3;
        try {
            PageBean<Order> pageBean = orderService.findByPage(user.getUid(),pageNumber,pageSize);
            System.out.println(pageBean);
            request.setAttribute("pageBean",pageBean);
            request.getRequestDispatcher("jsp/order_list.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
