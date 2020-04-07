package application.share.entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * Lot entity
 * @author
 *
 */
public class Lot {
	private  SimpleStringProperty auctionId;

	private  SimpleStringProperty prijectId;
	private  SimpleStringProperty name;
	private String state="0";
	private String userName;
	private String money;



	public Lot(String AuctionId, String prijectId, String name) {
		this.auctionId = new SimpleStringProperty(AuctionId);
		this.name = new SimpleStringProperty(name);
		this.prijectId = new SimpleStringProperty(prijectId);
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getMoney() {
		return money;
	}



	public void setMoney(String money) {
		this.money = money;
	}



	public String getAuctionId() {
		return auctionId.get();
	}

	public void setAuctionId(String publicId) {
		this.auctionId .set(publicId);
	}

	public String getPrijectId() {
		return prijectId.get();
	}

	public void setPrijectId(String prijectId) {
		this.prijectId.set(prijectId);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}



}
