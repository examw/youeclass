package com.changh.sccms.service;

import com.changh.sccms.entity.WebInfo;

public interface WebInfoService {
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
