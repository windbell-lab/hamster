package org.windbell.lab.hamster.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil1 {
	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/m_t?useUnicode=true&amp;characterEncoding=UTF-8";
		Connection conn = DriverManager.getConnection(url, "root", "eversec123098");
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
