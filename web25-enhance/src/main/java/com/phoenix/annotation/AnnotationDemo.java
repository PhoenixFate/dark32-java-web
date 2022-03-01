package com.phoenix.annotation;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class AnnotationDemo {

    @SuppressWarnings({"rawtypes","unused"})
    public static void main(String[] args) {
        @SuppressWarnings({"rawtypes","unused"})
        List list=new ArrayList();

    }

    @Deprecated
    public static void show(){

    }

    //帮助人员检查重写的方法名是否正确
    @Override
    public String toString() {
        return super.toString();
    }
}
