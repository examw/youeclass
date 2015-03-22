package com.changh.eschool.action.mobile;

public class GradeGroup {
	private String name;
	private Object obj;
	private int complex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public int getComplex() {
		return complex;
	}
	public void setComplex(int complex) {
		this.complex = complex;
	}
	public GradeGroup(String name, Object obj,int complex) {
		super();
		this.name = name;
		this.obj = obj;
		this.complex = complex;
	}
	
}
