package cn.entity;

import java.math.BigDecimal;
import java.util.Date;

public class smbms_bill {
	private int id;//主键ID'
	private String billCode;//账单编码
	private String productName;//商品名称
	private String productDesc;//商品描述
	private String productUnit;//商品单位
	private BigDecimal productCount;//'商品数量
	private BigDecimal totalPrice;//商品总额
	private int isPayment;//是否支付（1：未支付 2：已支付）
	private int createdBy;//创建者（userId）
	private Date creationDate;//创建时间',
	private int modifyBy;//'更新者（userId）
	private Date modifyDate;//'更新时间
	private int providerId;//'供应商ID'
	public smbms_bill(){};
	public smbms_bill(int providerId){
		this.providerId = providerId;
	};
	public smbms_bill(int id, String billCode, String productName,
			String productDesc, String productUnit, BigDecimal productCount,
			BigDecimal totalPrice, int isPayment, int createdBy,
			Date creationDate, int modifyBy, Date modifyDate, int providerId) {
		this.id = id;
		this.billCode = billCode;
		this.productName = productName;
		this.productDesc = productDesc;
		this.productUnit = productUnit;
		this.productCount = productCount;
		this.totalPrice = totalPrice;
		this.isPayment = isPayment;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
		this.providerId = providerId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public BigDecimal getProductCount() {
		return productCount;
	}
	public void setProductCount(BigDecimal productCount) {
		this.productCount = productCount;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getIsPayment() {
		return isPayment;
	}
	public void setIsPayment(int isPayment) {
		this.isPayment = isPayment;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public int getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	@Override
	public String toString() {
		return "smbms_bill [id=" + id + ", billCode=" + billCode
				+ ", productName=" + productName + ", productDesc="
				+ productDesc + ", productUnit=" + productUnit
				+ ", productCount=" + productCount + ", totalPrice="
				+ totalPrice + ", isPayment=" + isPayment + ", createdBy="
				+ createdBy + ", creationDate=" + creationDate + ", modifyBy="
				+ modifyBy + ", modifyDate=" + modifyDate + ", providerId="
				+ providerId + "]";
	}
	

}
