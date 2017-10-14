package com.pdf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.pdf.entity.File;
import com.pdf.entity.User;
import com.pdf.service.OrderService;
import com.pdf.serviceImpl.OrderServiceImpl;
import com.pdf.utils.Constant;
import com.pdf.utils.Utils;

@SuppressWarnings("serial")
public class FileAdd extends HttpServlet{		
	
	private static OrderService instance = OrderServiceImpl.getInstance();
       
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		System.out.println("file-add servlet");
		File file = new File();
		HttpSession session = req.getSession();
		User user =  (User) session.getAttribute("user");
		int user_id = user.getUser_id();
		String file_name = null;
		String file_path = null;
		boolean flag = false;
		int newid = 0 ;
		try {
			//1���õ�����������
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2���õ�������
			ServletFileUpload upload = new ServletFileUpload(factory);
			//3��Ϊ�ϴ���������ý����������ϴ�����
			List<FileItem> list = upload.parseRequest(req);//FileItem
			//����list���õ����ڷ�װ��һ���ϴ�����������fileItem����
			newid = Utils.getNewFileId()+1;
			//��ȡ�����ļ�id+1
			for(FileItem item :list){
				if(item.isFormField()){
					//�õ�������ͨ������
					String name = item.getFieldName();//�õ������������
					//
					byte[] buf = item.get();
					String value = new String(buf,"utf-8");
					file.totalSet(name,value);
				}else{
					System.out.println("file loading");
					//�õ��ϴ�������
					file_name = item.getName();
					file_name = file_name.substring(file_name.lastIndexOf("\\")+1);
					//д��ͼƬ��
//					String basicpath = req.getSession().getServletContext().getRealPath("/") ;
//					file_path = basicpath+"/Test/"+file_name;
					file_path = Constant.PdfLocation+newid+".pdf";
					file.setFile_path(newid+".pdf");
					System.out.println("filepath---------"+file_path);
					flag = Utils.writeFile(item.getInputStream(),file_path );
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(flag){
			System.out.println("file upload success!");
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String nowtime = sdf.format(date);  //����id����
			
			Date date2 = new Date();
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowtime2 = sdf2.format(date2); //�޸�ʱ������
			
			file.setOrder_id(nowtime);
			file.setUser_id(user_id);
			file.setEdit_time(0);           //���� �����֮��༭����Ĭ��Ϊ0
			file.setModify_time(nowtime2);   //�����޸�ʱ���¼
			file.setLocked(0);              //���� �����֮������Ĭ��Ϊ0  
			file.setEdition(1);
			file.setOrder_state(1);
			file.setSchedule(0);     //����ļ����ļ�����Ϊ0 ��¼��
			System.out.println("file:"+file.toString());
			System.out.println("insert a new file");
			
			flag = (instance.AddFile(file)!=0);
			if(!flag) {
				System.out.println("����ļ�ʧ��");
			}
			file.setFile_id(newid);         //�޸�ֱ�Ӱ��µ�id�Ž�file��
			file.setSchedule(0);			//�޸���¼���scheduleΪ0
			flag = (LogOperate.FileLog("��Ӷ���",file)!=0);
		}
		PrintWriter out = res.getWriter();
		if(flag){
			out.println("<script> alert(\"��ӳɹ�!\");</script>");
		}else{
			out.println("<script> alert(\"���ʧ��!\");</script>");
		}
		out.flush();
		out.close();
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
