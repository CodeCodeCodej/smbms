package cn.service.impl;

import java.util.List;
import java.util.Map;

import cn.dao.BillDao;
import cn.dao.ProviderDao;
import cn.dao.impl.BillDaoImpl;
import cn.dao.impl.ProviderDaoImpl;
import cn.entity.PageBean;
import cn.entity.smbms_bill;
import cn.entity.smbms_provider;
import cn.service.ProviderService;

public class ProviderServiceImpl implements ProviderService {
	ProviderDao pd = new ProviderDaoImpl();

	// 查询所有供应商的信息
	public List<smbms_provider> List() {
		return pd.List();
	}

	// 根据供应厂编码查询供应商信息
	public smbms_provider ProCode(String proCode) {
		return pd.ProCode(proCode);
	}

	// 删除
	public int Delect(String proCode) {
		return pd.Delect(proCode);
	}

	// 修改信息
	public int Updeta(smbms_provider pro) {
		return pd.Updeta(pro);
	}

	// 增加信息
	public int Add(smbms_provider pro) {
		return pd.Add(pro);
	}

	// 分页
	public PageBean Bean(int currentPage, int currentCount, String proName) {
		PageBean pageBean = new PageBean();
		// 当前页
		pageBean.setCurrentPage(currentPage);
		// 当前条数
		pageBean.setCurrentCount(currentCount);
		// 总条数
		int totalCount = pd.total(proName);
		pageBean.setTotalCount(totalCount);
		// 总页数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据
		int index = (currentPage - 1) * currentCount;
		List<smbms_provider> list = pd.BeanList(index, currentCount, proName);
		pageBean.setList(list);
		return pageBean;
	}

	// 查看有没有未支付的
	public boolean zhifu(int providerId) {
		boolean flag = true;
		BillDao bd = new BillDaoImpl();
		List<smbms_bill> list = bd.list(providerId);
		for (smbms_bill bill : list) {
			if (bill.getIsPayment() == 1) {
				flag = false;
				break;
			}
		}
		return flag;
	}
}
