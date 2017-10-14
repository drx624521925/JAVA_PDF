package com.pdf.andriod.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pdf.entity.File;
import com.pdf.service.OrderService;
import com.pdf.service.andriod.OrderService_andriod;
import com.pdf.serviceImpl.OrderServiceImpl;
import com.pdf.serviceImpl.andriod.OrderServiceImpl_andriod;
import com.pdf.servlet.BaseServlet;
import com.pdf.servlet.LogOperate;
import com.pdf.utils.Utils;

public class orderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static OrderService_andriod orderInstance_andriod = OrderServiceImpl_andriod.getInstance();
	private static OrderService orderInstance = OrderServiceImpl.getInstance();
	//private static UserService userInstance = UserServiceImpl.getInstance();
    
	public void search(HttpServletRequest res,HttpServletResponse resp) throws IOException{
		//int user_id = Integer.parseInt(res.getParameter("user_id"));
	    String order_name = res.getParameter("order_name");
		File result = orderInstance_andriod.search(order_name);
	    JsonObject json = new JsonObject();
	    	if(result!=null){
	    		 json.add("data", new JsonParser().parse(new Gson().toJson(result)).getAsJsonObject());
	    		 json.addProperty("flag", true);
	    	}else{
	    		 json.addProperty("flag", false);
	    	}
	    PrintWriter out = resp.getWriter();
	    out.println(json);
	    out.flush();
	    out.close();
	    System.out.println("search"+result);
	}
	
	public void getOrderHis(HttpServletRequest req,HttpServletResponse res) throws IOException{
		System.out.println("getorderhis");
		String order_id = req.getParameter("order_id");
		List<File> HisList = orderInstance_andriod.getOrderHis(order_id);
		System.out.println(HisList);
		JsonObject json = new JsonObject();
		json.add("result", new JsonParser().parse(new Gson().toJson(HisList)).getAsJsonArray());
		PrintWriter out = res.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}
	
	//�ṩ���Ա �༭Ա��ȡ�����б�           ��ʹ��
	public void getOrderListById(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		System.out.println("getOrderListById");
		String role_id = req.getParameter("role_id");
		int user_id = Integer.valueOf( req.getParameter("user_id") );
		System.out.println(role_id+"  role----user "+user_id);
		List<File> result =null;
		result= orderInstance.getOrderListById(role_id);
		System.out.println(result);
		JsonObject json = new JsonObject();
		
		if(result != null){
			json.add("result", new JsonParser().parse(new Gson().toJson(result)).getAsJsonArray());
			json.addProperty("flag", true);
    	}else{
    			//json.add("data", new JsonParser().parse(new Gson().toJson(result)).getAsJsonArray());
    			json.addProperty("flag", false);
      	}
		
		PrintWriter out = resp.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}
	public void download(HttpServletRequest req,HttpServletResponse resp)throws IOException{
		System.out.println("download");
		
		String order_id = (req.getParameter("order_id"));
		System.out.println(order_id);
		JsonObject json = new JsonObject();
		PrintWriter out = resp.getWriter();
		
		File file1 = orderInstance.SearchOrder(order_id);
		System.out.println("file---"+file1);           //���ж��Ƿ�����
		if(file1.getLocked() == 1 ) {
			json.addProperty("result", false);
			out.println(json);
			out.flush();
			out.close();
			System.out.println("�ļ�������");
			return ;
		}
		
		File file = orderInstance_andriod.download(order_id);
		System.out.println("downloading  file --"+file);
		if(orderInstance.LockOrder(req.getParameter("order_id")) == 0) {  // ������������
			System.out.println("����+"+order_id);
			System.out.println("��������ʧ��");
			return;
		}
		else{
			if(LogOperate.FileLog("���ض���",file) == 1) {
				System.out.println("��¼���سɹ�");
			}
		}
		if(file!=null){
			json.add("data", new JsonParser().parse(new Gson().toJson(file)).getAsJsonObject());
			json.addProperty("result", true);
		}else{
			json.addProperty("result", false);
		}
		out.println(json);
	    out.flush();
	    out.close();
	}
	
	
	public void UnLockOrder(HttpServletRequest req,HttpServletResponse res) throws IOException {
		System.out.println("unlockorder");
		String order_id = req.getParameter("order_id");
		int result = orderInstance.UnLockOrder(order_id);
		PrintWriter out = res.getWriter();
		if(result != 0) {
			out.println("success");
			System.out.println("success");
		}else
			out.println("fail");
		out.flush();
		out.close();
	}
	
	public void DownloadForView(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String order_id = req.getParameter("order_id");
		File result = orderInstance.SearchOrder(order_id); 
		JsonObject json = new JsonObject();
		PrintWriter out = res.getWriter();
		json.add("result", new JsonParser().parse(new Gson().toJson(result)).getAsJsonObject());
		out.println(json);
		out.flush();
		out.close();
	}
	
	public void ReviewOrder(HttpServletRequest req,HttpServletResponse res) throws IOException{
		PrintWriter out = res.getWriter();
		JsonObject json = new JsonObject();
		
		System.out.println("ReviewOrder");
		String user_id = req.getParameter("user_id");
		String order_id = req.getParameter("order_id");
		boolean result = false;
		if(Utils.IsLocked(order_id)) {         //�����ж��Ƿ�����
			json.addProperty("result",result);
			out.println(json);
			System.out.println("�ļ�������");
			out.flush();
			out.close();
			return ;
		}
		
		File file = new File();
		file.setOrder_id(order_id);
		file.setUser_id(Integer.valueOf(user_id));
		result = orderInstance.ReviewOrder(order_id,user_id);
		if(result) {
			System.out.println("��˳ɹ�");
			if( (LogOperate.FileLog("��˶���", file))!= 0  ){
				result = true;
				System.out.println("��¼�ɹ�");
			}else {
				result = false;
				System.out.println("��¼ʧ��");
			}
		}
		//json = new JsonParser().parse(new Gson().toJson(result)).getAsJsonObject();
		json.addProperty("result",result);
		out.println(json);
		out.flush();
		out.close();
	}
	
	public void withdraw(HttpServletRequest req ,HttpServletResponse res) throws IOException {
		String order_id = req.getParameter("order_id");
		String user_id = req.getParameter("user_id");
		String edit_time = req.getParameter("edit_time");
		if(Utils.IsLocked(order_id)) {				//�����ж��Ƿ�����
			System.out.println("�ļ�������");
			PrintWriter out = res.getWriter();
			out.flush();
			out.close();
			return ;
		}
		int flag = orderInstance.Withdraw(order_id,edit_time,user_id);
		File file = orderInstance.SearchOrder(order_id);
		if(flag != 0) {
			flag = LogOperate.FileLog("���ض���", file);
		}
		JsonObject json = new JsonObject();
	//	json = new JsonParser().parse(new Gson().toJson(flag)).getAsJsonObject();
		boolean result = (flag==1);
		json.addProperty("result", result);
		PrintWriter out = res.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}
}
