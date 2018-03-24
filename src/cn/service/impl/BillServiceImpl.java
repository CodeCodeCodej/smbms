package cn.service.impl;

import java.util.Map;
import java.util.List;

import cn.dao.BillDao;
import cn.dao.impl.BillDaoImpl;
import cn.entity.PageBean;
import cn.entity.smbms_bill;
import cn.entity.smbms_provider;
import cn.service.BillService;

public class BillServiceImpl implements BillService {
	BillDao bd = new BillDaoImpl();
//查询全部信息
	public java.util.List<Map> List() {
		return bd.List();
	}
	//id查
	public Map<String,Object> IdData(int id) {
		// TODO Auto-generated method stub
		return bd.IdData(id);
	}
	//修改
	public int Update(smbms_bill bill) {
		return bd.UPdate(bill);
	}
	//增加
	public int ADD(smbms_bill bill) {
		return bd.ADD(bill);
	}
	//删除
	public int delete(int id) {
		return bd.delete(id);
	}
	//分页查询
	public PageBean Bean(String productName, int isPayment, int providerId,
			int currentPage, int currentCount) {
		PageBean pageBean = new PageBean<Map>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentCount);
		//总条数
		int totalCount = bd.totalCount( productName,isPayment,providerId);
		pageBean.setTotalCount(totalCount);
	
		//总页数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);
		//每页显示的数据
		int index = (currentPage - 1) * currentCount;
		List<Map> list = bd.BeanList(index,currentCount,productName,isPayment,providerId);
		pageBean.setList(list);
		return pageBean;
	}

}
