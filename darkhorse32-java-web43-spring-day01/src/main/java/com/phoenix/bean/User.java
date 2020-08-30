package com.phoenix.bean;

public class User {

    private String name;

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

    private Car car;

    public User() {
        System.out.println("User()空参构造");
    }

    public void init(){
        System.out.println("user init()");
    }

    public void destroy(){
        System.out.println("user destroy()");
    }

    public String getName() {
        return name;
    }

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
