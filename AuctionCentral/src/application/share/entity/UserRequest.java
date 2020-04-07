package application.share.entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * User request record
 * @author
 *
 */
public class UserRequest {
	private  SimpleStringProperty userName;
	private  SimpleStringProperty bankKey;
	private  SimpleStringProperty getAuctionKey;
	public UserRequest(String userName, String bankKey, String getAuctionKey) {
		this.userName = new SimpleStringProperty(userName);
		this.bankKey = new SimpleStringProperty(bankKey);
		this.getAuctionKey = new SimpleStringProperty(getAuctionKey);
	}
	public String getUserName() {
		return userName.get();
	}
	public void setUserName(String userName) {
		this.userName.set(userName);
	}
	public String getBankKey() {
		return bankKey.get();
	}
	public void setBankKey(String bankKey) {
		this.bankKey.set(bankKey);
	}
	public String getGetAuctionKey() {
		return getAuctionKey.get();
	}
	public void setGetAuctionKey(String getAuctionKey) {
		this.getAuctionKey.set(getAuctionKey);
	}



}
