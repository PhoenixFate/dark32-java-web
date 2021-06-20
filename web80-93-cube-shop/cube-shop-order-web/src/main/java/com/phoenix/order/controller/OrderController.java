package com.phoenix.order.controller;

import com.phoenix.cart.service.CartService;
import com.phoenix.item.pojo.Item;
import com.phoenix.order.pojo.OrderInfo;
import com.phoenix.order.service.OrderService;
import com.phoenix.sso.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private CartService cartService;

    @Value("${SEARCH_ADDRESS}")
    private String SEARCH_ADDRESS;

    @Value("${SSO_ADDRESS}")
    private String SSO_ADDRESS;

    @Value("${PORTAL_ADDRESS}")
    private String PORTAL_ADDRESS;

    @Value("${CART_ADDRESS}")
    private String CART_ADDRESS;

    @Value("${ORDER_ADDRESS}")
    private String ORDER_ADDRESS;

    @Autowired
    private OrderService orderService;

    @RequestMapping("order-cart")
    public String showOrderCart(HttpServletRequest request, Model model){
        User user =  (User)request.getAttribute("user");
        //根据用户id，获取收货地址列表

        //获得支付方式列表

        //根据用户id从redis中获得购物车列表
        List<Item> itemList = cartService.getItemListByUserId(2L);
        //把购物车列表传递给页面
        request.setAttribute("cartList",itemList);
        model.addAttribute("searchAddress",SEARCH_ADDRESS);
        model.addAttribute("ssoAddress",SSO_ADDRESS);
        model.addAttribute("registerAddress",SSO_ADDRESS+"/user/register/page");
        model.addAttribute("portalAddress",PORTAL_ADDRESS);
        model.addAttribute("cartAddress",CART_ADDRESS);
        model.addAttribute("orderAddress",ORDER_ADDRESS);
        return "order-cart";
    }

    @RequestMapping(value = "create",method = RequestMethod.POST)
    public String createOrder(OrderInfo orderInfo,HttpServletRequest request,Model model){
        //取用户信息
        User user = (User) request.getAttribute("user");
        //把用户信息添加到OrderInfo中
        orderInfo.setUserId(user.getId());
        orderInfo.setBuyerNick(user.getUsername());
        //调用服务生成订单
        String orderId = orderService.createOrder(orderInfo);
        //如果生成成功，删除购物车数据
        cartService.clearCart(user.getId());
        //把订单号传递给页面
        request.setAttribute("orderId",orderId);
        request.setAttribute("payment",orderInfo.getPayment());


        model.addAttribute("searchAddress",SEARCH_ADDRESS);
        model.addAttribute("ssoAddress",SSO_ADDRESS);
        model.addAttribute("registerAddress",SSO_ADDRESS+"/user/register/page");
        model.addAttribute("portalAddress",PORTAL_ADDRESS);
        model.addAttribute("cartAddress",CART_ADDRESS);
        //返回逻辑视图
        return "success";
    }

}
