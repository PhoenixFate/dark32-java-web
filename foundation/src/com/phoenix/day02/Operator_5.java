package com.phoenix.day02;
/*
	三元运算符
	公式:
	   布尔表达式 ? 结果1:结果2;
	   布尔表达式结果是true,三元运算符的结果,就是  结果1
	   布尔表达式结果是false,三元运算符的结果,就是 结果2
*/
public class Operator_5{
	@SuppressWarnings("unused")
	public static void main(String[] args){
		System.out.println(3<5?99:88);
		
		String s = 0==1?"哈哈":"呵呵";
		System.out.println(s);
		
		
		int a = 5;
		int b = 3;
		int c = 1;
		//         T  && T 
		int n2 = (a>b && b>c) ? (c++) : (++c);
		System.out.println(c);
		System.out.println(n2);
	}
}