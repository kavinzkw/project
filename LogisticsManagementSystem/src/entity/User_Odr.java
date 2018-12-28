package entity;

public class User_Odr {
	private String oid;
	private String send_siteid;
	private String receiving_siteid;
	private String send_sitename;
	private String rece_sitename;
	private String send_data;
	private String receive_data;
	private String category;
	private String state;
	private String send_name;
	private String send_tel;
	private String send_address;
	private String receiving_name;
	private String receiving_tel;
	private String receiving_ars;
	private float weight;
	private float volume;
	private String good_name;
	private int good_num;
	private double price;
	private int insured;
	private int pay_method;
	private long resttime;
	public User_Odr(String oid, String send_siteid, String receiving_siteid, String send_sitename, String rece_sitename,
			String send_data, String receive_data, String category, String state, String send_name, String send_tel,
			String send_address, String receiving_name, String receiving_tel, String receiving_ars, float weight,
			float volume, String good_name, int good_num, float price, int insured, int pay_method, long resttime) {
		super();
		this.oid = oid;
		this.send_siteid = send_siteid;
		this.receiving_siteid = receiving_siteid;
		this.send_sitename = send_sitename;
		this.rece_sitename = rece_sitename;
		this.send_data = send_data;
		this.receive_data = receive_data;
		this.category = category;
		this.state = state;
		this.send_name = send_name;
		this.send_tel = send_tel;
		this.send_address = send_address;
		this.receiving_name = receiving_name;
		this.receiving_tel = receiving_tel;
		this.receiving_ars = receiving_ars;
		this.weight = weight;
		this.volume = volume;
		this.good_name = good_name;
		this.good_num = good_num;
		this.price = price;
		this.insured = insured;
		this.pay_method = pay_method;
		this.resttime = resttime;
	}
	public User_Odr() {
		super();
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getSend_siteid() {
		return send_siteid;
	}
	public void setSend_siteid(String send_siteid) {
		this.send_siteid = send_siteid;
	}
	public String getReceiving_siteid() {
		return receiving_siteid;
	}
	public void setReceiving_siteid(String receiving_siteid) {
		this.receiving_siteid = receiving_siteid;
	}
	public String getSend_sitename() {
		return send_sitename;
	}
	public void setSend_sitename(String send_sitename) {
		this.send_sitename = send_sitename;
	}
	public String getRece_sitename() {
		return rece_sitename;
	}
	public void setRece_sitename(String rece_sitename) {
		this.rece_sitename = rece_sitename;
	}
	public String getSend_data() {
		return send_data;
	}
	public void setSend_data(String send_data) {
		this.send_data = send_data;
	}
	public String getReceive_data() {
		return receive_data;
	}
	public void setReceive_data(String receive_data) {
		this.receive_data = receive_data;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSend_name() {
		return send_name;
	}
	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}
	public String getSend_tel() {
		return send_tel;
	}
	public void setSend_tel(String send_tel) {
		this.send_tel = send_tel;
	}
	public String getSend_address() {
		return send_address;
	}
	public void setSend_address(String send_address) {
		this.send_address = send_address;
	}
	public String getReceiving_name() {
		return receiving_name;
	}
	public void setReceiving_name(String receiving_name) {
		this.receiving_name = receiving_name;
	}
	public String getReceiving_tel() {
		return receiving_tel;
	}
	public void setReceiving_tel(String receiving_tel) {
		this.receiving_tel = receiving_tel;
	}
	public String getReceiving_ars() {
		return receiving_ars;
	}
	public void setReceiving_ars(String receiving_ars) {
		this.receiving_ars = receiving_ars;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}
	public String getGood_name() {
		return good_name;
	}
	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}
	public int getGood_num() {
		return good_num;
	}
	public void setGood_num(int good_num) {
		this.good_num = good_num;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double d) {
		this.price = d;
	}
	public int getInsured() {
		return insured;
	}
	public void setInsured(int insured) {
		this.insured = insured;
	}
	public int getPay_method() {
		return pay_method;
	}
	public void setPay_method(int pay_method) {
		this.pay_method = pay_method;
	}
	public long getResttime() {
		return resttime;
	}
	public void setResttime(long resttime) {
		this.resttime = resttime;
	}
	@Override
	public String toString() {
		return "User_Odr [oid=" + oid + ", send_siteid=" + send_siteid + ", receiving_siteid=" + receiving_siteid
				+ ", send_sitename=" + send_sitename + ", rece_sitename=" + rece_sitename + ", send_data=" + send_data
				+ ", receive_data=" + receive_data + ", category=" + category + ", state=" + state + ", send_name="
				+ send_name + ", send_tel=" + send_tel + ", send_address=" + send_address + ", receiving_name="
				+ receiving_name + ", receiving_tel=" + receiving_tel + ", receiving_ars=" + receiving_ars + ", weight="
				+ weight + ", volume=" + volume + ", good_name=" + good_name + ", good_num=" + good_num + ", price="
				+ price + ", insured=" + insured + ", pay_method=" + pay_method + ", resttime=" + resttime + "]";
	}
	
	
}
