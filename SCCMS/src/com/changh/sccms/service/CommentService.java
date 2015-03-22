package com.changh.sccms.service;

import java.util.List;

import com.changh.sccms.entity.Comment;



public interface CommentService {
	/**
	 * ��ҳ��������
	 * @param tchId
	 * @param page
	 * @param pagesize
	 * @param sortname
	 * @param sortorder
	 * @return
	 */
	public List<Comment> findByTchId(int tchId,int page,int pagesize,String sortname,String sortorder );
	/**
	 *��ʦ�����ܵ�����
	 * @param tchId
	 * @return
	 */
	public int findTotal(int tchId);
	/**
	 * ͳ����ʦ������
	 */
	public int findSorce(int tchId);
	/**
	 * �������
	 * @param commet
	 */
	public void save(Comment comment);
	/**
	 * ����
	 * @return
	 */
	public int getCommentId();
}
