package com.phoenix.shop.utils;

import java.util.UUID;

public class CommonsUtils {

	/**
	 * 获得32长度的UUID字符串
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-","");
	}

	/**
	 * 获得64长度的UUID字符串
	 */
	public static String getUUID64(){
		return getUUID()+getUUID();
	}
	
}
