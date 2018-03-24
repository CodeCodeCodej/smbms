package cn.service.impl;

import java.util.List;
import java.util.Map;

import cn.dao.UserDao;
import cn.dao.impl.UserDaoImpl;
import cn.entity.PageBean;
import cn.entity.smbms_role;
import cn.entity.smbms_user;
import cn.service.UserService;

public class UserServiceImpl implements UserService {
	UserDao ud = new UserDaoImpl();

	// 登陆
	public smbms_user login(String userCode, String userPassword) {
		return ud.login(userCode, userPassword);
	}

	// 修改密码
	public int UpdatePassword(String userPassword, int id) {
		// TODO Auto-generated method stub
		return ud.UpdatePassword(userPassword, id);
	}


	// 删除用户信息
	public int Delect(String userCOde) {
		return ud.Delect(userCOde);
	}

	// 查询用户名是否已经存在
	public boolean useid(String userId) {
		// TODO Auto-generated method stub
		return ud.userid(userId);
	}
//增加信息
	public int Add(smbms_user user) {
		return ud.Add(user);
	}
//根据账号查询信息
	public smbms_user UserCodeUser(String userCode) {
		return ud.UserCodeUser(userCode);
	}
//修改信息
public int update(smbms_user user) {
	return ud.update(user);
}
//分页
public PageBean Bean(int currentPage, int currentCount ,String userName) {
	PageBean pageBean = new PageBean<Map>();
	//当前页
	pageBean.setCurrentPage(currentPage);
	//当前条数
	pageBean.setCurrentCount(currentCount);
	//总条数
	int totalCount=ud.total(userName);
	pageBean.setTotalCount(totalCount);
	//总页数
	int totalPage=(int) Math.ceil(1.0 * totalCount / currentCount);
	pageBean.setTotalPage(totalPage);
	//每页显示的数据
	int index=(currentPage - 1) * currentCount;
	List<Map> list = ud.BeanList(index, currentCount , userName);
	pageBean.setList(list);
	return pageBean;
}

}
