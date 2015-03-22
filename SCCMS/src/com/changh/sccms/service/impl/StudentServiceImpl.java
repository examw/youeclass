package com.changh.sccms.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.changh.sccms.dao.StudentDAO;
import com.changh.sccms.dao.TradeDAO;
import com.changh.sccms.entity.Administrator;
import com.changh.sccms.entity.ExamRecord;
import com.changh.sccms.entity.Student;
import com.changh.sccms.entity.StudyRecord;
import com.changh.sccms.entity.Trade;
import com.changh.sccms.service.StudentService;
import com.changh.sccms.until.Constant;
import com.changh.sccms.until.DegistUtil;
import com.changh.sccms.until.TimeFormat;
import com.opensymphony.xwork2.ActionContext;


public class StudentServiceImpl implements StudentService {
	//injection
	private StudentDAO studentDao;	
	private TradeDAO tradeDao;
	
	public void setTradeDao(TradeDAO tradeDao) {
		this.tradeDao = tradeDao;
	}
	public void setStudentDao(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}
	public List<Student>  studentList(int page,int pagesize,String sortName,String sortOrder) throws Exception{
		List<Student> list=null;
	    list = studentDao.findAll(page, pagesize, sortName, sortOrder);
		return list;
	}
	public void deleteStudent(int stuId) throws Exception {
		studentDao.delete(stuId);
		
	}
	public Student updateStudent(int stuId) throws Exception {
		Student stu=studentDao.findById(stuId);
		return stu;
	}
	public void modifyStudent(Student stu) throws Exception {
		studentDao.modify(stu);
	}
	
	public List<Student> studentSelect(String date,int page,int pagesize,String sortname,String sortorder) throws Exception {
		List<Student> list=studentDao.studentSelect(date,page,pagesize,sortname,sortorder);
		return list;
	}

	public void addStudent(Student stu) throws Exception {
		
		//再次验证数据的合法性？？？？？？？？？？
		
		/*注册成功表示第一次登录，完善部分学员信息
		 * 1，登录次数置为1
		 * 2，登录ip
		 * 3，登录时间
		 */
		stu.setStuLoginNumber(1);
		stu.setStuLoginIp(ServletActionContext.getRequest().getRequestURI());
		stu.setStuLastLoginTime(new Date());
		//密码md5加密,（密码找回的业务逻辑？）
		stu.setStuPassword(DegistUtil.md5Digest(stu.getStuPassword()));
		//加入数据库
		studentDao.insert(stu);
	}
	public Student findByUsername(String username) throws Exception {

		return studentDao.findByUsername(username);
	}
	public Student findByEmail(String email) throws Exception {

		return studentDao.findByEmail(email);
	}
	public Student findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return studentDao.findById(id);
	}
	public boolean updateAccount(int stuId, double money,String tradePattern,String content) throws Exception {
		// TODO Auto-generated method stub
		Student stu = studentDao.findById(stuId);
		
		if(stu!=null)
		{
			//2013.05.17修改	 添加管理员的操作信息
			Administrator admin = (Administrator) ActionContext.getContext().getSession().get("admin");
			stu.setStuCash((((int)(stu.getStuCash()+money)*100))/100);
			studentDao.update(stu);//........
			//添加交易记录
			Trade trade = new Trade();
			//充值
			trade.setStuId(stuId);
			trade.setTradeCardBalance(stu.getStuCard());
			trade.setTradeCashBalance(stu.getStuCash());
			trade.setTradeOrderNo("SD"+TimeFormat.format(new Date()));
			trade.setTradePattern(tradePattern);
			trade.setTradeMoney(money);
			trade.setTradeTime(new Date());
			trade.setTradeIp(ServletActionContext.getRequest().getRemoteAddr());
			if(money>0)
			{
				trade.setTradeType(Constant.RECHARGE);
				trade.setTradeNote("管理员"+admin.getAdmUsername()+"手动充值"+(content==null?"":content));
			}
			else 
			{
				trade.setTradeType(Constant.OTHER);//其他扣费
				trade.setTradeNote(content);
			}
			tradeDao.save(trade);
			return true;
		}
		return false;
	}
	public int getTotal(String date) {
		return studentDao.getTotal(date);
	}
	public List<Student> search(int page, int pagesize, String sortname,
			String sortorder, String str, String searchName) throws Exception {
		return studentDao.search(page, pagesize, sortname, sortorder, str, searchName);
	}
	public int getSearchTotal(String searchName, String str) throws Exception {
		return studentDao.getSearchTotal(searchName, str);
	}
	public List<StudyRecord> findStudyRecord(int stuId, int page, int pagesize,
			String sortname, String sortorder) {
		// TODO Auto-generated method stub
		return studentDao.findStudyRecord(stuId, page, pagesize, sortname, sortorder);
	}
	public List<ExamRecord> findExamRecord(int stuId, int page, int pagesize,
			String sortname, String sortorder) {
		// TODO Auto-generated method stub
		return studentDao.findExamRecord(stuId, page, pagesize, sortname, sortorder);
	}
	public Integer getStudyRecordTotal(int stuId) {
		// TODO Auto-generated method stub
		return studentDao.getStudyRecordTotal(stuId);
	}
	public Integer getExamRecordTotal(int stuId) {
		// TODO Auto-generated method stub
		return studentDao.getExamRecordTotal(stuId);
	}
	
	@Override
	public void updatePwdReset(int stuId) throws Exception {
		// TODO Auto-generated method stub
		Student stu = this.studentDao.findById(stuId);
		stu.setStuPassword(DegistUtil.md5Digest("123456"));
		//this.studentDao.update(stu);
	}
	
	@Override
	public boolean updateAccount(int stuId, double money, String content)
			throws Exception {
		Student stu = studentDao.findById(stuId);
		if(stu!=null)
		{
			//2013.05.17修改	 添加管理员的操作信息
			Administrator admin = (Administrator) ActionContext.getContext().getSession().get("admin");
			stu.setStuCard((((int)(stu.getStuCard()-money)*100))/100);
			studentDao.update(stu);//........
			//添加交易记录
			Trade trade = new Trade();
			//充值
			trade.setStuId(stuId);
			trade.setTradeCardBalance(stu.getStuCard());
			trade.setTradeCashBalance(stu.getStuCash());
			trade.setTradeOrderNo("SD"+TimeFormat.format(new Date()));
			trade.setTradePattern("手动扣费(学习卡)");
			trade.setTradeMoney(money);
			trade.setTradeTime(new Date());
			trade.setTradeIp(ServletActionContext.getRequest().getRemoteAddr());
			trade.setTradeType(Constant.OTHER);
			trade.setTradeNote("管理员"+admin.getAdmUsername()+"手动扣费学习卡 "+(content==null?"":content));
			tradeDao.save(trade);
			return true;
		}
		return false;
	}
}
