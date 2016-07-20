package org.windbell.lab.hamster.utlis;

import java.util.Random;

/**
 * @author MN
 * @Description: 生成随机字符串工具类
 * @date: 2016年6月18日 上午7:56:26
 */
public final class RandomStrUtils {
	public static String allChar="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static Random random = new Random();
	
	
	private RandomStrUtils() {
	}

	/**
	 * @Title: getRandomString
	 * @author: MN
	 * @Description: 生成指定长度的英文和数字字符串
	 * @date: 2016年6月18日 上午7:53:32
	 * @param strLength
	 * @return
	 */
	public static String getRandomString(int strLength){
		StringBuffer sb=new StringBuffer(allChar.length());
		for (int i = 0; i < strLength; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}
	
	public static String getUpperRandomString(int number){
		return getRandomString(number).toUpperCase();
	}
	
	public static String getLowerRandomString(int number){
		return getRandomString(number).toLowerCase();
	}
	
	public static String getRandomString(){
		return getRandomString(random.nextInt());
	}
}
