package org.windbell.lab.hamster.jdbc.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class JdbcUtil2 {
	public static Connection getConnection() throws Exception {
		// 读文件
		FileReader fr = new FileReader("./src/main/resources/jdbc-config.properties");
		BufferedReader br = new BufferedReader(fr);
		HashMap<String, String> map = new HashMap<String, String>();
		while (true) {
			String str = br.readLine();
			if (str == null)
				break;
			String[] ss = str.split("=");
			map.put(ss[0], ss[1]);
		}
		br.close();

		Class.forName(map.get("db.mysql.driver"));
		Connection conn = DriverManager.getConnection(map.get("db.mysql.url"),
				map.get("db.mysql.user"), map.get("db.mysql.pass"));
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
