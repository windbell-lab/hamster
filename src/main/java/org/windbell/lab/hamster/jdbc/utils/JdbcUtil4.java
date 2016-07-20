package org.windbell.lab.hamster.jdbc.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil4 {
	private static Properties map = new Properties();
	static {
		try {
			// 读文件
			InputStream fis = new FileInputStream("./src/main/resources/jdbc-config.properties");
			map.load(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final ThreadLocal<Connection> tlocal = new ThreadLocal<Connection>();

	public static Connection getConnection() throws Exception {
		Connection conn = tlocal.get();
		if (conn == null) {
			Class.forName(map.getProperty("db.mysql.driver"));
			conn = DriverManager.getConnection(
					map.getProperty("db.mysql.url"),
					map.getProperty("db.mysql.user"),
					map.getProperty("db.mysql.pass"));
			tlocal.set(conn);
		}
		return conn;
	}

	public static void close(ResultSet rs, Statement stm, Connection conn) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			}
		if (stm != null)
			try {
				stm.close();
			} catch (SQLException e) {
			}
		if (conn != null)
			try {
				conn.close();
				tlocal.remove();
			} catch (SQLException e) {
			}
	}
	public static void main(String[] args) throws Exception {
		Connection connection = getConnection();
		System.out.println(connection);
		for (int i = 0; i < 10; i++) {
			
			Thread thread = new Thread( new Runnable() {
				
				public void run() {
					Connection connection = null;
					try {
						connection = getConnection();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("int thread: ["+this+"] connection is :["+connection+"]");
					
				}
			});
			
		}
		connection = getConnection();
		System.out.println(connection);
	}
}
