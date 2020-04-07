package application.share.entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * Bank account
 * @author
 *
 */
public class BankAccount {
	public SimpleStringProperty bankName;
	public SimpleStringProperty bankAccount;
	public SimpleStringProperty bankKey;
	public SimpleStringProperty money;
	public SimpleStringProperty state;
	public SimpleStringProperty getKey;
	public BankAccount(String bankName, String bankAccount, String bankKey,String money,String state,String getKey) {
		this.bankName = new SimpleStringProperty(bankName);
		this.bankAccount = new SimpleStringProperty(bankAccount);
		this.bankKey = new SimpleStringProperty(bankKey);
		this.money = new SimpleStringProperty(money);
		this.state = new SimpleStringProperty(state);
		this.getKey = new SimpleStringProperty(getKey);

	}

	public String getBankName() {
		return bankName.get();
	}
	public void setBankName(String bankName) {
		this.bankName.set(bankName);
	}
	public String getBankAccount() {
		return bankAccount.get();
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount.set(bankAccount);
	}
	public String getBankKey() {
		return bankKey.get();
	}
	public void setBankKey(String bankKey) {
		this.bankKey.set(bankKey);
	}
	public String getMoney() {
		return money.get();
	}
	public void setMoney(String money) {
		this.money.set(money);
	}
	public String getState() {
		return state.get();
	}
	public void setState(String state) {
		this.state.set(state);
	}
	public String getGetKey() {
		return getKey.get();
	}
	public void setGetKey(String getKey) {
		this.getKey.set(getKey);
	}

}
