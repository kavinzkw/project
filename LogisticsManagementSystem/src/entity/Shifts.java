package entity;

public class Shifts {
	private String rid;
	private String cid; 
	private String dtime;
	private String atime;
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getDtime() {
		return dtime;
	}
	public void setDtime(String dtime) {
		this.dtime = dtime;
	}
	public String getAtime() {
		return atime;
	}
	public void setAtime(String atime) {
		this.atime = atime;
	}
	@Override
	public String toString() {
		return "Shifts [sid=" + rid + ", cid=" + cid + ", dtime=" + dtime + ", atime=" + atime + "]";
	}
	

}
