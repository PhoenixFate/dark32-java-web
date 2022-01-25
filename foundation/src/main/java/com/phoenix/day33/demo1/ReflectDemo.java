package com.phoenix.day33.demo1;
/*
 *  获取一个类的class文件对象的三种方式
 *   1. 对象获取
 *   2. 类名获取
 *   3. Class类的静态方法获取
 */
public class ReflectDemo {
	public static void main(String[] args)throws ClassNotFoundException {
		//1. 对象获取
		Person p = new Person();
		//调用Person类的父类的方法 getClass
		Class<? extends Person> c = p.getClass();
		System.out.println(c);
		System.out.println(c.getClass());
		
		//2. 类名获取
	    //每个类型,包括基本和引用,都会赋予这个类型一个静态的属性,属性名字class
		Class<Person> c1 = Person.class;
		System.out.println(c1);
		//三种获得Class对象都是同一个
		System.out.println(c==c1); //true
		System.out.println(c.equals(c1)); //true
		
		//3. Class类的静态方法获取 forName(字符串的类名)包名.类名
		Class<?> c2 = Class.forName("com.phoenix.day33.demo1.Person");
		System.out.println(c2);
	}
}
