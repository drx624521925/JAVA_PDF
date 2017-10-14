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
public class FileUpload extends HttpServlet{
		
		
	OrderService instance = OrderServiceImpl.getInstance();
	       
		/* (non-Javadoc)
		 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 */
		@SuppressWarnings("unchecked")
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setCharacterEncoding("utf-8");
	    	req.setCharacterEncoding("utf-8");
	    	HttpSession session = req.getSession();
	    	User user =  (User) session.getAttribute("user");
	    	PrintWriter out = res.getWriter();
	    	System.out.println("file-upload servlet");
	    	
	    	
			File file = new File();
			int user_id = user.getUser_id();
			String file_name = null;
			String file_path = null;
			boolean flag = false;
			int LogFlag = 0;
			int newid = 0;
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
						byte[] buf = item.get();
						String value = new String(buf,"utf-8");
						file.totalSet(name,value);
					}else{
						System.out.println("file loading");
						//�õ��ϴ�������
						file_name = item.getName();
						file_name = file_name.substring(file_name.lastIndexOf("\\")+1);
						file_name = file_name.substring(0,file_name.lastIndexOf("."));
						//�������ļ����ƺ��ļ�д��
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
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowtime = sdf.format(date);
				// 1    �����ϴ����ļ���id��ȡ����
				file = instance.GetOrderByFileId(Integer.valueOf(file_name));
				
				if(!Utils.IsLocked(file.getOrder_id())) {         //�����ж��Ƿ�δ����
					System.out.println("�б���δ����");
					out.println("<script> alert(\"�ļ�δ����!\");</script>");
					out.flush();
					out.close();
					return ;
				}
				file.setFile_path(newid+".pdf");
				file.setFile_id(newid);
				file.setModify_time(nowtime);//�����޸�ʱ���¼
				file.setLocked(0);           //��������Ĭ��Ϊ0
				file.setEdit_time(file.getEdit_time()+1);    //�����༭����+1
				file.setUser_id(user_id);  //��¼��������
				file.setEdition(1);
				file.setOrder_state(1);
				file.setSchedule(1);
				// 2    �Ƚ����Ѿ��������ϴ����صĶ���
				int lockflag = instance.UnLockOrder(file.getOrder_id());
				System.out.println("file:"+file.toString());
				System.out.println("upload new file");
				//  3    ���¶���
				System.out.println("���¶�����"+file);
				int file_id = instance.UploadFile(file);
				file.setFile_id(file_id);
				//  4    ��������
				if(lockflag == 0) {
					System.out.println("��������ʧ��");
					out.println("<script> alert(\"��������ʧ��!\");</script>");
					out.flush();
					out.close();
				}
				//  5  ��¼����
				LogFlag = LogOperate.FileLog("�ϴ�����",file);
			}
			if(LogFlag == 1){
				out.println("<script> alert(\"�ϴ��ɹ�!\");</script>");
			}else{
				out.println("<script> alert(\"�ϴ�ʧ��!\");</script>");
			}
			out.flush();
			out.close();
		    
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}
