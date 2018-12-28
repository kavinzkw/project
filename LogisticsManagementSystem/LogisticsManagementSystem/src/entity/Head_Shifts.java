package entity;

public class Head_Shifts {
	 
	private String dePlace;
	private String arPlace;
	private String deTime;
	public String getDePlace() {
		return dePlace;
	}
	public void setDePlace(String dePlace) {
		this.dePlace = dePlace;
	}
	public String getArPlace() {
		return arPlace;
	}
	public void setArPlace(String arPlace) {
		this.arPlace = arPlace;
	}
	public String getDeTime() {
		return deTime;
	}
	public void setDeTime(String deTime) {
		this.deTime = deTime;
	}
	public String getArTime() {
		return arTime;
	}
	public void setArTime(String arTime) {
		this.arTime = arTime;
	}
	private String arTime;
	@Override
	public String toString() {
		return "Head_Shifts [dePlace=" + dePlace + ", arPlace=" + arPlace + ", deTime=" + deTime + ", arTime=" + arTime
				+ "]";
	}

}
