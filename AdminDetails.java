package org.bank.dto;

public class AdminDetails {

	private int adminId;
	private String adminemailid;
	private int adminpassword;
	public AdminDetails() {
		super();
	}
	public AdminDetails(int adminId, String adminemailid, int adminpassword) {
		super();
		this.adminId = adminId;
		this.adminemailid = adminemailid;
		this.adminpassword = adminpassword;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminemailid() {
		return adminemailid;
	}
	public void setAdminemailid(String adminemailid) {
		this.adminemailid = adminemailid;
	}
	public int getAdminpassword() {
		return adminpassword;
	}
	public void setAdminpassword(int adminpassword) {
		this.adminpassword = adminpassword;
	}
	@Override
	public String toString() {
		return "AdminDetails [adminId=" + adminId + ", adminemailid=" + adminemailid + ", adminpassword="
				+ adminpassword + "]";
	}
	
	
}
