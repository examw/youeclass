package com.changh.sccms.action.user.student;

public class StudyRecordAction {
	private Integer stuId;
	public String execute(){
		return "success";
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
}
