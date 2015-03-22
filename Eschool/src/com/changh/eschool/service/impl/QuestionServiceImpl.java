package com.changh.eschool.service.impl;

import java.util.List;

import com.changh.eschool.dao.AnswerDAO;
import com.changh.eschool.dao.QuestionDAO;
import com.changh.eschool.entity.Answer;
import com.changh.eschool.entity.Question;
import com.changh.eschool.service.QuestionService;

public class QuestionServiceImpl implements QuestionService {
	private QuestionDAO questionDao;
	private AnswerDAO answerDao;
	
	public void setAnswerDao(AnswerDAO answerDao) {
		this.answerDao = answerDao;
	}
	public void setQuestionDao(QuestionDAO questionDao) {
		this.questionDao = questionDao;
	}
	//�����������⣬����ҳ
	public List<Question> findAll(int page, int pagesize, String sortname,
			String sortorder) throws Exception {
		return questionDao.findPage(page, pagesize, sortname, sortorder);
				
	}
	//�����ݻ�����������⣬����ҳ
	public List<Question> findByContent(int page, int pagesize,
			String sortname, String sortorder, String content) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer buf = new StringBuffer();
		buf.append(" where q.questionTitle like '%");
		buf.append(content);
		buf.append("%' or ");
		buf.append(" q.questionContent like '%");
		buf.append(content);
		buf.append("%' ");
		return questionDao.searchPage(page, pagesize, sortname, sortorder, buf.toString());
		
	}
	public Question findById(int questionId) throws Exception {
		return questionDao.findById(questionId);
	}
	//��������������ĳ���������ҳ��ʾ
	public List<Question> findByType(int page, int pagesize, String sortname,
			String sortorder, int type) throws Exception {
		// TODO Auto-generated method stub
		String content = " where q.questionSource = "+type;
		return questionDao.searchPage(page, pagesize, sortname, sortorder, content);
	}
	//�������״̬���ѻش�δ�ش𣩣���ҳ��ʾ
	public List<Question> findByStatus(int page, int pagesize, String sortname,
			String sortorder, int status) throws Exception {
		String content = " where q.questionStatus = "+status;
		return questionDao.searchPage(page, pagesize, sortname, sortorder, content);
	}
	//ĳһ��ѧԱ���������
	public List<Question> findByStuId(int page, int pagesize, String sortname,
			String sortorder, int stuId) throws Exception {
		// TODO Auto-generated method stub
		String content = " where q.student.stuId = "+stuId;
		return questionDao.searchPage(page, pagesize, sortname, sortorder, content);
	}
	public int findTotal(String criteria) throws Exception {
		// TODO Auto-generated method stub
		return (int) questionDao.findTotal(criteria);
	}
	public boolean deleteQuestion(int questionId, int stuId) throws Exception {
		// TODO Auto-generated method stub
		Question question = questionDao.findBy2Id(questionId, stuId);
		return questionDao.delete(question);
	}
	public void save(Question question) throws Exception {
		// TODO Auto-generated method stub
		questionDao.save(question);
	}
	public List<Question> findByCriteria(int page, int pagesize,
			String sortname, String sortorder, String criteria)
			throws Exception {
		// TODO Auto-generated method stub
		return questionDao.searchPage(page, pagesize, sortname, sortorder, criteria);
	}
	public boolean addAnswer(Answer answer) throws Exception {
		// TODO Auto-generated method stub
		if(answer!=null&&answer.getAnswerContent()!=null)
		{
			answerDao.save(answer);
			//�޸�����
			Question question = questionDao.findById(answer.getQuestionId());
			question.setQuestionStatus(1);
			return true;
		}
		return false;
	}
	public List<Question> findByClassId(int classId,int type) {
		return questionDao.findByClassId(classId,type);
	}
	
}
