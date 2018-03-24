package cn.service;

import java.util.List;
import java.util.Map;

import cn.entity.PageBean;
import cn.entity.smbms_bill;
import cn.entity.smbms_provider;

public interface BillService {
	// 查询全部
	List<Map> List();
//根据ID查询资料
	Map<String,Object> IdData(int id);
	//修改
	int Update(smbms_bill bill);
	//增加 
	int ADD(smbms_bill bill);
	//删除
	int delete(int id);
	//分页查询
	PageBean Bean(String productName, int isPayment, int providerId,
			int currentPage, int currentCount);

}
