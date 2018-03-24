package cn.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import cn.entity.smbms_user;
import cn.service.UserService;
import cn.service.impl.UserServiceImpl;

public class PasswordServlet extends HttpServlet {
	UserService service = new UserServiceImpl();

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		if ("password".equals(path)) {
			this.JSP(request, response);

		} else if ("verifyPassword".equals(path)) {
			this.verify(request, response);
		} else if ("UpdatePassword".equals(path)) {
			this.updatePassword(request, response);
		}else if ("welcome".equals(path)){
			System.out.println(231231);
			request.getRequestDispatcher("/WEB-INF/admin/welcome.jsp").forward(
					request, response);
		}
	}

	public void JSP(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/admin/password.jsp").forward(
				request, response);
	}

	public void verify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String password = request.getParameter("userPassword");
		System.out.println("页面输入的密码" + Base64.encode(password.getBytes()));
		smbms_user user = (smbms_user) request.getSession()
				.getAttribute("user");
		System.out.println("Session中的密码" + user.getUserPassword());
		try {
			if (password.equals(new String(Base64.decode((user
					.getUserPassword()))))) {
				System.out.println("密码正确");
				out.print("<span style='color:green'>密码验证正确</span>");
			} else {
				System.out.println("密码错误");
				out.print("<span style='color:red'>密码验证错误</span>");
			}
		} catch (Base64DecodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updatePassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// Session中的id
		smbms_user user = (smbms_user) request.getSession()
				.getAttribute("user");
		int id = user.getId();
		// 获得新密码
		String password = request.getParameter("newPassword");
		String userPassword = Base64.encode(password.getBytes());
		System.out.println(userPassword+"修改后的");
		int num = service.UpdatePassword(userPassword, id);
		if (num != 0) {
			request.getSession().removeAttribute("user");
			System.out.println("修改成功");
			out.print("修改密码成功，请从新登陆");
			
		} else {
			System.out.println("修改失败");
			out.print("修改密码失败");
		}
	}
}
