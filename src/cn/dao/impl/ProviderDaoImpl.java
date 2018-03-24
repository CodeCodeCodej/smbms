package cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.dao.BaseDao;
import cn.dao.ProviderDao;
import cn.entity.smbms_bill;
import cn.entity.smbms_provider;

public class ProviderDaoImpl extends BaseDao implements ProviderDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public List<smbms_provider> List() {
		smbms_provider pro = new smbms_provider();
		List<smbms_provider> list = new ArrayList<smbms_provider>();
		String sql = "select * from smbms_provider ";
		Object[] obj = {};
		rs = query(sql, obj);
		try {
			while (rs.next()) {
				pro = new smbms_provider(rs.getInt("id"),
						rs.getString("proCode"), rs.getString("proName"),
						rs.getString("proDesc"), rs.getString("proContact"),
						rs.getString("proPhone"), rs.getString("proAddress"),
						rs.getString("proFax"), rs.getInt("createdBy"),
						rs.getDate("creationDate"), rs.getDate("modifyDate"),
						rs.getInt("modifyBy"));
				list.add(pro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list;
	}

	// 根据供应商查询信息
	public smbms_provider ProCode(String proCode) {
		smbms_provider pro = new smbms_provider();
		String sql = "select * from smbms_provider where proCode=?";
		Object[] obj = { proCode };
		rs = query(sql, obj);
		try {
			while (rs.next()) {
				pro.setId(rs.getInt("id"));
				pro.setProCode(rs.getString("proCode"));
				pro.setProName(rs.getString("proName"));
				pro.setProContact(rs.getString("proContact"));
				pro.setProPhone(rs.getString("proPhone"));
				pro.setProAddress(rs.getString("proAddress"));
				pro.setProFax(rs.getString("proFax"));
				pro.setProDesc(rs.getString("proDesc"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return pro;
	}

	// 删除
	public int Delect(String proCode) {
		int num = 0;
		try {
			String sql = "delete from smbms_provider  where proCode=?";
			Object[] obj = { proCode };
			num = Update(sql, obj);
		} finally {
			close(con, ps, rs);
		}
		return num;
	}

	// 修改
	public int Updeta(smbms_provider pro) {
		int num = 0;
		try {
			String sql = "update smbms_provider set"
					+ " proCode=?,proName=?,proContact=?,proPhone=?,proAddress=?,proFax=?,proDesc=?,modifyDate=?,modifyBy=?"
					+ " where id=?";
			Object[] obj = { pro.getProCode(), pro.getProName(),
					pro.getProContact(), pro.getProPhone(),
					pro.getProAddress(), pro.getProFax(), pro.getProDesc(),
					pro.getModifyDate(), pro.getModifyBy(), pro.getId() };
			num = Update(sql, obj);
		} finally {
			close(con, ps, rs);
		}
		return num;
	}

	// 增加信息
	public int Add(smbms_provider pro) {
		int num = 0;
		try {
			String sql = "insert into smbms_provider (proCode,proName,proDesc,proContact,proPhone,proAddress,proFax,createdBy,creationDate) values(?,?,?,?,?,?,?,?,?)";
			Object[] obj = { pro.getProCode(), pro.getProName(),
					pro.getProDesc(), pro.getProContact(), pro.getProPhone(),
					pro.getProAddress(), pro.getProFax(), pro.getCreatedBy(),
					pro.getCreationDate() };
			num = Update(sql, obj);
		} finally {
			close(con, ps, rs);
		}
		return num;
	}

	// 分页
	public List<smbms_provider> BeanList(int index, int currentCount,
			String proName) {
		smbms_provider pro = new smbms_provider();
		List<smbms_provider> list = new ArrayList<smbms_provider>();
		String sql = "select * from smbms_provider ";
		if (proName != null && !proName.trim().equals("")) {
			sql += "where proName like ? order by creationDate desc limit ?,?";
			Object[] obj = { "%" + proName + "%", index, currentCount };
			rs = query(sql, obj);
		} else {
			sql += " order by creationDate desc limit ?,?";
			Object[] obj = { index, currentCount };
			rs = query(sql, obj);
		}

		try {
			while (rs.next()) {
				pro = new smbms_provider(rs.getInt("id"),
						rs.getString("proCode"), rs.getString("proName"),
						rs.getString("proDesc"), rs.getString("proContact"),
						rs.getString("proPhone"), rs.getString("proAddress"),
						rs.getString("proFax"), rs.getInt("createdBy"),
						rs.getDate("creationDate"), rs.getDate("modifyDate"),
						rs.getInt("modifyBy"));
				list.add(pro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list;
	}

	// 条数
	public int total(String proName) {
		int total = 0;
		if (proName != null && !proName.trim().equals("")) {
			String sql = "select count(*) from smbms_provider where proName like ? ";
			Object[] obj = { "%" + proName + "%" };
			rs = query(sql, obj);
		} else {
			String sql = "select count(*) from smbms_provider";
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


}
