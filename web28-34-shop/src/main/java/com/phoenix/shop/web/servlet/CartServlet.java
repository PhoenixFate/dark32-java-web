package com.phoenix.shop.web.servlet;

import com.phoenix.shop.domain.Product;
import com.phoenix.shop.service.ProductService;
import com.phoenix.shop.vo.Cart;
import com.phoenix.shop.vo.CartItem;
import com.phoenix.shop.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {


    private ProductService productService=new ProductService();

    public String cartUI(HttpServletRequest request, HttpServletResponse response){

        return "/jsp/cart.jsp";
    }

    public void addToCart(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        try {
            String pid = request.getParameter("pid");
            Product product = productService.findById(pid);
            String count=request.getParameter("count");
            if(cart!=null){
                CartItem cartItem = cart.getCartItems().get(pid);
                if(cartItem!=null){
                    // 该商品已经存在，直接该数量
                    cartItem.setSubTotal((cartItem.getCount()+Integer.parseInt(count))*cartItem.getProduct().getShop_price());
                    cartItem.setCount(cartItem.getCount()+Integer.parseInt(count));
                    Map<String, CartItem> cartItems = cart.getCartItems();
                    cartItems.replace(pid,cartItem);
                    cart.setTotalMoney(cart.getTotalMoney()+cartItem.getSubTotal());
                    cart.setCartItems(cartItems);

                }else {
                    cartItem=new CartItem();
                    cartItem.setCount(Integer.parseInt(count));
                    cartItem.setProduct(product);
                    cartItem.setSubTotal(product.getShop_price()*(Integer.parseInt(count)));
                    Map<String, CartItem> cartItems = cart.getCartItems();
                    cartItems.put(pid,cartItem);
                    cart.setTotalMoney(cart.getTotalMoney()+cartItem.getSubTotal());
                    cart.setCartItems(cartItems);
                }

            }else {
                cart=new Cart();
                Map<String, CartItem> cartItems=new HashMap<>();
                CartItem cartItem=new CartItem();
                cartItem.setProduct(product);
                cartItem.setCount(Integer.parseInt(count));
                cartItem.setSubTotal(product.getShop_price()*Integer.parseInt(count));
                cartItems.put(pid,cartItem);
                cart.setCartItems(cartItems);
                cart.setTotalMoney(cartItem.getSubTotal());
            }
            System.out.println(cart);
            session.setAttribute("cart",cart);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 这里不能用转发 ，因为转发地址不变，会导致刷新一下页面，重新又添加购物车
//        return "/jsp/cart.jsp";
        try {
            response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void deleteCartItem(HttpServletRequest request, HttpServletResponse response){
        String pid=request.getParameter("pid");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        // 需要判断session是否为空，因为session可能会过期
        if(cart!=null){
            Map<String, CartItem> cartItems = cart.getCartItems();
            CartItem cartItem = cartItems.get(pid);
            cartItems.remove(pid);
            cart.setCartItems(cartItems);
            cart.setTotalMoney(cart.getTotalMoney()-cartItem.getSubTotal());
            session.setAttribute("cart",cart);
        }
        try {
            response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearCart(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("cart");
        try {
            response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
