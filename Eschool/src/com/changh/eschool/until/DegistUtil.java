package com.changh.eschool.until;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;
/**
 * Degist Util
 * @author Mr.rong
 *
 */
public class DegistUtil {
	/**
	 * ����MD5�㷨����
	 * @param seq
	 * @return
	 */
	public static String md5Digest(String seq) {
		try {
			//����MD5�㷨����
			MessageDigest md5Code =
				MessageDigest.getInstance("md5");
			byte[] bTmp=md5Code.digest(seq.getBytes());
			//����Base64�㷨�����ܺ��byte[]ת����string
			BASE64Encoder base64=new BASE64Encoder();
			seq=base64.encode(bTmp);
			return seq;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
//	public static void main(String[] args) {
//		System.out.println(md5Digest("11111111111"));
//		System.out.println(md5Digest("22"));
//	}
}
