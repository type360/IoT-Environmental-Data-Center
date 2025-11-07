package com.briup.smartlabs.gateway.utils;

public class MessageConvertUtils {
	/**
	 * 16进制字符串数据转字节数组
	 * @author guomiao
	 *
	 * @param str  16进制字符串
	 * @return		字节数组
	 */
	public static byte[] toByteArray(String str) {
		byte[] buff = new byte[str.length()/2];
		for(int i = 0;i<str.length()/2;i++){
			String hexStr = str.substring(i*2,i*2+2);
			byte b = (byte)Integer.parseInt(hexStr,16);
			buff[i] = b;
		}
		return buff;
	}

	public static void main(String[] args) {
		byte[] buff = toByteArray("12230101");
		for(byte b:buff) {
			System.out.println(b);
		}
	}
}






