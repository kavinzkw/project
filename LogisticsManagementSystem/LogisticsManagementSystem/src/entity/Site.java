package entity;

public class Site {
	private String sid;
	private String sanme;
	private String site_range;
	private String province;
	public Site(String sid, String sanme, String site_range, String province) {
		super();
		this.sid = sid;
		this.sanme = sanme;
		this.site_range = site_range;
		this.province = province;
	}
	public Site() {
		super();
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSanme() {
		return sanme;
	}
	public void setSanme(String sanme) {
		this.sanme = sanme;
	}
	public String getSite_range() {
		return site_range;
	}
	public void setSite_range(String site_range) {
		this.site_range = site_range;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Override
	public String toString() {
		return "Site [sid=" + sid + ", sanme=" + sanme + ", site_range=" + site_range + ", province=" + province + "]";
	}
	

}
