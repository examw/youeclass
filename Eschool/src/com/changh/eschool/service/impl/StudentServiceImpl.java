package com.changh.eschool.service.impl;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.changh.eschool.dao.AddressDAO;
import com.changh.eschool.dao.StudentDAO;
import com.changh.eschool.entity.Address;
import com.changh.eschool.entity.Cooperate;
import com.changh.eschool.entity.Student;
import com.changh.eschool.service.StudentService;
import com.changh.eschool.until.DegistUtil;
import com.changh.eschool.until.PropertiesUtil;


public class StudentServiceImpl implements StudentService {
	//injection
	private StudentDAO studentDao;	
	private AddressDAO addressDao;
	
	public void setAddressDao(AddressDAO addressDao) {
		this.addressDao = addressDao;
	}
	public void setStudentDao(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}
	public Student studentUpdate(int stuId) throws Exception {
		Student stu=studentDao.findById(stuId);
		return stu;
	}
	public void updateStudent(Student stu) throws Exception {
		studentDao.update(stu);
	}
	
	public List<Student> studentSelect(String date) throws Exception {
		List<Student> list=studentDao.select(date);
		return list;
	}

	public void addStudent(Student stu) throws Exception {
		
		//�ٴ���֤���ݵĺϷ��ԣ�������������������
		
		//�������ʱ��
		stu.setStuAddTime(new Date());
		//����״̬��Ϣ��Ĭ������״̬
		stu.setStuStatus(1);
		/*ע��ɹ���ʾ��һ�ε�¼�����Ʋ���ѧԱ��Ϣ
		 * 1����¼������Ϊ1
		 * 2����¼ip
		 * 3����¼ʱ��
		 */
		stu.setStuLoginNumber(1);
		stu.setStuLoginIp(ServletActionContext.getRequest().getRemoteAddr());
		stu.setStuLastLoginTime(new Date());
		stu.setStuImgUrl("../upload/userface/no_face.gif");//Ĭ��ͼ���ַ
		//����md5����,�������һص�ҵ���߼�����
		stu.setStuPassword(DegistUtil.md5Digest(stu.getStuPassword()));
		//�������ݿ�
		studentDao.insert(stu);
	}
	public Student findByUsername(String username) throws Exception {

		return studentDao.findByUsername(username);
	}
	public Student findByEmail(String email) throws Exception {

		return studentDao.findByEmail(email);
	}
	/**
	 *��¼��ҵ���߼�
	 */
	public Student login(String username, String password) throws Exception {
		Student stu = studentDao.findByUsername(username);
		if(stu!=null&&stu.getStuPassword().equals(DegistUtil.md5Digest(password))&&stu.getStuStatus()==1)
		{
			//��ʾ�û������붼��ȷ,�����ʺ�״̬����
			//�����û���Ϣ����session
				//��¼������1
			stu.setStuLoginNumber(stu.getStuLoginNumber()+1);
				//��¼ip
			stu.setStuLoginIp(ServletActionContext.getRequest().getRemoteAddr());
				//��¼ʱ��
			stu.setStuLastLoginTime(new Date());
			//�����û���Ϣ
			studentDao.update(stu);
			//��cookie
			Cookie cookie = new Cookie("eschool_user", username);
			//����cookie����ʱ��
			cookie.setMaxAge(60*60*24*30); //��λ���룬ʱ��Ϊһ����
			//����·��
			cookie.setPath("/");
			ServletActionContext.getResponse().addCookie(cookie);
			return stu;
		}else
			return null;
	}
	/**
	 *��¼��ҵ���߼�
	 */
	public Student login(String username, String password,Integer auto) throws Exception {
		Student stu = studentDao.findByUsername(username);
		if(stu!=null&&stu.getStuPassword().equals(DegistUtil.md5Digest(password))&&stu.getStuStatus()==1)
		{
			//��ʾ�û������붼��ȷ,�����ʺ�״̬����
			//�����û���Ϣ����session
				//��¼������1
			stu.setStuLoginNumber(stu.getStuLoginNumber()+1);
				//��¼ip
			stu.setStuLoginIp(ServletActionContext.getRequest().getRemoteAddr());
				//��¼ʱ��
			stu.setStuLastLoginTime(new Date());
			if(auto!=null&&auto.equals(1))
			{
				String token = DegistUtil.md5Digest(stu.getStuUsername()+"|"+stu.getStuPassword());
				Cookie cookie = new Cookie("youeclass_token", token);
				//����cookie����ʱ��
				cookie.setMaxAge(60*60*24*30); //��λ���룬ʱ��Ϊһ����
				//����·��
				cookie.setPath("/");
				ServletActionContext.getResponse().addCookie(cookie);
				stu.setStuToken(token);
			}
			//�����û���Ϣ
			studentDao.update(stu);
			//��cookie
			Cookie cookie = new Cookie("eschool_user", username);
			//����cookie����ʱ��
			cookie.setMaxAge(60*60*24*30); //��λ���룬ʱ��Ϊһ����
			//����·��
			cookie.setPath("/");
			ServletActionContext.getResponse().addCookie(cookie);
			return stu;
		}else
			return null;
	}
	/**
	 * �����һأ����� �����ӵ��ʼ� �������������������
	 */
	public boolean findPwd(String username, String email1,JavaMailSender mailSender,SimpleMailMessage mailMessage) throws Exception {
		// TODO Auto-generated method stub
		if(email1==null||email1.trim().isEmpty()) return false;
		Student stu = studentDao.findByEmail(email1);
		if(stu!=null)
		{
			//!!!!!!!!!!!!!ע��������ӵ�ַ����Ҫ�޸ĵģ�����������������������
			StringBuffer buf = new StringBuffer("<html><head></head><body><h3>�𾴵�������УѧԱ ");
			StringBuffer addr = new StringBuffer();
			buf.append(stu.getStuUsername());
			buf.append("</h3>���ã�<p>Ҫ�����������룬�������������ӣ����������ַ. </p>");
			buf.append("<p><a href='");
			//�˴���ַ��Ҫ�޸�
			addr.append(PropertiesUtil.getOptValue("findpwd"));
			addr.append("user/resetPwd?username=");
			addr.append(stu.getStuUsername());
			addr.append("&actionCode=");
			addr.append(URLEncoder.encode(stu.getStuPassword(), "UTF-8"));
			//addr.append("&expire=");//��Ч����������
			buf.append(addr.toString());
			buf.append("'>");
			buf.append(addr.toString());
			buf.append("</a></p><p>���ͨ��������������޷����ʣ��뽫����ַ���Ʋ�ճ�����µ�����������С�<br>�����������յ��˴˵����ʼ���������ִ���κβ�����ȡ�������һأ������ʻ����뽫���ᱻ�޸ġ�</p></body></html> ");
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"GB2312");
			helper.setFrom(mailMessage.getFrom());
			helper.setSubject(mailMessage.getSubject());
			helper.setTo(email1);
			helper.setText(buf.toString(),true);
	        mailSender.send(mimeMessage);
	        return true;
		}
		return false;
	}
	/**
	 * ��������
	 */
	public void resetPwd(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		Student stu = studentDao.findByUsername(username);
		if(stu!=null)
		{
			stu.setStuPassword(DegistUtil.md5Digest(password));
			studentDao.update(stu);
		}
	}
	/**
	 * �ӹ��ﳵ��ע��
	 */
	public boolean registerFromCart(Student stu) throws Exception {
		// TODO Auto-generated method stub
		//���û���Ϣ�������ݿ⣬��session
		//��ռ��ע��ʧ��
		if(studentDao.isExist(stu.getStuUsername(), stu.getStuEmail()))
		{
			return false;
		}
		addStudent(stu);
		////////�������ݿ��stu��־ö��󣬶�����������ֵ��������ʵ��ֵ������ΪId��ֵ�����⣡����
		///ÿ�λ�Ҫ���һ�α�Ӱ���ٶ�
		stu = findByUsername(stu.getStuUsername());
		//////////////
		//��session
		ServletActionContext.getRequest().getSession().setAttribute("student", stu);
		//��cookie
		Cookie cookie = new Cookie("eschool_user", stu.getStuUsername());
		//����cookie����ʱ��
		cookie.setMaxAge(60*60*24*30); //��λ���룬ʱ��Ϊһ����
		//����·��
		cookie.setPath("/");
		ServletActionContext.getResponse().addCookie(cookie);
		return true;
	}
	public List<Address> findAddrListByStuId(int stuId) throws Exception {
		// TODO Auto-generated method stub
		return addressDao.findByStuId(stuId);
	}
	public void addAddr(Address addr,int stuId) throws Exception {
		// TODO Auto-generated method stub
		addr.setStuId(stuId);
		addr.setAddrIsDefault(0);
		addressDao.save(addr);
	}
	public void updateAddr(Address addr) throws Exception {
		// TODO Auto-generated method stub
		addressDao.update(addr);
	}
	public Address findAddr(int aid)throws Exception
	{
		return addressDao.findById(aid);
	}
	public void updateDefaultAddr(int aid, int stuId) throws Exception {
		// TODO Auto-generated method stub
		Address addr1 = addressDao.findById(aid);
		if(addr1==null)
		{
			return;
		}
		Address addr2 = addressDao.findDefault(stuId);
		if(addr2!=null)
		{
			if(addr2.getAddrId()==aid)
			{
				return;
			}
			addr2.setAddrIsDefault(0);
			//addressDao.update(addr2);
		}
		addr1.setAddrIsDefault(1);
		//addressDao.update(addr1);
		
	}
	public Student findByStuId(int stuId) {
		return studentDao.findByStuId(stuId);
	}
	public Student findByNameAndPwd(String username, String password)
			throws Exception {
		// TODO Auto-generated method stub
		Student stu = studentDao.findByUsername(username);
		if(stu!=null&&stu.getStuPassword().equals(DegistUtil.md5Digest(password))&&stu.getStuStatus()==1)
		{
			return stu;
		}else
		{
			return null;
		}
	}
	@Override
	public Cooperate findCooperate(String url) {
		// TODO Auto-generated method stub
		return studentDao.findCooperate(url);
	}
	/**
	 * ���ֻ��Ų���ѧԱ
	 * 2014.04.11
	 */
	public Student findByMobilePhone(String phone){
		if(phone == null ||phone.trim().isEmpty()) return null;
		return studentDao.findByMobilePhone(phone);
	}
	/**
	 * 
	 */
	public Student findByToken(String token){
		if(token == null ||token.trim().isEmpty()) return null;
		return studentDao.findByToken(token);
	}
}
