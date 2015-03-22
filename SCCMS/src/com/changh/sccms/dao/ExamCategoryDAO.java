package com.changh.sccms.dao;

import java.util.List;

import com.changh.sccms.entity.ExamCategory;
import com.changh.sccms.entity.ExamCategory1;

public interface ExamCategoryDAO {
		//����hibernate�ļ���
		public List<ExamCategory1> findAll1()throws Exception;
		public List<ExamCategory1> findByPid1(int pid)throws Exception;
		//////////////////////////
		//�ҳ����еĿ���
		public List<ExamCategory> findAll()throws Exception;
		//��ָ��id�Ŀ���
		public ExamCategory  findById(int id)throws Exception;
		//�޸Ŀ�����Ϣ
		public void update(int id, String examName,String examUrl,String examDescription)throws Exception;
		//ɾ��
		public void delete(int examPid,int id)throws Exception;
		//���
		public void save(ExamCategory exam)throws Exception;
		//���ҵ�ǰ Ϊpid������
		public int fingByPid(int examPid) throws Exception;
		//��id����ExamCategory
		public ExamCategory findByIdExamAdd(int id) throws Exception;
		//��pid����ExamCategory
		public List<ExamCategory> findByExamPid(int examPid);
		//����Ƿ�����ڵ�ǰ����������ӷ���
		public boolean checkAdd(int examId);
		/**
		 * ��ÿ������id
		 * @return
		 */
		public int getExamId();
		/**
		 * �����Բ��Ҷ���
		 * @param pro
		 * @param value
		 * @return
		 */
		public List<ExamCategory> findByPro(String pro,Object value);

		
}
