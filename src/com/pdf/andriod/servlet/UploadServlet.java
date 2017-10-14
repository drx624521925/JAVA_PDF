package com.pdf.andriod.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.pdf.entity.File;
import com.pdf.service.OrderService;
import com.pdf.serviceImpl.OrderServiceImpl;
import com.pdf.servlet.LogOperate;
import com.pdf.utils.Constant;
import com.pdf.utils.Utils;
@SuppressWarnings("serial")
@WebServlet("/upload")
public class UploadServlet extends HttpServlet{		
	
	private static OrderService instance = OrderServiceImpl.getInstance();

       
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("upload servlet");
		String file_name = "";
		String file_path = "";
		File file = new File();
		boolean flag = false;
		int LogFlag = 0;
		int user_id =0;
		String order_id = "";
		user_id = Integer.valueOf(req.getParameter("user_id"));
		order_id = req.getParameter("order_id");
		System.out.println("user_id----"+user_id+" "+order_id+" =--order_id");
		PrintWriter out = res.getWriter();
		int newid = Utils.getNewFileId();
		try {
			//1���õ�����������
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2���õ�������
			ServletFileUpload upload = new ServletFileUpload(factory);
			//3��Ϊ�ϴ���������ý����������ϴ�����
			List<FileItem> list = upload.parseRequest(req);//FileItem
			//����list���õ����ڷ�װ��һ���ϴ�����������fileItem����
			
			//��ȡ�����ļ�id+1
			for(FileItem item :list){
				if(item.isFormField()){
					System.out.println(item.toString());
					//�õ�������ͨ������
					String name = item.getFieldName();//�õ������������
					System.out.println("name"+name);
					//
					byte[] buf = item.get();
					String value = new String(buf,"utf-8");
					System.out.println("value"+value);
					
					if(name.equals("user_id")){
						user_id =Integer.valueOf( value);
						System.out.println("user_id"+user_id);
					}else if(name.equals("order_id")){
						System.out.println("order_id"+order_id);
						order_id = value;
					}

				}else{
					System.out.println("file loading");
					//�õ��ϴ�������
					file_name = item.getName();
					file_name = file_name.substring(file_name.lastIndexOf("\\")+1);
					System.out.println("file"+file_name);
					file_path = Constant.PdfLocation+newid+".pdf";
					file.setFile_path(newid+".pdf");
					System.out.println(file_path);
					System.out.println(file);
					flag = Utils.writeFile(item.getInputStream(),file_path );
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(flag){
			System.out.println("file upload success!");
			file = instance.SearchOrder(order_id);
			if(!Utils.IsLocked(order_id)) {         //�����ж��Ƿ�δ����
				System.out.println("�б���δ����");
				out.flush();
				out.close();
				return ;
			}
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowtime = sdf.format(date);
			file.setFile_path(newid+".pdf");
			                                                  //ɾ�����޸Ľ���
			file.setEdition(1);
			int flag_unlock = instance.UnLockOrder(order_id);                   //�����Ȱ�֮ǰ�������ļ���������
			if(flag_unlock == 0) {
				System.out.println("���ļ�����ʧ��");
			}
			file.setEdit_time(file.getEdit_time()+1);         //����һ�α༭����
			file.setModify_time(nowtime);                      //�����޸�ʱ���¼
			file.setLocked(0);
			//file.setFile_path(file_path);
			file.setUser_id(user_id);// �����ύ����Ա
			//�ύ����File����
			System.out.println("�ύ����");
			System.out.println(file);
			int file_id = instance.SubmitFile(file);
			file.setFile_id(file_id);
			//��¼�ύ��������
			System.out.println("��¼����");
			LogFlag = LogOperate.FileLog("�ύ����",file);
		}
		if(LogFlag == 1){
			System.out.println("��¼�ɹ�");
			out.println(1);
		}else{
			out.println(0);
		}
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
