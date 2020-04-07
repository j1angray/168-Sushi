/***************************************************
 * Group member: Zihao Liu, Sun Su
 * The class of the bank account
 **************************************************/
package application.share.entity;
public class BankAccount {
	//name
	public String bankName;
	//account
	public String bankAccount;
	//key
	public String bankKey;
	//deposit
	public String money;
  // state: 1 means locked, 0 means normal
	public String state;

	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankKey() {
		return bankKey;
	}
	public void setBankKey(String bankKey) {
		this.bankKey = bankKey;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
