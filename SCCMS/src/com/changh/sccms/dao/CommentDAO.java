package com.changh.sccms.dao;

import java.util.List;

import com.changh.sccms.entity.Comment;



public interface CommentDAO {
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
	 * @param comment
	 */
	public void save(Comment comment);
	/**
	 * ����
	 */
	public int getCommentId();
}
