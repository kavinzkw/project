package entity;

public class Order {
    private String oId;
    private String sePlace;
    private String rePlace;
    private String seName;
    private String setel;
    private String date;
    @Override
	public String toString() {
		return "Order [oId=" + oId + ", sePlace=" + sePlace + ", rePlace=" + rePlace + ", seName=" + seName + ", setel="
				+ setel + ", date=" + date + ", state=" + state + "]";
	}
	private String state;
    public String getDate() {
		return date;
	}
	public void setDate(String data) {
		this.date = data;
	}
	
    public String getoId() {
		return oId;
	}
	public void setoId(String oId) {
		this.oId = oId;
	}
	public String getSePlace() {
		return sePlace;
	}
	public void setSePlace(String sePlace) {
		this.sePlace = sePlace;
	}
	public String getRePlace() {
		return rePlace;
	}
	public void setRePlace(String rePlace) {
		this.rePlace = rePlace;
	}
	public String getSeName() {
		return seName;
	}
	public void setSeName(String seName) {
		this.seName = seName;
	}
	public String getSetel() {
		return setel;
	}
	public void setSetel(String setel) {
		this.setel = setel;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	 
}
