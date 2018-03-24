package cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.dao.BaseDao;
import cn.dao.BillDao;
import cn.entity.smbms_bill;
import cn.entity.smbms_provider;

public class BillDaoImpl extends BaseDao implements BillDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	smbms_provider pro = new smbms_provider();

	// 查询信息
	public java.util.List<Map> List() {
		List<Map> list = new ArrayList<Map>();
		String sql = "select smbms_bill.id,smbms_bill.billCode,smbms_bill.productName,smbms_bill.totalPrice,smbms_bill.isPayment,smbms_bill.creationDate,smbms_provider.proName,smbms_bill.providerId from smbms_bill,smbms_provider where  smbms_bill.providerId=smbms_provider.id";
		Object[] obj = {};
		rs = query(sql, obj);
		try {
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", rs.getInt("id"));
				map.put("billCode", rs.getString("billCode"));
				map.put("productName", rs.getString("productName"));
				map.put("proName", rs.getString("proName"));
				map.put("totalPrice", rs.getBigDecimal("totalPrice"));
				map.put("isPayment", rs.getInt("isPayment"));
				map.put("creationDate", rs.getDate("creationDate"));
				map.put("providerId", rs.getInt("providerId"));
				list.add(map);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list;
	}

	// id查询
	public Map<String, Object> IdData(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		//String sql = "select smbms_bill.billCode,smbms_bill.id,smbms_bill.productName,smbms_bill.productUnit,smbms_bill.productCount,smbms_bill.totalPrice,smbms_bill.isPayment,smbms_provider.proName,smbms_bill.providerId from smbms_bill,smbms_provider where smbms_bill.providerId=smbms_provider.id and smbms_bill.id=?";
		String sql = "select smbms_bill.billCode,smbms_bill.id,smbms_bill.productName,smbms_bill.productUnit,smbms_bill.productCount,smbms_bill.totalPrice,smbms_bill.isPayment,smbms_provider.proName,smbms_bill.providerId from smbms_bill left join smbms_provider on smbms_bill.providerId=smbms_provider.id where smbms_bill.id=?";
		Object[] obj = { id };
		rs = query(sql, obj);
		try {
			while (rs.next()) {
				map.put("billCode", rs.getString("billCode"));
				map.put("id", rs.getInt("id"));
				map.put("productName", rs.getString("productName"));
				map.put("productUnit", rs.getString("productUnit"));
				map.put("productCount", rs.getBigDecimal("productCount"));
				map.put("totalPrice", rs.getBigDecimal("totalPrice"));
				map.put("isPayment", rs.getInt("isPayment"));
				map.put("proName", rs.getString("proName"));
				map.put("providerId", rs.getString("providerId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return map;
	}

	// 修改
	public int UPdate(smbms_bill bill) {
		int num = 0;
		try {
			String sql = "update smbms_bill set "
					+ "billCode=?,productName=?,productUnit=?,productCount=?,totalPrice=?,isPayment=?,providerId=?,modifyBy=?,modifyDate=? "
					+ " where id=?";
			Object[] obj = { bill.getBillCode(), bill.getProductName(),
					bill.getProductUnit(), bill.getProductCount(),
					bill.getTotalPrice(), bill.getIsPayment(),
					bill.getProviderId(), bill.getModifyBy(),
					bill.getModifyDate(), bill.getId() };
			num = Update(sql, obj);
		} finally {
			close(con, ps, rs);
		}
		return num;
	}

	public int ADD(smbms_bill bill) {
		int num = 0;
		try {
			String sql = "insert into smbms_bill (billCode,productName,productUnit,productCount,totalPrice,providerId,isPayment,createdBy,creationDate) values(?,?,?,?,?,?,?,?,?)";
			Object[] obj = { bill.getBillCode(), bill.getProductName(),
					bill.getProductUnit(), bill.getProductCount(),
					bill.getTotalPrice(), bill.getProviderId(),
					bill.getIsPayment(), bill.getCreatedBy(),
					bill.getCreationDate() };
			num = Update(sql, obj);
		} finally {
			close(con, ps, rs);
		}
		return num;
	}

	// 删除
	public int delete(int id) {
		int num = 0;
		try {
			String sql = "delete from smbms_bill where id=?";
			Object[] obj = { id };
			num = Update(sql, obj);
		} finally {
			close(con, ps, rs);
		}
		return num;
	}

	// 分页模糊查询
	public java.util.List<Map> BeanList(int index, int currentCount,
			String productName, int isPayment, int providerId) {
		List<Map> list = new ArrayList<Map>();
		// 设置一个容器
		List<Object> RQlist = new ArrayList<Object>();
	/*	String sql = "select smbms_bill.id,smbms_bill.billCode,smbms_bill.productName,smbms_bill.totalPrice,"
				+ "smbms_bill.isPayment,smbms_bill.creationDate,smbms_provider.proName,smbms_bill.providerId from smbms_bill,smbms_provider "
				+ " where  smbms_bill.providerId=smbms_provider.id";*/
			String sql = "select smbms_bill.id,smbms_bill.billCode,smbms_bill.productName,smbms_bill.totalPrice,"
				+ "smbms_bill.isPayment,smbms_bill.creationDate,smbms_provider.proName,smbms_bill.providerId from smbms_bill left join smbms_provider on"
				+ " smbms_bill.providerId=smbms_provider.id where 1=1";
		if (!productName.equals("")) {
			sql += " and productName like ?";
			RQlist.add("%" + productName + "%");
		}
		if (isPayment != 0) {
			sql += " and isPayment=?";
			RQlist.add(isPayment);
		}
		if (providerId != 0) {
			sql += " and providerId=?";
			RQlist.add(providerId);
		}
		sql += " order by creationDate desc limit ?,?";
		RQlist.add(index);
		RQlist.add(currentCount);
		Object[] obj = RQlist.toArray();
		rs = query(sql, obj);
		try {
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", rs.getInt("id"));
				map.put("billCode", rs.getString("billCode"));
				map.put("productName", rs.getString("productName"));
				map.put("proName", rs.getString("proName"));
				map.put("totalPrice", rs.getBigDecimal("totalPrice"));
				map.put("isPayment", rs.getInt("isPayment"));
				map.put("creationDate", rs.getDate("creationDate"));
				map.put("providerId", rs.getInt("providerId"));
				list.add(map);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list;
	}

	// 总条数
	public int totalCount(String productName, int isPayment, int providerId) {
		int totalCount = 0;
		// 设置一个容器
		List<Object> RQlist = new ArrayList<Object>();
		String sql = "select count(*) from smbms_bill left join smbms_provider on "
				+ "smbms_bill.providerId=smbms_provider.id where 1=1";
		if (!productName.equals("")) {
			sql += " and productName like ?";
			RQlist.add("%" + productName + "%");
		}
		if (isPayment != 0) {
			sql += " and isPayment=?";
			RQlist.add(isPayment);
		}
		if (providerId != 0) {
			sql += " and providerId=?";
			RQlist.add(providerId);
		}
		Object[] obj = RQlist.toArray();
		rs = query(sql, obj);
		try {
			while (rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return totalCount;
	}

	public List<smbms_bill> list(int providerId) {
		smbms_bill bill = new smbms_bill();
		List<smbms_bill> list = new ArrayList<smbms_bill>();
		String sql = "select * from smbms_bill where providerId=?";
		Object[] obj = { providerId };
		rs = query(sql, obj);
		try {
			while(rs.next()){
				bill = new smbms_bill(rs.getInt("id"),
						rs.getString("billCode"), rs.getString("productName"),
						rs.getString("productDesc"),
						rs.getString("productUnit"),
						rs.getBigDecimal("productCount"),
						rs.getBigDecimal("totalPrice"), rs.getInt("isPayment"),
						rs.getInt("createdBy"), rs.getDate("creationDate"),
						rs.getInt("modifyBy"), rs.getDate("modifyDate"),
						rs.getInt("providerId"));
				list.add(bill);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list;
	}
}
