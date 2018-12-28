package entity;

public class User_Ord_User {
	private String oid;
	private String send_name;
	private String send_data;
	private String receiving_data;
	private String category;
	private String state;
	private String receiving_name;
	
	
	@Override
	public String toString() {
		return "User_Ord_User [oid=" + oid + ", send_name=" + send_name
				+ ", send_data=" + send_data + ", receiving_data="
				+ receiving_data + ", category=" + category + ", state="
				+ state + ", receiving_name=" + receiving_name + "]";
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getSend_name() {
		return send_name;
	}
	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}
	public String getSend_data() {
		return send_data;
	}
	public void setSend_data(String send_data) {
		this.send_data = send_data;
	}
	public String getReceiving_data() {
		return receiving_data;
	}
	public void setReceiving_data(String receiving_data) {
		this.receiving_data = receiving_data;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getReceiving_name() {
		return receiving_name;
	}
	public void setReceiving_name(String receiving_name) {
		this.receiving_name = receiving_name;
	}

}
