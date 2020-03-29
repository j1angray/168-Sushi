package application.share.entity;
/**
 * Bidders record
 * @author
 *
 */
public class AuctionRecord {
	private String ip;
	private String money;
	private String userName;
	private String prijectId;
	private String state;

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPrijectId() {
		return prijectId;
	}
	public void setPrijectId(String prijectId) {
		this.prijectId = prijectId;
	}



}
