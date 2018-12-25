package entity;

public class site_Orders {
	private String sid;
	private String oid;
	public site_Orders(String sid, String oid) {
		super();
		this.sid = sid;
		this.oid = oid;
	}
	public site_Orders() {
		super();
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	
}
