package com.changh.sccms.until;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

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
	public static String getMd5CodeOfQuestion(String content)
	{

		//���������Ŀ
		content = content.toUpperCase().replaceAll("[^0-9A-Za-z\u4E00-\u9FA5]", "");
		String[] arrStr = content.replaceAll("[ABCD]{1}","@@@").split("@@@");
/*		if(arrStr.length>50)
		{
			System.out.println("�����Ŀѡ�����û�а취�ж��Ƿ��ظ�");
		}else
		{
			Arrays.sort(arrStr);
			System.out.println(Arrays.toString(arrStr));
		}*/
		try
		{
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] temp = digest.digest(Arrays.toString(arrStr).getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			String dest = encoder.encode(temp);
			return dest;
		} catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
//	public static void main(String[] args) {
//		System.out.println(md5Digest("11111111111"));
//		System.out.println(md5Digest("22"));
//	}
}
