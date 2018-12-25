package entity;

public class UserOdr {//用户下订单表实体类
private String oid;
private int sendsiteid;
private int receivingsieid;
private String senddata;
private String receivingdata;
private String category;
private String state;
private String sendname;
private String sendtel;
private String sendads;
private String receivingname;
private String receivingtel;
private String receiviangads;
private Float volume;
private Float weigth;
private String goodsname;
private int goodsnum;
private Float goodsprice;
private Enum goodsinsured;
private Enum paymethod;
public String getOid() {
	return oid;
}
public void setOid(String oid) {
	this.oid = oid;
}
public int getSendsiteid() {
	return sendsiteid;
}
public void setSendsiteid(int sendsiteid) {
	this.sendsiteid = sendsiteid;
}
public int getReceivingsieid() {
	return receivingsieid;
}
public void setReceivingsieid(int receivingsieid) {
	this.receivingsieid = receivingsieid;
}
public String getSenddata() {
	return senddata;
}
public void setSenddata(String senddata) {
	this.senddata = senddata;
}
public String getReceivingdata() {
	return receivingdata;
}
public void setReceivingdata(String receivingdata) {
	this.receivingdata = receivingdata;
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
public String getSendname() {
	return sendname;
}
public void setSendname(String sendname) {
	this.sendname = sendname;
}
public String getSendtel() {
	return sendtel;
}
public void setSendtel(String sendtel) {
	this.sendtel = sendtel;
}
public String getSendads() {
	return sendads;
}
public void setSendads(String sendads) {
	this.sendads = sendads;
}
public String getReceivingname() {
	return receivingname;
}
public void setReceivingname(String receivingname) {
	this.receivingname = receivingname;
}
public String getReceivingtel() {
	return receivingtel;
}
public void setReceivingtel(String receivingtel) {
	this.receivingtel = receivingtel;
}
public String getReceiviangads() {
	return receiviangads;
}
public void setReceiviangads(String receiviangads) {
	this.receiviangads = receiviangads;
}
public Float getVolume() {
	return volume;
}
public void setVolume(Float volume) {
	this.volume = volume;
}
public Float getWeigth() {
	return weigth;
}
public void setWeigth(Float weigth) {
	this.weigth = weigth;
}
public String getGoodsname() {
	return goodsname;
}
public void setGoodsname(String goodsname) {
	this.goodsname = goodsname;
}
public int getGoodsnum() {
	return goodsnum;
}
public void setGoodsnum(int goodsnum) {
	this.goodsnum = goodsnum;
}
public Float getGoodsprice() {
	return goodsprice;
}
public void setGoodsprice(Float goodsprice) {
	this.goodsprice = goodsprice;
}
public Enum getGoodsinsured() {
	return goodsinsured;
}
public void setGoodsinsured(Enum goodsinsured) {
	this.goodsinsured = goodsinsured;
}
public Enum getPaymethod() {
	return paymethod;
}
public void setPaymethod(Enum paymethod) {
	this.paymethod = paymethod;
}

}
