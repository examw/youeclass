package com.changh.sccms.test;

import java.util.Arrays;

import com.changh.sccms.entity.ExamQuestion;


public class Test3 {
	public static void main(String[] args) throws Exception{
		//String str = "1231%' or '1%' = '1";
		//String str1 ="1231";
		//System.out.println(str.replace("%", "\\%"));
		//System.out.println(str1.replace("%", "\\%"));	
		String str = "sssssA) 15 	    B) 50     C) 55     D) 5E) 5F) 5G) 5H) 5I) 5";
		System.out.println(Arrays.toString(str.replaceAll("[ABCD][.)����]", "@@@").split("@@@")));
		System.out.println(str.replaceAll("[A-Z][.)����]", "@@@").split("@@@").length);
		System.out.println(str.replaceAll("<[/]?p>", "").replaceAll("&nbsp;", ""));
		String questContent = "<p><br /></p><p>1��ĳ�½����ٹ�··���������ж���·����ʪ���͵ı�׼��( &nbsp; &nbsp;)��<br />A���ֽ���Ժ�ˮ��<br />B���ֽ��Ȼ���<br />C��·���ٽ�߶�<br />D��·����ѹʵ��<br />[��]:A<br />[����]:�����Ŀ�Ľ���<br /><br />2������·���У�����������ʩ�����ǣ���<br />A������ʯ·��<br />B��·ǵ����·��<br />C��������·��<br />D��������ɰ����·��<br />[��]:C<br />[����]:�����Ŀ�Ľ���</p>";
		questContent = questContent.replaceAll("<[/]?p>", "").replaceAll("&nbsp;", "").replaceAll("<br />[0-9]+[.����]", "@@@@");
		String[] contentArr = questContent.split("@@@@");
		String content = "<p>1��ĳ�½����ٹ�··���������ж���·����ʪ���͵ı�׼��( &nbsp; &nbsp;)��<br />A���ֽ���Ժ�ˮ��<br />B���ֽ��Ȼ���<br />C��·���ٽ�߶�<br />D��·����ѹʵ��<br />2������·���У�����������ʩ�����ǣ���<br />A������ʯ·��<br />B��·ǵ����·��<br />C��������·��<br />D��������ɰ����·��<br />[��]:</p><p>1.C 2.C<br /></p>";
		content = "<p><br /></p>"+content;
		content = content.replaceAll("<[/]?p>", "").replaceAll("&nbsp;", "");
		String answer[] = content.substring(content.indexOf("[��]:"), content.length()).replaceAll("[0-9]+[.����]", "@@@@").split("@@@@");
		content = (content.substring(0, content.indexOf("[��]:")).replaceAll("<br />[0-9]+[.����]", "@@@@"));
		String[] arr = content.split("@@@@");
//		System.out.println(arr[1]);
		StringBuffer buf = new StringBuffer();
		for(int i=1;i<arr.length;i++)
		{
			String s = arr[i];
			System.out.println(s.replaceAll("<br />", "\n"));
			System.out.println("�𰸣�"+answer[i]);
		}
		System.out.println("ddd".contains(" "));
		System.out.println("ddd-".split("-").length);
	}
}
