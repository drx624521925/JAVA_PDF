package com.pdf.service;

import java.util.List;

import com.pdf.entity.User;

public interface UserService {
	/**
	 * 
	 * ��������:��½
	 * @author Searlas
	 * ʱ��2017��7��8������4:15:27
	 * @param user
	 * @return
	 */
	User Login(User user);
	/**
	 * 
	 * ��������:�����û�
	 * @author Searlas
	 * ʱ��2017��7��9������4:01:31
	 * @param user
	 * @return
	 */
	int AddUser(User user);
	/**
	 * 
	 * ��������:ɾ���û�
	 * @author Searlas
	 * ʱ��2017��7��10������1:47:19
	 * @param user_id
	 * @return
	 */
	boolean DeleteUser(String user_id);
	/**
	 * 
	 * ��������:����ɾ���û�
	 * @author Searlas
	 * ʱ��2017��7��10������2:24:04
	 * @param user_list
	 * @return
	 */
	boolean DeleteUserList(String user_list);
	/**
	 * 
	 * ��������:��ȡ�༭Ա����
	 * @author Searlas
	 * ʱ��2017��7��11������7:31:06
	 * @return
	 */
	int getEditorNum();
	/**
	 * 
	 * ��������:��ȡ�༭Ա�б�
	 * @author Searlas
	 * ʱ��2017��7��11������7:49:11
	 * @return
	 */
	List<User> GetEditorList();
	/**
	 * 
	 * ��������:�༭�û���Ϣ
	 * @author Searlas
	 * ʱ��2017��7��12������1:16:10
	 * @param user
	 * @return
	 */
	boolean EditUser(User user);
	List<User> getUserList(String page, String user_name);
	int getUserNumber(String user_name);
	/**
	 * 
	 * ��������:����id�����û�
	 * @author Searlas
	 * ʱ��2017��7��20������1:12:15
	 * @param user_id
	 * @return
	 */
	User SearchUserById(int user_id);
	User UserDetail(int user_id);
	
}
