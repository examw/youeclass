package com.changh.eschool.action.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.changh.eschool.action.BaseAction;
import com.changh.eschool.entity.ClassDetail;
import com.changh.eschool.entity.Grade;
import com.changh.eschool.entity.MyLesson;
import com.changh.eschool.entity.Student;
import com.changh.eschool.entity.StudyRecord;
import com.changh.eschool.service.ClassDetailService;
import com.changh.eschool.service.ExamCategoryService;
import com.changh.eschool.service.OrderService;
import com.changh.eschool.service.StudyRecordService;
import com.changh.eschool.until.Constant;
/**
* @Title: ClassRoomHdAction.java
* @Package com.changh.eschool.action.member
* @Description: TODO(进入高清频action)
* @author Failymiss jiangwei3457@163.com  
* @date 2013-7-6 下午3:10:47
* @version V1.0
 */
public class ClassRoomHdAction extends BaseAction{
	/**
	 * 课节Id
	 */
	private Integer classId;
	/**
	 * 班级
	 */
	private Grade  g;
	/**
	 * 课节
	 */
	private ClassDetail classDetail;
	/**
	 * 下一节Id
	 */
	private Integer nextId;
	/**
	 * 上一节Id
	 */
	private Integer prevId;
	/**
	 * 如果登录，判断当前用户是否购买了该课程
	 */
	private Integer payflag =0;
	/**
	 * 考试类别
	 */
	private String examName;
	public Integer getNextId() {
		return nextId;
	}
	public void setNextId(Integer nextId) {
		this.nextId = nextId;
	}
	public Integer getPrevId() {
		return prevId;
	}
	public void setPrevId(Integer prevId) {
		this.prevId = prevId;
	}
	
	/**
	 * service方法
	 */
	private ClassDetailService classDetailService;
	private OrderService orderService;
	private StudyRecordService studyRecordService;
	private ExamCategoryService examCategoryService;
	public OrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	/**
	 * action方法
	 * @return
	 * @throws Exception 
	 */
	public String execute() throws Exception{	
		g = classDetailService.findGbyclassId(classId);
		Student stu = (Student) session.get("student");
		if(stu==null){
			return "error";
		}
		examName = (examCategoryService.examLoad(g.getExamCategory().getExamPid())).getExamName();
		classDetail = classDetailService.findByClassId(classId);
		List<ClassDetail> list = new ArrayList<ClassDetail>();
		//判断当前用户是否已经购买了当前课程
		if(stu!=null){
			payflag= (Integer) session.get(g.getGradeId()+"");
			out:if(null==payflag){
				MyLesson myLesson = orderService.findMyLesson(stu.getStuId(),Constant.FINISH);
				if(myLesson !=null){
					for(int i=0;i<myLesson.getGrade().size();i++){
						if(myLesson.getGrade().get(i).getGradeId()==g.getGradeId()){
							payflag=1;
							session.put(g.getGradeId()+"", payflag);
							break out;
						}
					}
					for(int i=0;i<myLesson.getClassPackages().size();i++){
						for(int j=0;j<myLesson.getClassPackages().get(i).getGrade().size();j++){
							if(myLesson.getClassPackages().get(i).getGrade().get(j).getGradeId().equals(g.getGradeId())){
								payflag=1;	
								session.put(g.getGradeId()+"", payflag);
								break out;
							}
						}
					}
				}
			}
			//播放记录添加
			if(payflag==1){
				list = classDetailService.listClassDetail(g.getGradeId(), 0, 0);
				StudyRecord studyRecord = studyRecordService.findSRecord(stu.getStuId(), classId);
				if(studyRecord==null){
					StudyRecord  sr = new StudyRecord();
					Integer recordId = studyRecordService.findRecordId(stu.getStuId(), classId);
					sr.setRecordIp(ServletActionContext.getRequest().getRemoteAddr());
					sr.setClassId(classId);
					sr.setGradeId(g.getGradeId());
					sr.setRecordId(recordId);
					sr.setRecordStartTime(new Date());
					sr.setStuId(stu.getStuId());
					sr.setRecordName(g.getName()+classDetail.getClassTitle());
					sr.setCountNum(1);
					studyRecordService.save(sr);
				}else{
					studyRecord.setRecordStartTime(new Date());
					studyRecord.setCountNum(studyRecord.getCountNum()+1);
					studyRecordService.save(studyRecord);
				}	
			}else{
				list = classDetailService.findClassDetailByGid(g.getGradeId(), Constant.FREE);
			}		
		}
		//获得上下讲
		if(list!=null&&list.size()>1){
			for(int i=0;i<list.size();i++){
				
				if(list.get(i).getClassId()==(int)classId&&i>0&&i<list.size()-1){
				
					nextId =list.get(i+1).getClassId();
					prevId = list.get(i-1).getClassId();
				}else if(list.get(i).getClassId()==(int)classId&&i==0){
					
					nextId =list.get(i+1).getClassId();
					prevId = 0;
				}else if(list.get(i).getClassId()==(int)classId&&i==list.size()-1){
					
					nextId =0;
					prevId = list.get(i-1).getClassId();
				}
			}
		}
		httpResponse.addHeader("Referer", "http://www.youeclass.com/js/daliedu.swf");
		return "success";
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public Grade getG() {
		return g;
	}
	public void setG(Grade g) {
		this.g = g;
	}
	public ClassDetailService getClassDetailService() {
		return classDetailService;
	}
	public void setClassDetailService(ClassDetailService classDetailService) {
		this.classDetailService = classDetailService;
	}
	public ClassDetail getClassDetail() {
		return classDetail;
	}
	public void setClassDetail(ClassDetail classDetail) {
		this.classDetail = classDetail;
	}
	public Integer getPayflag() {
		return payflag;
	}
	public void setPayflag(Integer payflag) {
		this.payflag = payflag;
	}
	public StudyRecordService getStudyRecordService() {
		return studyRecordService;
	}
	public void setStudyRecordService(StudyRecordService studyRecordService) {
		this.studyRecordService = studyRecordService;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public ExamCategoryService getExamCategoryService() {
		return examCategoryService;
	}
	public void setExamCategoryService(ExamCategoryService examCategoryService) {
		this.examCategoryService = examCategoryService;
	}
		
}
