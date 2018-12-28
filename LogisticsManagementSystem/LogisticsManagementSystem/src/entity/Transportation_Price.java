package entity;

public class Transportation_Price {//‘À ‰º€∏Ò
	private String send_id;
	private String receiving_id;
	private String send_name;
	private String rece_name;
	private double first_weight;
	private double first_volume;
	private double first_weight_price;
	private double first_volume_price;
	private double seconds_serght_price;
	private double seconds_volume_price;
	public Transportation_Price(String send_id, String receiving_id, String send_name, String rece_name,
			double first_weight, double first_volume, double first_weight_price, double first_volume_price,
			double seconds_serght_price, double seconds_volume_price) {
		super();
		this.send_id = send_id;
		this.receiving_id = receiving_id;
		this.send_name = send_name;
		this.rece_name = rece_name;
		this.first_weight = first_weight;
		this.first_volume = first_volume;
		this.first_weight_price = first_weight_price;
		this.first_volume_price = first_volume_price;
		this.seconds_serght_price = seconds_serght_price;
		this.seconds_volume_price = seconds_volume_price;
	}
	public Transportation_Price() {
		super();
	}
	public String getSend_id() {
		return send_id;
	}
	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}
	public String getReceiving_id() {
		return receiving_id;
	}
	public void setReceiving_id(String receiving_id) {
		this.receiving_id = receiving_id;
	}
	public String getSend_name() {
		return send_name;
	}
	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}
	public String getRece_name() {
		return rece_name;
	}
	public void setRece_name(String rece_name) {
		this.rece_name = rece_name;
	}
	public double getFirst_weight() {
		return first_weight;
	}
	public void setFirst_weight(double first_weight) {
		this.first_weight = first_weight;
	}
	public double getFirst_volume() {
		return first_volume;
	}
	public void setFirst_volume(double first_volume) {
		this.first_volume = first_volume;
	}
	public double getFirst_weight_price() {
		return first_weight_price;
	}
	public void setFirst_weight_price(double first_weight_price) {
		this.first_weight_price = first_weight_price;
	}
	public double getFirst_volume_price() {
		return first_volume_price;
	}
	public void setFirst_volume_price(double first_volume_price) {
		this.first_volume_price = first_volume_price;
	}
	public double getSeconds_serght_price() {
		return seconds_serght_price;
	}
	public void setSeconds_serght_price(double seconds_serght_price) {
		this.seconds_serght_price = seconds_serght_price;
	}
	public double getSeconds_volume_price() {
		return seconds_volume_price;
	}
	public void setSeconds_volume_price(double seconds_volume_price) {
		this.seconds_volume_price = seconds_volume_price;
	}
	@Override
	public String toString() {
		return "Transportation_Price [send_id=" + send_id + ", receiving_id=" + receiving_id + ", send_name="
				+ send_name + ", rece_name=" + rece_name + ", first_weight=" + first_weight + ", first_volume="
				+ first_volume + ", first_weight_price=" + first_weight_price + ", first_volume_price="
				+ first_volume_price + ", seconds_serght_price=" + seconds_serght_price + ", seconds_volume_price="
				+ seconds_volume_price + "]";
	}
	
	
}
