package com.phoenix.annotation;

public class AnnotationTest {

    @MineAnnotation(name = "abc",age=20)
    public void show() {
        System.out.println("show");
    }

    @MineAnnotation2({"abc","acc","def"})
    public void show2(){

    }

}
