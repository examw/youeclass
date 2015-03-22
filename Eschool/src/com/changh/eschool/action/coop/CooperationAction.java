package com.changh.eschool.action.coop;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.changh.eschool.action.BaseAction;
import com.changh.eschool.entity.AjaxJson;
import com.changh.eschool.entity.Grade;
import com.changh.eschool.entity.Items;
import com.changh.eschool.entity.Student;
import com.changh.eschool.service.GradeService;
import com.changh.eschool.service.OrderService;
import com.changh.eschool.service.StudentService;
import com.changh.eschool.until.Constant;
import com.changh.eschool.until.DegistUtil;
import com.changh.eschool.until.StringUtil;

public class CooperationAction extends BaseAction{
	//�������ύ��ѧԱ��Ϣ��װ����
	//private Student stu = new Student();
	//ע��service
	private StudentService studentService;
	
	private OrderService orderService;
	private GradeService gradeService;

	//�����Ϣ
	private AjaxJson j = new AjaxJson();
	public String execute()throws Exception
	{
		j.setSuccess(false);
		String data = httpRequest.getParameter("data");
		if(null==data||data==""){
			j.setSuccess(false);
			return "success"; 
		}
		JSONObject json = new JSONObject(data);
		String username = (String) json.get("username");
		String stuName = json.getString("stuName");
		String key =json.getString("key");
		Student stu2 = new Student();
		if(StringUtil.isNotEmpty(username)&&StringUtil.isNotEmpty(key)&&null!=(studentService.findCooperate(username))&&key.equals(studentService.findCooperate(username).getCoopKey())){
			String courseId=json.getString("courseId");
			Grade g = gradeService.findById(Integer.parseInt(courseId));
			if(StringUtil.isNotEmpty(courseId)&&StringUtil.isNotEmpty(g)){
				 if(StringUtil.isNotEmpty(json.getString("stuPswd"))){
					 Student stu = new Student();
					 Student stu1 =studentService.findByUsername(stuName);	 
					 /**ѧԱ��Ϣ��ʼ**/
					 //�û���ȥ��
					 if(StringUtil.isNotEmpty(stu1)){
						 stu.setStuUsername("wx_"+stuName);
						 j.setUsername("wx_"+stuName);
					 }else{
						 stu.setStuUsername(stuName);
						 j.setUsername(stuName);
					 }
					 
					 //����ȥ��
					 String email = json.getString("email");
					 Student stu3 =studentService.findByEmail(email);
					 if(StringUtil.isNotEmpty(stu3)){
						 stu.setStuEmail("wx_"+email);
					 }else{
						 stu.setStuEmail(email);
					 }
					stu.setStuPassword(json.getString("stuPswd"));
					j.setPassword(stu.getStuPassword());
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
					stu.setStuPhone(json.getString("phone"));
					stu.setStuName(json.getString("realname"));
					studentService.addStudent(stu);
					stu2 =studentService.findByUsername(stu.getStuUsername());
				 }else{
					stu2 =studentService.findByUsername(stuName);
				 }
				/**ѧԱ��Ϣ����**/
				 Items item = new Items();
				 item.setProductId(g.getGradeId());
				 item.setItemPType(Constant.SINGER_GRADE);
				 //��������ǵ��༶
				 //���Ե�������
				 if(g!=null&&g.getGradeSingle()==0)
					{
						item.setExamId(g.getExamCategory().getExamPid());	
						item.setItemName(gradeService.findExamName(g.getGradeId())+"��"+g.getName());
						item.setItemOPrice(g.getGradeOPrice());
						item.setItemRPrice(g.getGradeRPrice());
						//��ѧԱ��
						item.setItemSPrice(g.getGradeRPrice());
						//����ʱ��
						item.setItemOverTime(g.getGradeDueTime());	
						//System.out.println(items);
					}
				 orderService.saveOrder(stu2, g.getGradeRPrice(),item);
				 j.setSuccess(true);
			 }else{
				 j.setMsg("�γ̲�����");
			 }
			
		}else{
			j.setMsg("��֤ʧ��");
		}
		
		return "success";
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public AjaxJson getJ() {
		return j;
	}

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	public void setJ(AjaxJson j) {
		this.j = j;
	}
}
