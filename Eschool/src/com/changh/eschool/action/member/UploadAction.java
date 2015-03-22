package com.changh.eschool.action.member;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.changh.eschool.entity.Student;
import com.changh.eschool.until.FileUtil;
import com.changh.eschool.until.TimeFormat;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;




public class UploadAction extends ActionSupport{
	private File mf;//�ͻ����ϴ�����ʱ�ļ�����
	private String mfFileName;//mf+FileName ԭ�ļ���
	private String mfContentType;//��ȡ�ļ�MIME ����
	private int imgWidth;
	private int imgHeight;
	public String execute(){
		//��mf���Ƶ����̵�uploadĿ¼��
		//System.out.println(mf.getPath());
		//System.out.println(mfFileName);
		//System.out.println(mfContentType);
		Student stu = (Student)ActionContext.getContext().getSession().get("student");
		//�ļ�������
		//if(mfFileName.matches("[0-9]*[\\s|\\S]*[\u4E00-\u9FA5]+[\\s|\\S]*"))
		//{
			String[] arr = mfFileName.split("[.]");
			mfFileName = stu.getStuUsername()+TimeFormat.format(new Date())+"."+arr[arr.length-1];
		//}
		//Ŀ��λ�õ�����·��
		ServletContext application = 
			ServletActionContext.getServletContext();
		String realPath = application.getRealPath("upload"+File.separatorChar+"temp");
		new File(realPath).mkdirs();
		String filePath = realPath+File.separatorChar+mfFileName;
		System.out.println(filePath);
		try {//�����ļ�
			File tempFile = new File(filePath);
			//�ļ���С����2M
			FileUtil.copy(mf, tempFile);
			FileInputStream fis = new FileInputStream(tempFile);
			BufferedImage bufferedImg = ImageIO.read(fis);
			imgWidth = bufferedImg.getWidth();
			imgHeight = bufferedImg.getHeight();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String getMfContentType() {
		return mfContentType;
	}

	public void setMfContentType(String mfContentType) {
		this.mfContentType = mfContentType;
	}
	public String getMfFileName() {
		return mfFileName;
	}

	public void setMfFileName(String mfFileName) {
		this.mfFileName = mfFileName;
	}
	public File getMf() {
		return mf;
	}

	public void setMf(File mf) {
		this.mf = mf;
	}

	public int getImgWidth() {
		return imgWidth;
	}

	public int getImgHeight() {
		return imgHeight;
	}
	public void addActionError(String anErrorMessage) {    
		  
		//����Ҫ���ж�һ�£�������Ҫ�滻�Ĵ��󣬲Ŵ���    
		  
		   if (anErrorMessage.startsWith("the request was rejected because its size")) {    
		  
		     Matcher m = Pattern.compile("//d+").matcher(anErrorMessage);    
		  
		    String s1 = "";    
		  
		    if (m.find())   s1 = m.group();    
		  
		    String s2 = "";    
		  
		    if (m.find())   s2 = m.group();    
		  
		    //͵������������Ϣ�滻��    
		     super.addActionError("���ϴ����ļ���С��" + s1 + "����������Ĵ�С��" + s2 + "��");    
		    //Ҳ���Ը�Ϊ��Field����Ĵ���  
		    // super.addFieldError("file","���ϴ����ļ���С��" + s1 + "����������Ĵ�С��" + s2 + "��");    
		  
		  } else {//����ԭ���ķ�������   
		  
		     super.addActionError(anErrorMessage);    
		}    
		  
		}   
}
