package org.windbell.lab.hamster.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseJdbc {
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		// 1.注册Driver,加载Driver类
		Class.forName("com.mysql.jdbc.Driver");
		// Class.forName("......"); 可以同时注册多个Driver

		// 2.建立数据库连接Connection
		String url = "jdbc:mysql://localhost:3306/m_t?useUnicode=true&amp;characterEncoding=UTF-8";
		String user = "root";
		String password = "eversec123098";
		Connection connection = DriverManager.getConnection(url, user, password);
		// 3.建立Statement对象
		Statement statement = connection.createStatement();
		// 4. 执行sql命令
		String createTableSql = "SET FOREIGN_KEY_CHECKS=0;";
		statement.execute(createTableSql);
		createTableSql = "DROP TABLE IF EXISTS `emp`;";
		statement.execute(createTableSql);
		createTableSql = "CREATE TABLE `emp` ("
				+ "`id` int(11) NOT NULL AUTO_INCREMENT,"
				+ " `name` varchar(6) DEFAULT NULL,"
				+" `age` int(11) DEFAULT NULL," 
				+ "`email` varchar(20) DEFAULT NULL," 
				+ "`address` varchar(255) DEFAULT NULL,"
				+ "PRIMARY KEY (`id`)"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		 statement.execute(createTableSql);
		/* 单条执行*/
		String sql = "insert into emp(id,name,age,email,address) values('1','liucy',30,'liucy1@123.com','beiyuan')";
		boolean execute = statement.execute(sql);
		
		sql="insert into emp(name,age,email,address) values(?,?,?,?)"; 
		/*prepareStatement  批量执行sql*/
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		String[] names={"aaaa","bbbb","cccc"};
		int[]  ages={23,24,25};
		String[] emails={"aaaa@123.com","bbbb@123.com","cccc@123.com"};
		String[] addresses={"aaaa","bbbb","cccc"};
		for (int i = 0; i < names.length; i++) {
			prepareStatement.setString(1, names[i]);
			prepareStatement.setInt(2, ages[i]);
			prepareStatement.setString(3, emails[i]);
			prepareStatement.setString(4, addresses[i]);
			prepareStatement.executeUpdate();
		}
		
		// 5.如果执行select命令，处理结果集ResultSet
		sql=" select  id,name,age,email,address from emp  order by id";
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()){
			 int id=rs.getInt(1);
			 String name=rs.getString("name");
			 System.out.println(id+"-----"+name);
		} 
		// 6.释放资源
		statement.close();
		connection.close();
	}
}
