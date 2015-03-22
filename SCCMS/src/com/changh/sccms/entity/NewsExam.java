package com.changh.sccms.entity;

public class NewsExam {
	private int id;
	private String ename;
	private NewsExam newsexam;
	private String cname;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public NewsExam getNewsexam() {
		return newsexam;
	}
	public void setNewsexam(NewsExam newsexam) {
		this.newsexam = newsexam;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
}
