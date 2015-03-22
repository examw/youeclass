package com.changh.sccms.dao;

import java.util.Date;
import java.util.List;

import com.changh.sccms.entity.StudyCard;

public interface StudyCardDAO {
	//��������
	public void createCards(List<StudyCard> list)throws Exception;
	//�ҳ����еļ�¼
	public List<StudyCard> findAll(String admUsername)throws Exception;
	//�ҳ�date֮�����ɵ����м�¼
	public List<StudyCard> findAll(Date date,String admUsername)throws Exception;
	//�ҳ�������ɵ�N��
	public List<StudyCard> findTopN(int num,String admUsername)throws  Exception;
	//�ҳ���¼���ܸ���
	public long findTotal(String admUsername)throws Exception;
	//��ҳ��ѯ
	public List<StudyCard> findPage(int page,int pagesize,String sortName,String sortorder,String admUsername)throws Exception;
	public List<StudyCard> findTopN(int num, int page, int pagesize,
			String sortName, String sortorder,String admUsername);
	//find by id
	public StudyCard findById(int cardId)throws Exception;
	
	public List<StudyCard> findPageByParams(int page,int pagesize,String sortName,String sortorder,String admUsername,String params)throws Exception;
	public long findTotalByParams(String admUsername,String params)throws Exception;
}
