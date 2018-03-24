package cn.entity;

import java.util.Date;

public class smbms_role {
	private int id;// 主键ID
	private String roleCode;// 角色编码
	private String roleName;// 角色名称
	private int createdBy;// 创建者
	private Date creationDate;// 创建时间
	private int modifyBy;// 修改者
	private Date modifyDate;// 修改时间
	public smbms_role(){};
	public smbms_role(int id, String roleCode, String roleName, int createdBy,
			Date creationDate, int modifyBy, Date modifyDate) {
		super();
		this.id = id;
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	@Override
	public String toString() {
		return "smbms_role [id=" + id + ", roleCode=" + roleCode
				+ ", roleName=" + roleName + ", createdBy=" + createdBy
				+ ", creationDate=" + creationDate + ", modifyBy=" + modifyBy
				+ ", modifyDate=" + modifyDate + "]";
	}

}
