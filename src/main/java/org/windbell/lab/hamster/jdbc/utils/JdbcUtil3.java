package org.windbell.lab.hamster.jdbc.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil3 {

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

	public static Connection getConnection() throws Exception {
		Class.forName(map.getProperty("db.mysql.driver"));
		Connection conn = DriverManager.getConnection(map.getProperty("db.mysql.url"),
				map.getProperty("db.mysql.user"), map.getProperty("db.mysql.pass"));
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
			} catch (SQLException e) {
			}
	}
}
