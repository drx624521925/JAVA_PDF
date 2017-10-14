package com.pdf.dao;

import java.util.List;

import com.pdf.entity.User;

public interface UserDao {
	/**
	 * 
	 * ��������:
	 * @author Searlas
	 * ʱ��2017��7��8������4:30:28
	 * @param user
	 * @return
	 */
	User Login(User user);
	/**
	 * 
	 * ��������:�����û�
	 * @author Searlas
	 * ʱ��2017��7��9������4:02:11
	 * @param user
	 * @return
	 */
	int AddUser(User user);
	/**
	 * 
	 * ��������:ɾ���û�
	 * @author Searlas
	 * ʱ��2017��7��10������2:24:59
	 * @param user_id
	 * @return
	 */
	boolean DeleteUser(String user_id);
	/**
	 * 
	 * ��������:����ɾ���û�
	 * @author Searlas
	 * ʱ��2017��7��10������2:40:13
	 * @param user_list
	 * @return
	 */
	boolean DeleteUserList(String user_list);
	/**
	 * 
	 * ��������:��ȡ�༭Ա����
	 * @author Searlas
	 * ʱ��2017��7��11������7:32:57
	 * @return
	 */
	int getEditorNum();
	/**
	 * 
	 * ��������:��ȡ�༭Ա�б�
	 * @author Searlas
	 * ʱ��2017��7��11������7:49:44
	 * @return
	 */
	List<User> GetEditorList();
	/**
	 * 
	 * ��������:�༭�û���Ϣ
	 * @author Searlas
	 * ʱ��2017��7��12������1:16:57
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
	 * ʱ��2017��7��20������1:13:23
	 * @param user_id
	 * @return
	 */
	User SearchUserById(int user_id);
	User UserDetail(int user_id);

}
