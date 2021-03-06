package com.changh.sccms.action.grade;

import com.changh.sccms.action.BaseAction;
import com.changh.sccms.entity.Administrator;
import com.changh.sccms.entity.Grade;
import com.changh.sccms.service.ExamCategoryService;
import com.changh.sccms.service.GradeService;
import com.changh.sccms.service.TeacherService;

public class UpdateAction extends BaseAction{
	private boolean ok =false;
	private Integer tchId;
	private Integer examId;
	private Integer gtypeId;
	private Grade grade;
	private GradeService gradeService;
	private ExamCategoryService examCategoryService;
	private TeacherService teacherService;
	public String execute() throws Exception{
		Administrator admin = (Administrator) session.get("admin");
		if(admin==null){
			return "false";
		}
		grade.setExamCategory(examCategoryService.examLoad(examId));
		grade.setGradeCategory(gradeService.findByGtypeId(gtypeId));
		grade.setTchId(tchId);
		grade.setTchName(teacherService.getTeacher(tchId).getTchName());
		gradeService.update(grade);
		setOk(true);
		return "success";
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public GradeService getGradeService() {
		return gradeService;
	}
	public void setExamCategoryService(ExamCategoryService examCategoryService) {
		this.examCategoryService = examCategoryService;
	}
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public Integer getTchId() {
		return tchId;
	}
	public void setTchId(Integer tchId) {
		this.tchId = tchId;
	}
	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	public Integer getGtypeId() {
		return gtypeId;
	}
	public void setGtypeId(Integer gtypeId) {
		this.gtypeId = gtypeId;
	}
	
}
