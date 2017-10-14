package com.pdf.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

@SuppressWarnings("static-access")
public class JdbcUtils_DBCP {

	private static DataSource ds = null;

	/**
	 * ��̬������ȡ�����ļ�����ʼ��DBCP���ӳ�
	 */
	static {
		try {
			InputStream in = JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			Properties prop = new Properties();
			prop.load(in);

			BasicDataSourceFactory factory = new BasicDataSourceFactory();

			ds = factory.createDataSource(prop);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 * �������ƣ�getDataSource
	 * ����������
	 * ����˵����
	 * ����ֵ��DataSource
	 * ��ע��
	 * 
	 * ���ߣ�ariclee		ʱ�䣺2016��6��12������1:23:39
	 */
	public static DataSource getDataSource() {
		return ds;
	}

	/**
	 * �������ƣ�getConnection
	 * ����������
	 * ����˵����
	 * ����ֵ��Connection
	 * ��ע��
	 * 
	 * ���ߣ�ariclee		ʱ�䣺2016��5��26������5:41:49
	 */
	public static Connection getConnection() {

		try {
			return ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("JdbcUtils_DBCP--getConnection ERROR!!");
		}
	}
	
	/**
	 * �������ƣ�release
	 * ����������
	 * ����˵����
	 * ����ֵ��void
	 * ��ע��
	 * 
	 * ���ߣ�ariclee		ʱ�䣺2016��5��26������5:41:55
	 */
	public static void release(Connection conn, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (st != null) {
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 *���ߣ�ZhangHuanMing
	 *ʱ�䣺2017��1��1������5:53:25
	 *��������releaseConn
	 *���ܣ������ӷ��ظ����ӳ�
	 *������
	 *����ֵ��void
	 */
	public static void releaseConn( Connection conn){
		try {
			
			if (conn != null && !conn.isClosed()) {
//				conn.setAutoCommit(true);
				conn.close();
				System.out.println("�������Ӹ����ӳ�");
			}
		} catch (SQLException e) {
			System.out.println("�ر����ݿ�����ʧ�ܣ�" + e);
		}
	}
}

