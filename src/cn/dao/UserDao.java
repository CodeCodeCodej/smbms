package cn.dao;

import java.util.List;
import java.util.Map;

import cn.entity.smbms_role;
import cn.entity.smbms_user;

public interface UserDao {

	smbms_user login(String userCode, String userPassword);
//修改密码
	int UpdatePassword(String userPassword,int id);
	//删除信息
	int Delect(String userCOde);
	//查询用户名已经存在吗？
	boolean userid(String userId);
	//增加信息
	int Add(smbms_user user);
	//根据账号查询信息
	smbms_user UserCodeUser(String userCode);
	//修改信息
	int update(smbms_user user);
	//查询一共有多少条
	int total(String userName);
	//分页
	List<Map> BeanList(int index, int currentCount,String userName);

}
