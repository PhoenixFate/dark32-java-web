package com.phoenix.shop.vo;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    // 该购物车中存储的n个购物项　
    private Map<String,CartItem> cartItems=new HashMap<>();

    private double totalMoney;

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<String, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItems=" + cartItems +
                ", totalMoney=" + totalMoney +
                '}';
    }
}
