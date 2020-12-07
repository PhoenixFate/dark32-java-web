package com.phoenix.day13.demo09;

public interface Smoking {
	public abstract void smoking();
}
/*
 *  实现类,实现接口 重写接口抽象方法,创建实现类对象
 *  class xxx implements Smoking{
 *      public void smoking(){
 *      
 *      }
 *  }
 *  xxx x = new xxx();
 *  x.smoking(); 
 *  Smoking s = new xxx();
 *  s.smoking();
 *  
 *  匿名内部类,简化问题:  定义实现类,重写方法,建立实现类对象,合为一步完成
 */
