package cn.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.PageBean;
import cn.entity.smbms_provider;
import cn.entity.smbms_user;
import cn.service.ProviderService;
import cn.service.impl.ProviderServiceImpl;

public class ProviderServlet extends HttpServlet {
	ProviderService service = new ProviderServiceImpl();
	smbms_provider pro = new smbms_provider();
	private String proName;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		if ("Provider".equals(path)) {
			this.ListJSP(request, response);
		} else if ("View".equals(path)) {
			this.view(request, response);
		} else if ("Delete".equals(path)) {
			this.Delete(request, response);
		} else if ("IdUpdate".equals(path)) {
			this.IdUpdate(request, response);
		} else if ("update".equals(path)) {
			this.Update(request, response);
		} else if ("add".equals(path)) {
			request.getRequestDispatcher("/WEB-INF/admin/provider_Add.jsp")
					.forward(request, response);
		} else if ("Add".equals(path)) {
			this.Add(request, response);
		} else if ("zhifu".equals(path)) {
			this.zhifu(request, response);
		}
	}

	public void ListJSP(HttpServletRequest request, HttpServletResponse response)
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
		if (request.getParameter("proName") == null) {
			proName = "";
		} else {
			proName = request.getParameter("proName");
		}
		PageBean pageBean = service.Bean(currentPage, currentCount, proName);
		request.setAttribute("pageBean", pageBean);
		List<smbms_provider> list = pageBean.getList();
		request.setAttribute("list", list);
		request.setAttribute("proName", proName);
		request.getRequestDispatcher("/WEB-INF/admin/provider_List.jsp")
				.forward(request, response);
	}

	public void view(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String proCode = request.getParameter("proCode");
		smbms_provider pro = service.ProCode(proCode);
		request.setAttribute("pro", pro);
		request.getRequestDispatcher("/WEB-INF/admin/provider_View.jsp")
				.forward(request, response);
	}

	public void Delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String proCode = request.getParameter("proCode");
		int num = service.Delect(proCode);
		if (num == 0) {
			out.print("删除失败");
		} else {
			out.print("删除成功");
		}
	}

	public void IdUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String proCode = request.getParameter("proCode");
		smbms_provider pro = service.ProCode(proCode);
		request.setAttribute("pro", pro);
		request.getRequestDispatcher("/WEB-INF/admin/provider_update.jsp")
				.forward(request, response);
	}

	public void Update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		pro.setProCode(request.getParameter("providerId"));
		pro.setProName(request.getParameter("providerName"));
		pro.setProContact(request.getParameter("people"));
		pro.setProPhone(request.getParameter("phone"));
		pro.setProAddress(request.getParameter("address"));
		pro.setProFax(request.getParameter("fax"));
		pro.setProDesc(request.getParameter("describe"));
		pro.setModifyDate(new Date());
		smbms_user user = (smbms_user) request.getSession()
				.getAttribute("user");
		pro.setModifyBy(user.getId());
		pro.setId(Integer.parseInt(request.getParameter("id")));
		int num = service.Updeta(pro);
		if (num == 0) {
			out.print("0");
		} else {
			out.print("1");
		}
	}

	public void Add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		pro.setProCode(request.getParameter("providerId"));
		pro.setProName(request.getParameter("providerName"));
		pro.setProContact(request.getParameter("people"));
		pro.setProPhone(request.getParameter("phone"));
		pro.setProAddress(request.getParameter("address"));
		pro.setProFax(request.getParameter("fax"));
		pro.setProDesc(request.getParameter("describe"));
		smbms_user user = (smbms_user) request.getSession()
				.getAttribute("user");
		pro.setCreatedBy(user.getId());
		pro.setCreationDate(new Date());
		int num = service.Add(pro);
		if (num == 0) {
			out.print("0");
		} else {
			out.print("1");
		}

	}

	public void zhifu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int providerId =Integer.parseInt(request.getParameter("providerId"));
		 boolean falg=service.zhifu(providerId);
		 if(!falg){
			 out.print("0");
		 }
	}
}
