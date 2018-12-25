package entity;

public class UserOrder {//订单确认完成的实体类
	private String oid;
	private String category;
	private String senddata;
	private String state;
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSenddata() {
		return senddata;
	}
	public void setSenddata(String senddata) {
		this.senddata = senddata;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
