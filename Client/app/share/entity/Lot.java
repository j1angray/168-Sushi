/***************************************************
 * Group member: Zihao Liu, Sun Su
 **************************************************/
package application.share.entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * Lot
 *
 */
public class Lot {
	private  SimpleStringProperty auctionId;
	private  SimpleStringProperty prijectId;
	private  SimpleStringProperty name;
	private String state;
	private SimpleStringProperty userName;
	private SimpleStringProperty money;


	public Lot(String AuctionId, String prijectId, String name) {
		this.auctionId = new SimpleStringProperty(AuctionId);
		this.name = new SimpleStringProperty(name);
		this.prijectId = new SimpleStringProperty(prijectId);
	}
	public Lot( String prijectId, String name,String userName,String money) {
		this.name = new SimpleStringProperty(name);
		this.prijectId = new SimpleStringProperty(prijectId);
		this.userName = new SimpleStringProperty(userName);
		this.money = new SimpleStringProperty(money);
	}

	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getUserName() {
		return userName.get();
	}


	public void setUserName(String userName) {
		this.userName.set(userName);
	}


	public String getMoney() {
		return money.get();
	}


	public void setMoney(String money) {
		this.money.set(money);
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


}
