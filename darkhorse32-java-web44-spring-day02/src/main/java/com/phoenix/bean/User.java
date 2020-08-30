package com.phoenix.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

//<bean name="user" class="com.phoenix.User"/>
// @Component("user")
// @Service("user")
// @Controller("user")
@Repository("user")
@Scope(scopeName = "prototype")
public class User {

    @Value("tome")
    private String name;

    @Value("19")
    private Integer age;

    public User(String name, Car car) {
        System.out.println("User(String name, Car car)");
        this.name = name;
        this.car = car;
    }

    public User( Car car,String name) {
        System.out.println("User( Car car,String name)");
        this.name = name;
        this.car = car;
    }

    //@Autowired //如果有多个car，无法选择具体注入哪一个
    //@Qualifier("car2")//使用@Qualifier注解告诉spring容器具体装配哪一个对象
    @Resource(name="car2")//手动注入哪一个对象
    private Car car;

    public User() {
        System.out.println("User()空参构造");
    }


    @PostConstruct //在对象创建后调用
    public void init(){
        System.out.println("user init()");
    }

    @PreDestroy //销毁前调用
    public void destroy(){
        System.out.println("user destroy()");
    }

    public String getName() {
        return name;
    }

    @Value("tom2")
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }

}
