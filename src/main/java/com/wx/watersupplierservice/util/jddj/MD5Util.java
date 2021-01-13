package com.wx.watersupplierservice.util.jddj;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 * @author wang-ql
 *
 */
public class MD5Util {

	private static final char[] hexDigits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	  public static String getMD5String(String s) {
		    try {
		      return getMD5String(s.getBytes("UTF-8"));
		    } catch (UnsupportedEncodingException ex) {
		      throw new RuntimeException("MD5加密失败，获取字符串UTF-8字节数组异常" + ex.getLocalizedMessage());
		    } 
		  }
	  
	  public static String getMD5String(byte[] bytes) {
		    try {
		      MessageDigest messagedigest = MessageDigest.getInstance("MD5");
		      messagedigest.update(bytes);
		      return bufferToHex(messagedigest.digest());
		    } catch (NoSuchAlgorithmException ex) {
		      throw new RuntimeException(MD5Util.class.getName() + "初始化失败，MessageDigest不支持MD5Util" + ex.getLocalizedMessage());
		    } 
		  }
	  
	  private static String bufferToHex(byte[] bytes) { 
		  return bufferToHex(bytes, 0, bytes.length); 
	  }

	  private static String bufferToHex(byte[] bytes, int m, int n) {
		    StringBuffer stringbuffer = new StringBuffer(2 * n);
		    int k = m + n;
		    for (int l = m; l < k; l++) {
		      appendHexPair(bytes[l], stringbuffer);
		    }
		    return stringbuffer.toString();
		  }
		  
		  private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		    char c0 = hexDigits[(bt & 0xF0) >> 4];
		    char c1 = hexDigits[bt & 0xF];
		    stringbuffer.append(c0);
		    stringbuffer.append(c1);
		  }
}
