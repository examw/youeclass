package com.changh.sccms.until;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DbUtil {
	//����Դ���ӳض���
	private static DataSource dataSource = null;
	//��һ��������̰߳�
	private static ThreadLocal<Connection> connLocal = 
						new ThreadLocal<Connection>();
	
	static{
		try{
		Properties props = new Properties();
		//����db.properties���ò���
		props.load(DbUtil.class.getClassLoader()
				.getResourceAsStream("db.properties"));
		//����dbcp�������dataSource����
		dataSource = BasicDataSourceFactory
					.createDataSource(props);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		//��ȡ�͵�ǰ�߳���ص�connection
		Connection conn = connLocal.get();
		if(conn == null || conn.isClosed()){//���û��,���ѹر�
			conn = dataSource.getConnection();//��ȡ�µ�connection
			connLocal.set(conn);
		}
		return conn;
	}
	
	public static void closeConnection(){
		//��ȡ�͵�ǰ�߳���ص�connection
		Connection conn = connLocal.get();
		//������̰߳󶨵�conn
		connLocal.set(null);//Ϊ��һ�ε���getConnectionҪ�½�
		try {
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
