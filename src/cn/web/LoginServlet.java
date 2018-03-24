package cn.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import cn.entity.smbms_user;
import cn.service.UserService;
import cn.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {
	UserService service = new UserServiceImpl();
	PrintWriter out = null;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		if ("login".equals(path)) {
			String userCode = request.getParameter("usercode");
			String userPassword = Base64.encode(request.getParameter("userpassword").getBytes());
			smbms_user user = service.login(userCode, userPassword);
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!userCode.equals(user.getUserCode())) {
				out.print("0");
			} else {
				if (userPassword.equals(user.getUserPassword())) {
					request.getSession().setAttribute("user", user);
					System.out.println(user);
					System.out.println("登陆成功");
					out.print("1");
				}else{
					out.print("2");
				}
			}

		} else if ("zhuxiao".equals(path)) {
			try {
				System.out.println("退出登录，清除session");
				request.getSession().removeAttribute("user");
				System.out.println(request.getSession().getAttribute("user"));
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}
}
