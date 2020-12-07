package com.phoenix.day13.demo07;

public class Test {
	public static void main(String[] args) {
		Outer.Inner in = new Outer().new Inner();
		in.inner();
	}
}
