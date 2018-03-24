package cn.service;

import java.util.List;
import java.util.Map;

import cn.entity.PageBean;
import cn.entity.smbms_role;
import cn.entity.smbms_user;

public interface UserService {

	// 登陆
	smbms_user login(String userCode, String userPassword);

	// 修改密码
	int UpdatePassword(String userPassword, int id);

//删除用户信息
		int Delect(String userCOde);
//查询用户名是否已经存在
		boolean useid(String userId);
//增加信息
		int Add(smbms_user user);
//根据账号查询信息
		smbms_user UserCodeUser(String userCode);
//修改信息
		int update(smbms_user user);
//分页
		PageBean Bean(int currentPage, int currentCount,String userName);

}
