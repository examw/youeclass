package com.changh.sccms.entity;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

/**
 * TbNews entity. @author MyEclipse Persistence Tools
 */

public class News implements java.io.Serializable {

	// Fields

	private Integer newId;
	private String newTitle;
	private String subTitle;//∏±±ÍÃ‚
	private String newContent;
	private Integer clickNum=0;
	private Integer isTop;
	private String newTemplet;
	private String newPath;
	private ExamCategory exam;
	private Province province;
	private NewClass newclass;
	private Integer orderId;
	private Date addTime;
	private String keywords;
	private String aditor;

	// Constructors
	private String className;
	private String examName;

	/** default constructor */
	public News() {
	}

	/** minimal constructor */
	public News(String newTitle, String newContent, Date addTime,
			String aditor) {
		this.newTitle = newTitle;
		this.newContent = newContent;
		this.addTime = addTime;
		this.aditor = aditor;
	}

	// Property accessors

	public Integer getNewId() {
		return this.newId;
	}

	public void setNewId(Integer newId) {
		this.newId = newId;
	}

	public String getNewTitle() {
		return this.newTitle;
	}

	public void setNewTitle(String newTitle) {
		this.newTitle = newTitle;
	}
	@JSON(serialize=false)
	public String getNewContent() {
		return this.newContent;
	}

	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}

	public Integer getClickNum() {
		return this.clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public Integer getIsTop() {
		return this.isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public String getNewTemplet() {
		return this.newTemplet;
	}

	public void setNewTemplet(String newTemplet) {
		this.newTemplet = newTemplet;
	}

	public String getNewPath() {
		return this.newPath;
	}

	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}

	
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getAditor() {
		return this.aditor;
	}

	public void setAditor(String aditor) {
		this.aditor = aditor;
	}
	@JSON(serialize=false)
	public NewClass getNewclass() {
		return newclass;
	}

	public void setNewclass(NewClass newclass) {
		this.newclass = newclass;
	}
	@JSON(serialize=false)
	public ExamCategory getExam() {
		return exam;
	}

	public void setExam(ExamCategory exam) {
		this.exam = exam;
	}
	@JSON(serialize=false)
	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getClassName() {
		return newclass.getClassCname();
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getExamName() {
		return exam.getExamName();
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}
	
	public String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}