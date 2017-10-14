package com.pdf.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pdf.entity.File;
import com.pdf.entity.Log;


public interface OrderService {
	
	
	/**
	 * 
	 * ��������:��������
	 * @author Searlas
	 * ʱ��2017��7��9������2:43:28
	 * @param order_id
	 * @return
	 */
	File SearchOrder(String order_id);
	/**
	 * 
	 * ��������:ɾ������
	 * @author Searlas
	 * ʱ��2017��7��9������2:43:38
	 * @param order_id
	 * @return
	 */
	boolean DeleteOrder(String order_id);
	/**
	 * 
	 * ��������:��Ӷ����ļ�
	 * @author Searlas
	 * ʱ��2017��7��11������6:16:25
	 * @param file
	 * @return
	 */
	int AddFile(File file);
	/**
	 * 
	 * ��������:��˶���
	 * @author Searlas
	 * ʱ��2017��7��9������5:25:00
	 * @param order_id
	 * @param judge
	 * @param user_id 
	 * @return
	 */
	boolean ReviewOrder(String order_id, String user_id);
	/**
	 * 
	 * ��������:����ɾ������
	 * @author Searlas
	 * ʱ��2017��7��10������1:20:58
	 * @param orderList
	 * @return
	 */
	boolean DeleteOrderList(String orderList);
	/**
	 * 
	 * ��������:��ȡ�����б�
	 * @author Searlas
	 * ʱ��2017��7��10������3:03:24
	 * @param page
	 * @param order_name
	 * @return
	 */
	List<File> GetOrderList(String page, String order_name);
	/**
	 * 
	 * ��������:��ȡ��������
	 * @author Searlas
	 * ʱ��2017��7��10������3:31:39
	 * @param order_id
	 * @return
	 */
	File getOrderDetail(String order_id);
	/**
	 * 
	 * ��������:��ȡ��־�б�
	 * @author Searlas
	 * ʱ��2017��7��10������8:30:41
	 * @param page
	 * @return
	 */
	List<Log> getLogList(String page);
	/**
	 * 
	 * ��������:��ȡ����˵Ķ����б�
	 * @author Searlas
	 * ʱ��2017��7��10������8:43:09
	 * @param page
	 * @return
	 */
	String AddOrder(HttpServletRequest req);
	/**
	 * 
	 * ��������:����ɾ����־
	 * @author Searlas
	 * ʱ��2017��7��12������9:26:56
	 * @param loglist
	 * @return
	 */
	boolean DeleteLogList(String loglist);
	/**
	 * 
	 * ��������:ɾ����־
	 * @author Searlas
	 * ʱ��2017��7��12������10:12:37
	 * @param log_id
	 * @return
	 */
	boolean DeleteLog(String log_id);
	/**
	 * 
	 * ��������:��ȡ�Ѿ���˵Ķ���
	 * @author Searlas
	 * ʱ��2017��7��12������10:22:05
	 * @param page
	 * @return
	 */
	List<File> getReviewEdOrderList(String page);
	int getOrderNumber(String order_name);
	/**
	 * 
	 * ��������:��ȡ�¶������ļ���id�Դ���ļ�
	 * @author Searlas
	 * ʱ��2017��7��14������7:35:23
	 * @return
	 */
	int getNewFileId();

	int getLogNumber();

	/**
	 * 
	 * ��������:��ȡ����������ʷ�ļ�
	 * @author Searlas
	 * ʱ��2017��7��15������5:20:08
	 * @param order_id
	 * @return
	 */
	List<File> getOrderHis(String order_id);
	/**
	 * 
	 * ��������:�޸Ķ�������
	 * @author Searlas
	 * ʱ��2017��7��15������5:25:05
	 * @param order_id
	 * @param schedule
	 * @return
	 */
//	int UpdateOrder(String order_id, String schedule,String user_id);
	/**
	 * 
	 * ��������:�ύ�¶����ļ�
	 * @author Searlas
	 * ʱ��2017��7��15������7:05:59
	 * @param file
	 * @return
	 */
	int SubmitFile(File file);
	/**
	 * 
	 * ��������:���ݽ�ɫ�ͱ༭id��ȡ�����б�
	 * @author Searlas
	 * ʱ��2017��7��19������10:52:10
	 * @param edit_id
	 * @param user_id
	 * @return
	 */
	List<File> getOrderListById(String edit_id);
	List<File> getSchedulList(String page);
	/**
	 * 
	 * ��������:��������
	 * @author Searlas
	 * ʱ��2017��9��15������10:27:39
	 * @param order_id
	 * @return
	 */
	int LockOrder(String order_id);
	/**
	 * 
	 * ��������:��������
	 * @author Searlas
	 * ʱ��2017��9��15������10:31:02
	 * @param order_id
	 * @return
	 */
	int UnLockOrder(String order_id);
	/**
	 * 
	 * ��������:�����ļ�id��ȡ����
	 * @author Searlas
	 * ʱ��2017��9��15������10:43:09
	 * @param file_id
	 * @return
	 */
	File GetOrderByFileId(int file_id);
	/**
	 * 
	 * ��������:�ϴ��޸ĵ��ļ�
	 * @author Searlas
	 * ʱ��2017��9��16������10:09:14
	 * @param file
	 * @return
	 */
	int UploadFile(File file);
	/**
	 * 
	 * ��������:���ض���״̬
	 * @author Searlas
	 * ʱ��2017��9��18������2:50:05
	 * @param order_id
	 * @param edit_time
	 * @param user_id
	 * @return
	 */
	int Withdraw(String order_id, String edit_time, String user_id);
	/**
	 * 
	 * ��������:�ж�һ�������Ƿ��Ѿ�����
	 * @author Searlas
	 * ʱ��2017��9��20������6:51:54
	 * @param order
	 * @return
	 */
	boolean IsLocked(String order_id);
	int getReviewNumber();

	
	
}
