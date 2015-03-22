package com.changh.eschool.entity;

import java.util.List;

public class MyLesson {
	private List<Grade> grade;
	private List<ClassPackage> classPackages;
	public List<Grade> getGrade() {
		return grade;
	}
	public void setGrade(List<Grade> grade) {
		this.grade = grade;
	}
	public List<ClassPackage> getClassPackages() {
		return classPackages;
	}
	public void setClassPackages(List<ClassPackage> classPackages) {
		this.classPackages = classPackages;
	}
	
	
}
