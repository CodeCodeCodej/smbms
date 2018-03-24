package cn.service;

import cn.entity.PageBean;
import cn.entity.smbms_provider;
import java.util.List;
public interface ProviderService {
//查询供应商
	List<smbms_provider> List();
//根据供应商编码查询信息
	smbms_provider ProCode(String proCode);
	//删除
	int Delect(String proCode);
	//修改信息
	int Updeta(smbms_provider pro);
	//增加信息
	int Add(smbms_provider pro);
	//分页
	PageBean Bean(int currentPage, int currentCount, String proName);
	//多表查询 查看有没有未支付的
	boolean zhifu(int providerId);

}
