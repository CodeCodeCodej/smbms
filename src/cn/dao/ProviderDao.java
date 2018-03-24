package cn.dao;

import java.util.List;
import java.util.Map;

import cn.entity.smbms_bill;
import cn.entity.smbms_provider;

public interface ProviderDao {
//查询全部
	List<smbms_provider> List();
//根据供应商查询信息
	smbms_provider ProCode(String proCode);
	//删除
	int Delect(String proCode);
	//修改信息
	int Updeta(smbms_provider pro);
	//增加信息
	int Add(smbms_provider pro);
	//分页
	List<smbms_provider> BeanList(int index, int currentCount, String proName);
	//条数
	int total(String proName);

}
