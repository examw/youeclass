package com.changh.eschool.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.changh.eschool.dao.RechargeRecordDAO;
import com.changh.eschool.dao.StudentDAO;
import com.changh.eschool.dao.StudyCardDAO;
import com.changh.eschool.dao.TradeDAO;
import com.changh.eschool.entity.RechargeRecord;
import com.changh.eschool.entity.Student;
import com.changh.eschool.entity.StudyCard;
import com.changh.eschool.entity.Trade;
import com.changh.eschool.service.StudyCardService;
import com.changh.eschool.until.Constant;
import com.changh.eschool.until.TimeFormat;

public class StudyCardServiceImpl implements StudyCardService{
	//injection
	private StudyCardDAO studyCardDao;
	private StudentDAO studentDao;
	private RechargeRecordDAO rechargeRecordDao;
	private TradeDAO tradeDao;
	
	public void setTradeDao(TradeDAO tradeDao) {
		this.tradeDao = tradeDao;
	}

	public void setStudyCardDao(StudyCardDAO studyCardDao)throws Exception {
		this.studyCardDao = studyCardDao;
	}
	
	public void setStudentDao(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}

	public void setRechargeRecordDao(RechargeRecordDAO rechargeRecordDao) {
		this.rechargeRecordDao = rechargeRecordDao;
	}

	public int recharge(int cardId,String password,int money) throws Exception {
		/**
		 * ѧϰ����ֵ
		 * 1���˶�ѧϰ���Ŀ������뼰�Ƿ���ڣ���ֵ���������
		 * ������ѧϰ�����ͣ�����͵�һ��ֻ�ܳ�һ�Σ������ظ���ֵ
		 * 2����ֵ�ɹ�,����ѧϰ��״̬
		 * 3������ѧԱ�˻���Ϣ
		 * 4����ӳ�ֵ��¼
		 */
		StudyCard card = studyCardDao.findById(cardId);
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(new Date());
		Date today = format.parse(str);
		//���ź����붼��ȷ����û�й��ڲ���û�г�ֵ��
		if(card!=null&&card.getCardPassword().equals(password))
		{
			Student student = (Student) ServletActionContext.getRequest().getSession().getAttribute("student");
			if(student==null) return -4;//��¼�Ѿ�ʧЧ
			if(card.getCardOverTime().compareTo(today)<0)
			{
				return -1; //ѧϰ����Ч���ѹ�
			}
			//��ֵ�������Ϳ������ҳ�ֵ�����Ϳ������Ϳ�һ��һ�Σ�
			RechargeRecord rr = rechargeRecordDao.findFreeCard(student.getStuId());
			if(card.getCardIsPresent()==1)
			{
				if(rr!=null&&rr.getRcCardId()!=cardId)
				{
					return -3; //ѧϰ���������Ϳ������Ѿ�ʹ�ù�һ��
				}
				money = card.getCardValue();//�������͵�һ���Գ�ֵ���
			}
			if(card.getCardBalance()<money)
			{
				return (int) (-100-card.getCardBalance()); //��ֵ���������
			}
			//����ѧϰ��״̬
			card.setStuId(student.getStuId());
			card.setCardUseTime(new Date());
			card.setCardBalance(card.getCardBalance()-money);//�������
			studyCardDao.update(card);
			//����ѧԱ�˻���Ϣ,һ�������ʧ�ܻ�Ӱ��session�е����ֵ,����copyһ���������в���
			Student copyStu = (Student) student.clone();
			copyStu.setStuCard(student.getStuCard()+money);
			studentDao.update(copyStu);
			//��ӳ�ֵ��¼
			RechargeRecord record = new RechargeRecord();
			record.setRcAddTime(new Date());//��ֵʱ��
			record.setRcCardId(card.getCardId());//��ֵ����
			record.setRcMoney(money);//��ֵ���
			record.setRcIp(ServletActionContext.getRequest().getRemoteAddr());//��ֵip
			record.setRcIsPresent(card.getCardIsPresent());//�Ƿ�Ϊ����
			record.setRcType(0);//ѧϰ����ֵ
			record.setStudent(student);
			rechargeRecordDao.save(record);//��ӵ����ݿ�
			Trade trade = new Trade();
			trade.setCardId(card.getCardNo());
			trade.setTradeOrderNo("XXK"+TimeFormat.format(new Date()).substring(8));
			trade.setStuId(student.getStuId());
			trade.setTradePattern("ѧϰ����ֵ");
			trade.setTradeType(Constant.RECHARGE);
			trade.setTradeMoney(money);
			trade.setTradeCardBalance(copyStu.getStuCard());
			trade.setTradeCashBalance(student.getStuCash());
			trade.setTradeIp(ServletActionContext.getRequest().getRemoteAddr());
			trade.setTradeTime(new Date());
			trade.setTradeNote("ѧϰ����ֵ�����ţ�"+card.getCardNo());
			tradeDao.save(trade);
			//����session��stu��ֵ
			student.setStuCard(student.getStuCard()+money);
			ServletActionContext.getRequest().getSession().setAttribute("student", student);
			return money;//��ֵ�ɹ������س�ֵ�Ľ��
		}
		return 0; //���Ż����������
	}
	public List<RechargeRecord> findRecordByStuId(int stuId,int page,int pagesize,String sortname,String sortorder) throws Exception {
		// TODO Auto-generated method stub
		return rechargeRecordDao.findPageByStuId(stuId, page, pagesize, sortname, sortorder);
	}
	public StudyCard findCard(int cardId, String password) throws Exception {
		// TODO Auto-generated method stub
		return studyCardDao.findCard(cardId, password);
	}
	public long findTotalByStuId(int stuId)throws Exception
	{
		return rechargeRecordDao.findTotalByStuId(stuId);
	}
	@Override
	public List<Trade> findTradeByStuId(int stuId, int page, int pagesize,
			String sortname, String sortorder) throws Exception {
		// TODO Auto-generated method stub
		return tradeDao.findPageByStuId(stuId, Constant.RECHARGE, page, pagesize, sortname, sortorder);
	}
	@Override
	public long findTradeTotalByStuId(int stuId) throws Exception {
		// TODO Auto-generated method stub
		return tradeDao.findTotalByStuId(stuId, Constant.RECHARGE);
	}
	
}  

