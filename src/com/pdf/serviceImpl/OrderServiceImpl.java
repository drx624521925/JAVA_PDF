package com.pdf.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pdf.dao.OrderDao;
import com.pdf.entity.File;
import com.pdf.entity.Log;
import com.pdf.factory.DaoFactory;
import com.pdf.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private static OrderServiceImpl instance = new OrderServiceImpl();
	private  OrderDao daoInstance = (OrderDao) DaoFactory.getInstance().getBean(OrderDao.class);
	
	public static OrderServiceImpl getInstance() {
		return instance;
	}
	
	
	@Override
	public File SearchOrder(String order_id) {
		return daoInstance.SearchOrder(order_id);
	}


	@Override
	public boolean DeleteOrder(String order_id) {
		return daoInstance.DeleteOrder(order_id);
	}

	/*@Override
	public boolean ReviewOrder(String order_id, int schedule,String user_id) {
		// TODO Auto-generated method stub
		return daoInstance.ReviewOrder(order_id,schedule,user_id);
	}*/
	@Override
	public boolean ReviewOrder(String order_id,String user_id) {
		// TODO Auto-generated method stub
		return daoInstance.ReviewOrder(order_id,user_id);
	}

	@Override
	public boolean DeleteOrderList(String orderList) {
		// TODO Auto-generated method stub
		return daoInstance.DeleteOrderList(orderList);
	}


	@Override
	public List<File> GetOrderList(String page,String order_name) {
		return daoInstance.GetOrderList(page,order_name);
	}


	@Override
	public File getOrderDetail(String order_id) {
		return daoInstance.GetOrderDetail(order_id);
	}


	@Override
	public List<Log> getLogList(String page) {
		return daoInstance.getLogList(page);
	}


	@Override
	public String AddOrder(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int AddFile(File file) {
		return daoInstance.AddFile(file);
	}


	@Override
	public boolean DeleteLogList(String loglist) {
		return daoInstance.DeleteLogList(loglist);
	}


	@Override
	public boolean DeleteLog(String log_id) {
		return daoInstance.DeleteLog(log_id);
	}


	@Override
	public List<File> getReviewEdOrderList(String page) {
		return daoInstance.getReviewEdOrderList(page);
	}


	@Override
	public int getOrderNumber(String order_name) {
		int number = daoInstance.getOrderNumber(order_name);
		return number;
	}


	@Override
	public int getNewFileId() {
		
		return daoInstance.getNewFileId();
	}


	@Override
	public int getLogNumber() {
		int number = daoInstance.getLogNumber();
		return number;
	}


	@Override
	public List<File> getOrderHis(String order_id) {
		return daoInstance.getOrderHis(order_id);
	}

	////////////////////////////��ʹ��
	/*@Override
	public int UpdateOrder(String order_id, String schedule,String user_id) {
		File file = daoInstance.SearchOrder(order_id);
		file.setUser_id(Integer.valueOf(user_id));
		System.out.println("find file---"+file);
		int schedule1 = Integer.valueOf(schedule);
		System.out.println("�޸�Ϊschedule---"+schedule);
		if(schedule1 == file.getSchedule()) {
			System.out.println("״̬��ͬ �Ķ�ʧ��");
			return -1;
		}else if(schedule1 > file.getSchedule()) {
			System.out.println("����״̬��");
			//��¼
			LogOperate.FileLog("��������״̬", file);
			
			//����File��file_id���þ�file��file_id  �������µ��ļ�
			return daoInstance.JumpOver(file,schedule1);
		}else {
			//���ز���
			System.out.println("����״̬");
			
			if(daoInstance.SearchOrderBySchedule(order_id,schedule1)==null) {
				System.out.println("����ʧ��--�Ҳ�����ѡ״̬");
				return 0;
			}else {
				//��¼
				LogOperate.FileLog("����״̬", file);
			//	return daoInstance.WithDraw(order_id,schedule1,user_id);
				return 0;
			}
		}
			
	}*/


	@Override
	public int SubmitFile(File file) {
		return daoInstance.SubmitFile(file);
	}


	public List<File> GetScheduleList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<File> getOrderListById(String role_id) {
		return daoInstance.getOrderListById(role_id);
	}


	@Override
	public List<File> getSchedulList(String page) {
		return daoInstance.getSchedulList(page);
	}


	@Override
	public int LockOrder(String order_id) {
		return daoInstance.LockOrder(order_id); 
	}


	@Override
	public int UnLockOrder(String order_id) {
		return daoInstance.UnLockOrder(order_id);
	}


	@Override
	public File GetOrderByFileId(int file_id) {
		return daoInstance.GetOrderByFileId(file_id);
	}


	@Override
	public int UploadFile(File file) {
		return daoInstance.UplaodFile(file);
	}


	@Override
	public int Withdraw(String order_id, String edit_time, String user_id) {
		return daoInstance.WithDraw(order_id, edit_time, user_id);
	}


	@Override
	public boolean IsLocked(String  order_id) {
		return daoInstance.IsLocked(order_id);
	}


	@Override
	public int getReviewNumber() {
		int number = daoInstance.getReviewNumber();
		return number;
	}


	




}
