package com.phoenix.cart.controller;

import com.phoenix.cart.service.CartService;
import com.phoenix.common.utils.CommonResult;
import com.phoenix.common.utils.CookieUtils;
import com.phoenix.common.utils.JsonUtils;
import com.phoenix.item.pojo.Item;
import com.phoenix.item.service.ItemService;
import com.phoenix.sso.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private ItemService itemService;

    @Value("${COOKIE_CART_EXPIRE_TIME}")
    private Integer COOKIE_CART_EXPIRE_TIME;

    @Value("${ITEM_ADDRESS}")
    private String ITEM_ADDRESS;

    @Value("${ORDER_ADDRESS}")
    private String ORDER_ADDRESS;

    @Value("${PORTAL_ADDRESS}")
    private String PORTAL_ADDRESS;

    @Value("${SSO_ADDRESS}")
    private String SSO_ADDRESS;


    @Autowired
    private CartService cartService;

    @RequestMapping("add/{itemId}")
    public String addCart(@PathVariable Long itemId,@RequestParam(value = "number",defaultValue = "1") Integer number,HttpServletRequest request,HttpServletResponse response){
        //判断是否是登录状态
        User user =(User) request.getAttribute("user");
        //如果是登录状态，把购物车写入redis
        if(user!=null){
            //保存到服务端
            cartService.addCartToRedis(user.getId(),itemId,number);
            return "cartSuccess";
        }
        //如果是未登录，则使用cookie
        //从cookie中取购物车列表
        List<Item> cartList = this.getCartListFromCookie(request,response);
        //判断商品在购物车列表中是否存在
        boolean itemIsExist=false;
        for (Item item:cartList) {
            //如果存在，数量相加
            if(item.getId().equals(itemId)){
                //找到商品，数量相加
                itemIsExist=true;
                item.setNum(item.getNum()+number);
                break;
            }
        }
        //如果不存在，根据商品id，查询商品信息
        if(!itemIsExist){
            Item item = itemService.getItemById(itemId);
            //设置商品数量
            item.setNum(number);
            String images = item.getImage();
            //取一张图片
            if(StringUtils.isNotBlank(images)){
                item.setImage(images.split(",")[0]);
            }
            //把商品添加到商品列表
            cartList.add(item);
        }
        //写入cookie
        CookieUtils.setCookie(request,response,"cart",JsonUtils.objectToJson(cartList),COOKIE_CART_EXPIRE_TIME,true);
        //返回添加成功的购物车页面
        return "cartSuccess";
    }

    /**
     * 未登录：从coolie中取购物车列表
     * 已登录：从redis中取购物车列表, 并且把cookie中的数据合并到redis中的数据并保存，并且删除cookie中的购物车数据
     * @param request
     * @return
     */
    private List<Item> getCartListFromCookie(HttpServletRequest request,HttpServletResponse response){
        //判断是否是登录状态
        User user =(User) request.getAttribute("user");
        //如果是登录状态，从redis中读取itemList
        if(user!=null){
            String cart = CookieUtils.getCookieValue(request, "cart", true);
            if(StringUtils.isNotBlank(cart)){
                List<Item> itemsFromCookie = JsonUtils.jsonToList(cart, Item.class);
                //合并redis和cookie中的数据
                cartService.mergeCart(user.getId(),itemsFromCookie);

                CookieUtils.deleteCookie(request,response,"cart");
            }

            List<Item> itemsFromRedis = cartService.getItemListByUserId(user.getId());
            return itemsFromRedis;
        }
        //未登录状态，从cookie中读取itemList
        //返回的是json串
        String cart = CookieUtils.getCookieValue(request, "cart", true);
        if(StringUtils.isBlank(cart)){
            return new ArrayList<>();
        }
        //把json转换成商品列表
        List<Item> itemsFromCookie = JsonUtils.jsonToList(cart, Item.class);
        return itemsFromCookie;
    }


    private void saveItemList(HttpServletRequest request,HttpServletResponse response,List<Item> itemList){
        //判断是否是登录状态
        User user =(User) request.getAttribute("user");
        //如果是登录状态，从redis中读取itemList
        if(user!=null){
            //保存到服务端
            cartService.saveItemList(user.getId(),itemList);
            return;
        }
        //未登录，存入cookie
        CookieUtils.setCookie(request,response,"cart",JsonUtils.objectToJson(itemList),COOKIE_CART_EXPIRE_TIME,true);
    }

    @RequestMapping("/cart")
    public String showCartList(HttpServletRequest request,HttpServletResponse response, Model model){
        //从cookie取购物车列表
        List<Item> cartList = this.getCartListFromCookie(request,response);

        //把购物车列表传递给页面
        model.addAttribute("cartList",cartList);
        model.addAttribute("itemAddress",ITEM_ADDRESS);
        model.addAttribute("orderAddress",ORDER_ADDRESS);
        model.addAttribute("portalAddress",PORTAL_ADDRESS);
        model.addAttribute("ssoAddress",SSO_ADDRESS);
        model.addAttribute("registerAddress",SSO_ADDRESS+"/user/register/page");

        return "cart";
    }

    /**
     * 删除购物车商品
     * @param request
     * @param itemId
     * @return
     */
    @RequestMapping("/delete/{itemId}")
    public String showCartList(RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response,@PathVariable Long itemId){
        //从cookie取购物车列表
        List<Item> cartList = this.getCartListFromCookie(request,response);
        for (Item item:cartList) {
            //如果存在，数量相加
            if(item.getId().equals(itemId)){
                //找到商品，删除商品
                cartList.remove(item);
                break;
            }
        }
        //把购物车列表传递给页面
        attr.addFlashAttribute("cartList",cartList);
        attr.addFlashAttribute("itemAddress",ITEM_ADDRESS);
        this.saveItemList(request,response,cartList);
        //返回购物车的逻辑视图
        return "redirect:/cart/cart.html";
    }

    @RequestMapping(value = "/update/num/{itemId}/{number}",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateNumber(HttpServletRequest request, HttpServletResponse response, @PathVariable Long itemId,  @PathVariable (value = "number") Integer number){
        //从cookie中取购物车列表
        List<Item> cartList = this.getCartListFromCookie(request,response);
        //判断商品在购物车列表中是否存在
        boolean itemIsExist=false;
        for (Item item:cartList) {
            //如果存在，数量相加
            if(item.getId().equals(itemId)){
                //找到商品，数量相加
                itemIsExist=true;
                item.setNum(number);
                break;
            }
        }
        //如果不存在，根据商品id，查询商品信息
        if(!itemIsExist){
            return CommonResult.error("商品不存在");
        }
        this.saveItemList(request,response,cartList);
        return CommonResult.ok();
    }

}
