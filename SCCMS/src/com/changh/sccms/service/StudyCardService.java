package com.changh.sccms.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.changh.sccms.entity.StudyCard;

public interface StudyCardService {
	/**
	 * ����ѧϰ����ҵ�񷽷�
	 * @param num			��������
	 * @param value			��ֵ
	 * @param date			����ʱ��
	 * @param pwdNum		���볤��
	 * @param isPresent		�Ƿ�����
	 * @throws Exception
	 */
	public void createCards(int num,int value,Date month,int pwdNum,int isPresent,String prefix)throws Exception;
	/**
	 * �ҳ����м�¼
	 * @return ���м�¼�ļ���
	 * @throws Exception
	 */
	public List<StudyCard> findAll()throws Exception;
	/**
	 * �ҳ�ָ������֮�����ɵ����м�¼
	 * @param date ָ��������
	 * @return ���ϵļ�¼����
	 * @throws Exception
	 */
	public List<StudyCard> findAll(Date date)throws Exception;
	/**
	 * �ҳ�������ɵ�ǰN����¼
	 * @param num ����N
	 * @return ǰN����¼�ļ���
	 * @throws Exception
	 */
	public List<StudyCard> findTopN(int num) throws Exception;
	public List<StudyCard> findTopN(int num,int page,int pagesize,String sortName,String sortorder) throws Exception;
	/**
	 * ��ҳ��ѯ
	 * @param page		�ڼ�ҳ
	 * @param pagesize  ÿҳ������
	 * @param sortName  ��������
	 * @param sortorder ����or����
	 * @return   		����Ҫ��ļ�¼�ļ��ϣ���װ��map��
	 * @throws Exception
	 */
	public Map<String,Object> findPage(int page,int pagesize,String sortName,String sortorder)throws Exception;
	/**
	 * ���Excel������
	 * @param list ��Ҫд��excel�ļ���
	 * @return һ��������
	 * @throws Exception
	 */
	public InputStream getExcelInputStream( List<StudyCard> list) throws Exception;
	
	public StudyCard findById(int cardId)throws Exception;
	
	public Map<String,Object> findPageByParam(int page,int pagesize,String sortName,String sortorder,String params)throws Exception;
	
	public Map<String,Object> findRecord(int cardId)throws Exception;
}
