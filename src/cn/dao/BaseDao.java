package cn.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

import cn.utils.DBCP;

public class BaseDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
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

	// 增删改、
	public int Update(String sql, Object[] obj) {
		int num = 0;
		try {
			// 获取连接
			con = getConnection();
			// 放SQL语句
			ps = con.prepareStatement(sql);
			// 遍历Object数组
			for (int i = 0; i < obj.length; i++) { 
				ps.setObject(i + 1, obj[i]);
			}
			num = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	// 查询
	public ResultSet query(String sql, Object[] obj) {
		try {
			// 获取连接
			con = getConnection();
			ps = con.prepareStatement(sql);
			if (obj != null) {
				for (int i = 0; i < obj.length; i++) {
					ps.setObject(i + 1, obj[i]);
				}
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;

	}

	// 关闭流
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
