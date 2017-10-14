package com.pdf.dao;

import java.util.List;

import com.pdf.entity.File;
import com.pdf.entity.Log;

public interface OrderDao {

	/**
	 * 
	 * ��������:��������
	 * @author Searlas
	 * ʱ��2017��7��9������1:31:36
	 * @param order_id
	 * @return
	 */
	
	File SearchOrder(String order_id);
	/**
	 * 
	 * ��������:ɾ������
	 * @author Searlas
	 * ʱ��2017��7��9������2:41:31
	 * @param orderId
	 * @return
	 */
	boolean DeleteOrder(String orderId);
	/**
	 * 
	 * ��������:��˶���
	 * @author Searlas
	 * ʱ��2017��7��9������5:27:00
	 * @param order_id
	 * @param judge
	 * @param user_id 
	 * @return
	 */
//	boolean ReviewOrder(String order_id, int judge, String user_id);
	boolean ReviewOrder(String order_id, String user_id);
	/**
	 * 
	 * ��������:����ɾ������
	 * @author Searlas
	 * ʱ��2017��7��10������1:22:25
	 * @param orderList
	 * @return
	 */
	boolean DeleteOrderList(String orderList);
	/**
	 * 
	 * ��������:��ȡ�����б�
	 * @author Searlas
	 * ʱ��2017��7��10������3:04:03
	 * @param page
	 * @param order_name
	 * @return
	 */
	List<File> GetOrderList(String page, String order_name);
	/**
	 * 
	 * ��������:��ȡ��������
	 * @author Searlas
	 * ʱ��2017��7��10������3:33:28
	 * @param order_id
	 * @return
	 */
	File GetOrderDetail(String order_id);
	/**
	 * 
	 * ��������:��ȡ��־�б�
	 * @author Searlas
	 * ʱ��2017��7��10������8:31:51
	 * @param page
	 * @return
	 */
	List<Log> getLogList(String page);
	 /**
	  * 
	  * ��������:��Ӷ����ļ�
	  * @author Searlas
	  * ʱ��2017��7��11������6:15:55
	  * @param file
	  * @return
	  */
	int AddFile(File file);
	/**
	 * 
	 * ��������:����ɾ����־
	 * @author Searlas
	 * ʱ��2017��7��12������9:28:11
	 * @param loglist
	 * @return
	 */
	boolean DeleteLogList(String loglist);
	/**
	 * 
	 * ��������:ɾ����־
	 * @author Searlas
	 * ʱ��2017��7��12������10:13:22
	 * @param log_id
	 * @return
	 */
	boolean DeleteLog(String log_id);
	/**
	 * 
	 * ��������:��ȡ�Ѿ���˶����б�
	 * @author Searlas
	 * ʱ��2017��7��12������10:22:49
	 * @param page
	 * @return
	 */
	List<File> getReviewEdOrderList(String page);
	int getOrderNumber(String order_name);
	/**
	 * 
	 * ��������:��ȡ�¶������ļ���id�Դ���ļ�
	 * @author Searlas
	 * ʱ��2017��7��14������7:37:41
	 * @return
	 */
	int getNewFileId();
	int getLogNumber();

	/**
	 * 
	 * ��������:��ȡ����������ʷ�ļ�
	 * @author Searlas
	 * ʱ��2017��7��15������5:21:00
	 * @param order_id
	 * @return
	 */
	List<File> getOrderHis(String order_id);
	/**
	 * 
	 * ��������:�ύ�¶����ļ�
	 * @author Searlas
	 * ʱ��2017��7��15������7:06:42
	 * @param file
	 * @return
	 */
	int SubmitFile(File file);
	/**
	 * 
	 * ��������:���ص�����ĳ��״̬
	 * @author Searlas
	 * ʱ��2017��7��16������4:05:23
	 * @param order_id
	 * @param edit_time
	 * @return
	 */
	int WithDraw(String order_id, String edit_time,String user_id);
	/**
	 * 
	 * ��������:���ݱ༭id��ȡ�����б�
	 * @author Searlas
	 * ʱ��2017��7��19������10:53:59
	 * @param edit_id
	 * @return
	 */
	List<File> getOrderListById(String edit_id);
	List<File> getSchedulList(String page);
	/**
	 * 
	 * ��������:���Ҷ��������ļ��Ƿ����
	 * @author Searlas
	 * ʱ��2017��7��22������3:50:53
	 * @param order_id
	 * @param schedule1
	 * @return
	 */
	File SearchOrderBySchedule(String order_id, int schedule1);
	/**
	 * 
	 * ��������:��������
	 * @author Searlas
	 * ʱ��2017��9��15������10:27:27
	 * @param order_id
	 * @return
	 */
	int LockOrder(String order_id);
	/**
	 * 
	 * ��������:��������
	 * @author Searlas
	 * ʱ��2017��9��15������10:31:43
	 * @param order_id
	 * @return
	 */
	int UnLockOrder(String order_id);
	/**
	 * 
	 * ��������:�����ļ�id��ȡ����
	 * @author Searlas
	 * ʱ��2017��9��15������10:45:07
	 * @param file_id
	 * @return
	 */
	File GetOrderByFileId(int file_id);
	/**
	 * 
	 * ��������:�ϴ��޸ĵ��ļ�
	 * @author Searlas
	 * ʱ��2017��9��16������10:09:57
	 * @param file
	 * @return
	 */
	int UplaodFile(File file);
	/**
	 * 
	 * ��������:�ж�һ�������Ƿ�����
	 * @author Searlas
	 * ʱ��2017��9��20������6:53:50
	 * @param order_id
	 * @return
	 */
	boolean IsLocked(String order_id);
	int getReviewNumber();


	
	
	

}
