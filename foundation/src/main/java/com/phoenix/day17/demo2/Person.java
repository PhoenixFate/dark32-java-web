package com.phoenix.day17.demo2;

public class Person {
	public void finalize(){
		System.out.println("垃圾被收取了");
	}
}
