package entity;

public class Abnormal_sheet {
	private String asid;
	private String oid;
	private double asmoney;
	private String asdata;
	private String payee;
	private String resons;
	private String handle;
	public Abnormal_sheet(String asid, String oid, double asmoney, String asdata, String payee, String resons,
			String handle) {
		super();
		this.asid = asid;
		this.oid = oid;
		this.asmoney = asmoney;
		this.asdata = asdata;
		this.payee = payee;
		this.resons = resons;
		this.handle = handle;
	}
	public Abnormal_sheet() {
		super();
	}
	public String getAsid() {
		return asid;
	}
	public void setAsid(String asid) {
		this.asid = asid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public double getAsmoney() {
		return asmoney;
	}
	public void setAsmoney(double asmoney) {
		this.asmoney = asmoney;
	}
	public String getAsdata() {
		return asdata;
	}
	public void setAsdata(String asdata) {
		this.asdata = asdata;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getResons() {
		return resons;
	}
	public void setResons(String reasons) {
		this.resons = reasons;
	}
	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	@Override
	public String toString() {
		return "Abnormal_sheet [asid=" + asid + ", oid=" + oid + ", asmoney=" + asmoney + ", asdata=" + asdata
				+ ", payee=" + payee + ", reasons=" + resons + ", handle=" + handle + "]";
	}
	
	
}
