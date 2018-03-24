package cn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class smbmsFiltre implements Filter {
	private String charset;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chian) throws IOException, ServletException {
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		System.out.println("进去有session的过滤器");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (null == req.getSession().getAttribute("user")) {
			resp.sendRedirect(req.getContextPath() + "/index.jsp");
			return;
		}
		// 放行
		chian.doFilter(request, response);
		System.out.println("响应了有session的过滤器");
	}

	public void init(FilterConfig config) throws ServletException {
		String Init = config.getInitParameter("Init");
		if (Init == null) {
			charset = "Utf-8";
		} else {
			charset = Init;
		}
	}

	public void destroy() {

	}

}
