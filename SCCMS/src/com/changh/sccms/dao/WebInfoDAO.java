package com.changh.sccms.dao;

import com.changh.sccms.entity.WebInfo;

public interface WebInfoDAO {
	/**
	 * �޸���վ������Ϣ
	 * @param info
	 */
	public void saveOrUpdate(WebInfo info);
	/**
	 * ���һ�����Ϣ
	 * @return
	 */
	public WebInfo findWebInfo();
}
