package cn.dao;

import java.util.Map;
import java.util.List;

import cn.entity.smbms_bill;

public interface BillDao {
//查询信息
List<Map> List();
//id查询
Map<String,Object> IdData(int id);
//修改
int UPdate(smbms_bill bill);
//增加
int ADD(smbms_bill bill);
//删除
int delete(int id);
//分页模糊查询
List<Map> BeanList(int index, int currentCount, String productName,
		int isPayment, int providerId);
//总条数
int totalCount(String productName, int isPayment, int providerId);

List<smbms_bill> list(int providerId);


}
