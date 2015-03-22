package com.changh.sccms.action.system;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.changh.sccms.until.PropertiesUtil;

public class UploadFileAction {
	private File upfile;
	private String  upfileFileName;
	private String savePath;
	public String execute() throws Exception {
			opt(0,"文件上传成功");
			return null;
	}
	private void opt(Integer flag,String msg) throws Exception{
		String newFileName = "";// 新文件名
		String nowTime =null;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		String savePath = this.getSavePath();
		String frontName = PropertiesUtil.getOptValue("frontName");
		savePath = savePath.replace(ServletActionContext.getRequest().getContextPath().substring(1), frontName);
		nowTime = new SimpleDateFormat("yyyymmddHHmmss").format(new Date());// 当前时间
				newFileName = nowTime+this.getUpfileFileName();
		FileUtils.copyFile(this.getUpfile(), new File(savePath + "\\" + newFileName));
		response.getWriter().print("<font color='green'>"+msg+"</font>");
	}
	public String getSavePath() {
		return ServletActionContext.getRequest().getRealPath(this.savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public File getUpfile() {
		return upfile;
	}
	public void setUpfile(File upfile) {
		this.upfile = upfile;
	}
	public void setUpfileFileName(String upfileFileName) {
		this.upfileFileName = upfileFileName;
	}
	public String getUpfileFileName() {
		return upfileFileName;
	}
}
