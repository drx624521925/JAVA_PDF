package com.pdf.dao;

import com.pdf.entity.File;

public interface LogDao {
	/**
	 * 
	 * ��������:��¼���ļ�����
	 * @author Searlas
	 * ʱ��2017��7��10������10:36:32
	 * @param operate_desc
	 * @param nowdate 
	 * @param file_id
	 * @param user
	 * @return
	 */
	boolean FileLog(String operate_desc	,File file, String nowdate);
	/**
	 * 
	 * ��������:��¼���û�����
	 * @author Searlas
	 * ʱ��2017��7��10������10:38:27
	 * @param operate_desc
	 * @param user_id
	 * @param nowdate 
	 * @param user
	 * @return
	 */
	boolean UserLog(String operate_desc,int user_id, String nowdate);

}
