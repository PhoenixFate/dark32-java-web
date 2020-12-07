package com.phoenix.day08;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		int[] arr = { 2, 2, 3, 4 };
		System.out.println("test");
		@SuppressWarnings({ "unused", "resource" })
		Scanner scanner = new Scanner(System.in);
		for (int j = 0; j < arr.length; j++) {
			System.out.println(arr[j]);
		}
		/*
		 * for (int i : arr) { System.out.println(i); }
		 */
		int method = method();
		System.out.println(method);
		

	}
	
	public static int method() {
		return 1;
	}

}
