package cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.dao.BaseDao;
import cn.dao.UserDao;
import cn.entity.smbms_role;
import cn.entity.smbms_user;

public class UserDaoImpl extends BaseDao implements UserDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	smbms_role role = new smbms_role();
	boolean flag;

	public smbms_user login(String userCode, String userPassword) {
		smbms_user user = new smbms_user();
		String sql = "select * from smbms_user where userCode=? and userPassword=?";
		Object[] obj = { userCode, userPassword };
		rs = query(sql, obj);
		try {
			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUserCode(rs.getString("userCode"));
				user.setUserName(rs.getString("userName"));
				user.setUserPassword(rs.getString("userPassword"));
				user.setGender(rs.getInt("gender"));
				user.setBirthday(rs.getDate("birthday"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				user.setUserRole(rs.getInt("userRole"));
				user.setCreatedBy(rs.getInt("createdBy"));
				user.setCreationDate(rs.getDate("creationDate"));
				user.setModifyBy(rs.getInt("modifyBy"));
				user.setModifyDate(rs.getDate("modifyDate"));
				user.setAge(Integer.parseInt(new SimpleDateFormat("yyyy")
						.format(new Date()))
						- Integer.parseInt(new SimpleDateFormat("yyyy")
								.format(rs.getDate("birthday"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return user;
	}

	// 修改密码
	public int UpdatePassword(String userPassword, int id) {
		int num = 0;
		try {
			String sql = "update smbms_user set userPassword=? where id=?";
			Object[] obj = { userPassword, id };
			num = Update(sql, obj);
		} finally {
			close(con, ps, rs);
		}
		return num;
	}

	// 删除信息
	public int Delect(String userCOde) {
		int num = 0;

		try {
			String sql = "delete from smbms_user where userCode=?";
			Object[] obj = { userCOde };
			num = Update(sql, obj);
		} finally {
			close(con, ps, rs);
		}
		return num;
	}

	// 查询用户名是否已经存在
	public boolean userid(String userId) {
		String sql = "select * from smbms_user where userCode=?";
		Object[] obj = { userId };
		rs = query(sql, obj);
		try {
			if (rs.next()) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return flag;
	}

	public int Add(smbms_user user) {
		int num = 0;
		try {
			String sql = "insert  into smbms_user (userCode,userName,userPassword,gender,birthday,phone,address,userRole,createdBy,creationDate) values (?,?,?,?,?,?,?,?,?,?)";
			Object[] obj = { user.getUserCode(), user.getUserName(),
					user.getUserPassword(), user.getGender(),
					user.getBirthday(), user.getPhone(), user.getAddress(),
					user.getUserRole(), user.getCreatedBy(),
					user.getCreationDate() };
			num = Update(sql, obj);
		} finally {
			close(con, ps, rs);
		}
		return num;
	}

	// 根据账号查询信息
	public smbms_user UserCodeUser(String userCode) {
		smbms_user user = new smbms_user();
		String sql = "select * from smbms_user where userCode=?";
		Object[] obj = { userCode };
		rs = query(sql, obj);
		try {
			while (rs.next()) {
				user.setUserCode(rs.getString("userCode"));
				user.setUserName(rs.getString("userName"));
				user.setGender(rs.getInt("gender"));
				user.setBirthday(rs.getDate("birthday"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				user.setUserRole(rs.getInt("userRole"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return user;
	}

	// 修改信息
	public int update(smbms_user user) {
		int num = 0;
		try {
			String sql = "update smbms_user set userName=?,"
					+ "gender=?,birthday=?,phone=?,address=?,userRole=?,modifyDate=?,modifyBy=?"
					+ " where userCode=?";
			Object[] obj = { user.getUserName(), user.getGender(),
					user.getBirthday(), user.getPhone(), user.getAddress(),
					user.getUserRole(), user.getModifyDate(),
					user.getModifyBy(), user.getUserCode() };
			num = Update(sql, obj);
		} finally {
			close(con, ps, rs);
		}
		return num;
	}

	// 查询有多少条
	public int total(String userName) {
		int total = 0;
		if (userName != null && !userName.trim().equals("")) {
			System.out.println(123);
			String sql = "select count(*) from smbms_user,smbms_role where smbms_user.userRole=smbms_role.id and userName like ? ";
			Object[] obj = { "%" + userName + "%" };
			rs = query(sql, obj);
		} else {
			String sql = "select count(*) from smbms_user,smbms_role where smbms_user.userRole=smbms_role.id  ";
			Object[] obj = {};
			rs = query(sql, obj);
		}
		try {
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return total;
	}

	// 分页查询数据
	public List<Map> BeanList(int index, int currentCount, String userName) {
		smbms_user user = new smbms_user();
		List<Map> UserList = new ArrayList<Map>();
		if (userName != null && !userName.trim().equals("")) {
			String sql = "select smbms_user.userCode,smbms_user.userName,smbms_user.gender,smbms_user.birthday,smbms_user.phone,smbms_role.roleName from smbms_user,smbms_role where smbms_user.userRole=smbms_role.id and userName like ? order by userRole ASC limit ?,?";
			Object[] obj = { "%" + userName + "%", index, currentCount };
			rs = query(sql, obj);
		} else {
			String sql = "select smbms_user.userCode,smbms_user.userName,smbms_user.gender,smbms_user.birthday,smbms_user.phone,smbms_role.roleName from smbms_user,smbms_role where smbms_user.userRole=smbms_role.id  order by userRole ASC limit ?,?";
			Object[] obj = { index, currentCount };
			rs = query(sql, obj);
		}
		try {
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 出生年
				Integer dateStr = Integer.parseInt(new SimpleDateFormat("yyyy")
						.format(rs.getDate("birthday")));
				// 当前年
				Integer dateStr1 = Integer
						.parseInt(new SimpleDateFormat("yyyy")
								.format(new Date()));
				int age = user.setAge((dateStr1 - dateStr));
				String roleName = rs.getString("roleName");
				map.put("userCode", rs.getString("userCode"));
				map.put("userName", rs.getString("userName"));
				map.put("gender", rs.getInt("gender"));
				map.put("phone", rs.getString("phone"));
				map.put("roleName", rs.getString("roleName"));
				map.put("age", age);
				UserList.add(map);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return UserList;
	}

}