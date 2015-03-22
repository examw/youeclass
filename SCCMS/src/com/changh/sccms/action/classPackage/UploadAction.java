package com.changh.sccms.action.classPackage;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.changh.sccms.service.ClassPackageService;
import com.changh.sccms.until.PropertiesUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Title: UploadAction.java
 * @Package com.changh.sccms.action.classPackage
 * @Description: TODO(�ϴ��ײ�ͼƬ)
 * @author Failymiss jiangwei3457@163.com
 * @date 2013-6-20 ����1:40:09
 * @version V1.0
 */
public class UploadAction extends ActionSupport{
	private File fileInput;
	private String fileInputFileName;
	private String savePath;
	private ClassPackageService classPackageService;

	public String execute() throws Exception {
		String rootPath = PropertiesUtil.getOptValue("rootPath");
		String newFileName = "";// ���ļ���
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");

		String ptypeId = classPackageService.getPackageCategoryId() + "";

		String savePath = rootPath + this.getSavePath();

		newFileName = "package_" + ptypeId + ".gif";
		String sPath = savePath + "\\" + newFileName;
		deleteFile(sPath);
		this.getFileInput().renameTo(new File(sPath));
		String s1 = "<font color='green'>�ļ��ϴ��ɹ�</font>";
		response.getWriter().print(s1);
		return null; // ���ﲻ��Ҫҳ��ת�����Է��ؿվͿ�����
	}

	/**
	 * ɾ�������ļ�
	 * 
	 * @param sPath
	 *            ��ɾ���ļ����ļ���
	 * @return �����ļ�ɾ���ɹ�����true�����򷵻�false
	 */
	public boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	public File getFileInput() {
		return fileInput;
	}

	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}

	public String getFileInputFileName() {
		return fileInputFileName;
	}

	public void setFileInputFileName(String fileInputFileName) {
		this.fileInputFileName = fileInputFileName;
	}

	public String getSavePath() {
		return this.savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public ClassPackageService getClassPackageService() {
		return classPackageService;
	}

	public void setClassPackageService(ClassPackageService classPackageService) {
		this.classPackageService = classPackageService;
	}

}
