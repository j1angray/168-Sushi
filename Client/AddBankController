/***************************************************
 * Group member: Zihao Liu
 * The class add a controller of bank.
 **************************************************/
package application;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import application.share.AlertBox;
import application.share.BankScoket;
import application.share.Utils;
import application.share.entity.Auction;
import application.share.entity.BankAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 *Creating a user
 * @author
 *
 */
public class AddBankController implements Initializable {
	@FXML
	private Button submitButton;
	@FXML
	private Button returnindex;

	@FXML
	private TextField name;
	@FXML
	private TextField account;
	@FXML
	private TextField key;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// TODO (don't really need to do anything here).

	}

	/**
	 * Creating a user
	 * @param event Monitor
	 */
	public void addBankUser(ActionEvent event) {
		String namevalue=name.getText();
		String accountvalue=account.getText();
		String keyvalue=key.getText();
		String moneyvalue="100000000.00";

		String json="@#@addBank_"+namevalue+"_"+accountvalue+"_"+keyvalue+"_"+moneyvalue;
		try {
			Main.sc.write(Main.charset.encode(json));
			Thread.sleep(1000);
			if("bankUserOk".equals(BankScoket.msg)){
				BankAccount ban=new BankAccount();
				ban.setBankAccount(accountvalue);
				ban.setBankKey(keyvalue);
				ban.setState("0");
				ban.setBankName(namevalue);
				ban.setMoney(moneyvalue);
				Utils.bankuser=ban;
				new AlertBox().display(Utils.PROMPT, "Successful application account");
			}else if("bankUserNo".equals(BankScoket.msg)){
				new AlertBox().display(Utils.PROMPT, "The username or account can not be repeated");
			}
		} catch (IOException e) {
			new AlertBox().display(Utils.PROMPT, Utils.ERROR);
			System.exit(1);
		} catch (InterruptedException e) {
			new AlertBox().display(Utils.PROMPT, Utils.ERROR);
			System.exit(1);
		}


	}

	/**
	 * Return to the home page
	 * @param event  Monitor
	 */
	public void retunindex(ActionEvent event) {
		Utils.start(Utils.stage);
	}




}
