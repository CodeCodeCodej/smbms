package cn.web;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.PageBean;
import cn.entity.smbms_bill;
import cn.entity.smbms_provider;
import cn.entity.smbms_user;
import cn.service.BillService;
import cn.service.ProviderService;
import cn.service.impl.BillServiceImpl;
import cn.service.impl.ProviderServiceImpl;

public class BillServlet extends HttpServlet {
	BillService service = new BillServiceImpl();
	smbms_bill bill = new smbms_bill();
	private String productName;
	private int isPayment;
	private int providerId;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		if ("Bill".equals(path)) {
			this.ListJSP(request, response);
		} else if ("View".equals(path)) {
			this.View(request, response);
		} else if ("Idupdate".equals(path)) {
			this.Idupdate(request, response);
		} else if ("Update".equals(path)) {
			this.Update(request, response);
		} else if ("add".equals(path)) {
			this.add(request, response);
		} else if ("ADD".equals(path)) {
			this.ADD(request, response);
		} else if ("Delete".equals(path)) {
			this.Delete(request, response);
		}
	}

	public void ListJSP(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("productName") == null
				|| ("").equals(request.getParameter("productName"))) {
			productName = "";
		} else {
			productName = request.getParameter("productName");
		}
		if ( request.getParameter("isPayment") == null||request.getParameter("isPayment").equals("")) {
			isPayment = 0;
		} else {
			isPayment = Integer.parseInt(request.getParameter("isPayment"));
		}
		if ( request.getParameter("providerId") == null||request.getParameter("providerId").equals("")) {
			providerId = 0;
		} else {
			providerId = Integer.parseInt(request.getParameter("providerId"));
		}
		// 获取前台传过来的当前页
		String CurrentPage = request.getParameter("currentPage");
		if (CurrentPage == null || CurrentPage.equals("")) {
			CurrentPage = "1";
		}
		int currentPage = Integer.parseInt(CurrentPage);
		// 每页显示多少数据
		int currentCount = 5;
		PageBean pageBaen = service.Bean(productName, isPayment, providerId,
				currentPage, currentCount);
		request.setAttribute("pageBaen", pageBaen);
		List<Map> list=pageBaen.getList();
		request.setAttribute("list", list);
		// 查询供应商
		ProviderService pro = new ProviderServiceImpl();
		List<smbms_provider> proName = pro.List();
		request.setAttribute("proName", proName);
		request.setAttribute("providerId", providerId);
		request.setAttribute("productName",productName);
		request.setAttribute("isPayment", isPayment);
		request.getRequestDispatcher("/WEB-INF/admin/bill_List.jsp").forward(
				request, response);
	}

	public void View(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Map<String, Object> bill = service.IdData(id);
		request.setAttribute("bill", bill);

		// 查看
		request.getRequestDispatcher("/WEB-INF/admin/bill_View.jsp").forward(
				request, response);

	}

	public void Idupdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 查询供应商
		ProviderService pro = new ProviderServiceImpl();
		List<smbms_provider> proName = pro.List();
		request.setAttribute("proName", proName);
		// 根据ID查询信息
		int id = Integer.parseInt(request.getParameter("id"));
		Map<String, Object> bill = service.IdData(id);
		request.setAttribute("bill", bill);
		// 修改回显数据
		request.getRequestDispatcher("/WEB-INF/admin/bill_update.jsp").forward(
				request, response);
	}

	public void Update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		bill.setId(Integer.parseInt(request.getParameter("id")));
		bill.setBillCode(request.getParameter("billCode"));
		bill.setProductName(request.getParameter("providerName"));
		bill.setProductUnit(request.getParameter("productUnit"));
		bill.setProductCount(new BigDecimal(request
				.getParameter("productCount")));
		bill.setTotalPrice(new BigDecimal(request.getParameter("totalPrice")));

		bill.setIsPayment(Integer.parseInt(request.getParameter("zhifu")));
		// 供应商名字
		bill.setProviderId(Integer.parseInt(request.getParameter("proName")));
		// 更新者
		smbms_user user = (smbms_user) request.getSession()
				.getAttribute("user");
		bill.setModifyBy(user.getId());
		bill.setModifyDate(new Date());
		int num = service.Update(bill);
		PrintWriter out = response.getWriter();
		if (num == 0) {
			out.print("0");
		} else {
			out.print("1");
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 查询供应商
		ProviderService pro = new ProviderServiceImpl();
		List<smbms_provider> proName = pro.List();
		request.setAttribute("proName", proName);
		request.getRequestDispatcher("/WEB-INF/admin/bill_Add.jsp").forward(
				request, response);
	}

	public void ADD(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		bill.setBillCode(request.getParameter("billId"));
		bill.setProductName(request.getParameter("billName"));
		bill.setProductUnit(request.getParameter("billCom"));
		bill.setProductCount(new BigDecimal(request.getParameter("billNum")));
		bill.setTotalPrice(new BigDecimal(request.getParameter("money")));
		bill.setProviderId(Integer.parseInt(request.getParameter("supplier")));
		System.out.println(request.getParameter("supplier"));
		bill.setIsPayment(Integer.parseInt(request.getParameter("isPayment")));
		System.out.println(request.getParameter("isPayment"));
		// 获取Session中的ID
		smbms_user user = (smbms_user) request.getSession()
				.getAttribute("user");
		bill.setCreatedBy(user.getId());
		bill.setCreationDate(new Date());
		PrintWriter out = response.getWriter();
		int num = service.ADD(bill);
		if (num == 0) {
			out.print("0");
		} else {
			out.print("1");
		}
	}

	public void Delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		PrintWriter out = response.getWriter();
		int num = service.delete(id);
		if (num == 0) {
			out.print("删除失败");
		} else {
			out.print("删除成功");
		}
	}
}
