package cn.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBCP {
	// 获取数据库连接和关系数据库连接
	private static final String DRIVER;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;

	private static BasicDataSource ds;
	static {
		// 从配置文件中读取参数
		Properties prop = new Properties();
		try {
			prop.load(DBCP.class.getResourceAsStream("/db.properties"));
			// 读取参数

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DRIVER = prop.getProperty("driver");
		URL = prop.getProperty("url");
		USERNAME = prop.getProperty("username");
		PASSWORD = prop.getProperty("password");
		String maxActive = prop.getProperty("maxActive");
		String maxIdle = prop.getProperty("maxIdle");
		String minIdle = prop.getProperty("minIdle");
		String maxWait = prop.getProperty("maxWait");

		// 创建数据源对象
		ds = new BasicDataSource();
		// 给数据源设置参数
		ds.setDriverClassName(DRIVER);
		ds.setUrl(URL);
		ds.setUsername(USERNAME);
		ds.setPassword(PASSWORD);

		// 设置池的相关参数
		if (maxActive != null) {
			ds.setMaxActive(Integer.parseInt(maxActive));
		}
		if (maxIdle != null) {
			ds.setMaxIdle(Integer.parseInt(maxIdle));
		}
		if (minIdle != null) {
			ds.setMinIdle(Integer.parseInt(minIdle));
		}
		if (maxWait != null) {
			ds.setMaxWait(Integer.parseInt(maxWait));
		}

	}

	// 获取数据库连接
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	// 关闭数据库连接
	public static void close(ResultSet rs, PreparedStatement ps, Connection ct) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (ct != null) {
				ct.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// main
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}
