package org.jecklove.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * md5加密工具类
 * @author junki
 * @date 2020年6月18日
 */
public final class Md5Util {

	private Md5Util() {};
	
	/*
	 *  md5加密：
	 *  	1.不可逆的
	 *  		root -> 7BB98050796B649E
	 *  		
	 */
	public static String encode(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// 获取md5加密对象
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		// 对密码进行加密，返回一个加密后的字节数组
		byte[] digest = md5.digest(str.getBytes("UTF-8"));
		// 使用base64进行编码
		String encodePassword = Base64.getEncoder().encodeToString(digest);
		return encodePassword;
	}
	
}
