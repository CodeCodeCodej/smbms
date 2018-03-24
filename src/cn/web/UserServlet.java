package cn.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import cn.entity.PageBean;
import cn.entity.smbms_role;
import cn.entity.smbms_user;
import cn.service.UserService;
import cn.service.impl.UserServiceImpl;

public class UserServlet extends HttpServlet {
	UserService service = new UserServiceImpl();
	smbms_user user = new smbms_user();
	private String userName;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		if ("User".equals(path)) {
			this.List(request, response);
		} else if ("Delete".equals(path)) {
			this.delete(request, response);
		} else if ("add".equals(path)) {
			this.AddJsp(request, response);
		} else if ("userId".equals(path)) {
			this.userid(request, response);
		} else if ("Add".equals(path)) {
			this.Add(request, response);
		} else if ("IdUpdate".equals(path)) {
			this.IdUpdate(request, response);
		} else if ("Update".equals(path)) {
			this.Update(request, response);
		} else if ("view".equals(path)) {
			this.view(request, response);
		}
	}

	// 遍历信息
	public void List(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 分页
		// 前台传入的当前页
		String CurrentPage = request.getParameter("currentPage");
		if (CurrentPage == null) {
			CurrentPage = "1";
		}
		int currentPage = Integer.parseInt(CurrentPage);
		// 每页显示的多少条数据
		int currentCount = 5;
		// 接受前台穿过来需要查询的数据
		if (request.getParameter("userName") == null) {
			userName = "";
		} else {
			userName = request.getParameter("userName");
		}
		PageBean pageBean = service.Bean(currentPage, currentCount, userName);
		request.setAttribute("pageBean", pageBean);
		List<Map> list = pageBean.getList();
		request.setAttribute("list", list);
		request.setAttribute("userName", userName);
		request.getRequestDispatcher("/WEB-INF/admin/user_List.jsp").forward(
				request, response);
	}

	// 删除信息
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userCode = request.getParameter("userCode");
		System.out.println("前台传过来需要删除的用户编码" + userCode);
		int num = service.Delect(userCode);
		if (num == 0) {
			out.print("删除失败");
		} else {
			out.print("删除成功");
		}
	}

	// 跳转增加页面
	public void AddJsp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/admin/user_Add.jsp").forward(
				request, response);
	}

	public void userid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		boolean user = service.useid(userId);
		System.out.println(user);
		PrintWriter out = response.getWriter();
		if (user) {
			out.print("用户名已存在");
		}
	}

	public void Add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		user.setUserCode(request.getParameter("userId"));
		user.setUserName(request.getParameter("userName"));
		user.setUserPassword(Base64.encode(request.getParameter("userpassword")
				.getBytes()));
		// 判断性别
		if (request.getParameter("sex").equals("man")) {
			user.setGender(2);
		} else {
			user.setGender(1);
		}
		// 转换出生日期
		try {
			user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(request
					.getParameter("data")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		user.setPhone(request.getParameter("userphone"));
		user.setAddress(request.getParameter("userAddress"));
		user.setUserRole(Integer.parseInt(request.getParameter("userlei")));
		smbms_user User = (smbms_user) request.getSession()
				.getAttribute("user");
		user.setCreatedBy(User.getId());
		// 从Session中获取创建者ID
		user.setCreationDate(new Date());
		int num = service.Add(user);
		// 返回增加成功与否
		if (num == 0) {
			out.print("增加失败");
		} else {
			out.print("增加成功");
		}
	}

	public void IdUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userCode = request.getParameter("userCode");
		smbms_user user = service.UserCodeUser(userCode);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/admin/user_update.jsp").forward(
				request, response);
	}

	// 修改信息
	public void Update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		user.setUserName(request.getParameter("userName"));
		// 判断性别
		if (request.getParameter("sex").equals("man")) {
			user.setGender(2);
		} else {
			user.setGender(1);
		}
		// 转换出生日期
		try {
			user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(request
					.getParameter("data")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setPhone(request.getParameter("userphone"));
		user.setAddress(request.getParameter("userAddress"));
		user.setUserRole(Integer.parseInt(request.getParameter("userlei")));
		user.setModifyDate(new Date());
		// 从Session中获取修改人的ID
		smbms_user User = (smbms_user) request.getSession()
				.getAttribute("user");
		user.setModifyBy(User.getId());
		user.setUserCode(request.getParameter("userCode"));
		int num = service.update(user);
		System.out.println(user + "123");
		// 更新session数据
		if (User.getUserCode().equals(request.getParameter("userCode"))) {
			user.setAge(Integer.parseInt(new SimpleDateFormat("yyyy")
					.format(new Date()))-Integer.parseInt(new SimpleDateFormat("yyyy")
					.format(user.getBirthday())));
			request.getSession().setAttribute("user", user);
			System.out.println(request.getSession().getAttribute("user"));
		}
		if (num == 0) {
			out.print("0");
		} else {
			out.print("1");
		}
	}

	public void view(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userCode = request.getParameter("userCode");
		smbms_user user = service.UserCodeUser(userCode);
		System.out.println(user);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/admin/user_View.jsp").forward(
				request, response);
	}
}
