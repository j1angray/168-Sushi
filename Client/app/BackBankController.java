/***************************************************
 * Group member: Zihao Liu, Sun Su
 * This class build the GUI that back to the bank controller.
 **************************************************/
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.share.AlertBox;
import application.share.BankScoket;
import application.share.Utils;
import application.share.entity.BankAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Open the user form
 * @author
 *
 */
public class BackBankController implements Initializable {
	@FXML
	private Button summit;
	@FXML
	private Button returnindex;

	@FXML
	private TextField name;
	@FXML
	private TextField account;

	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}
	/**
	 * Authenticated user
	 * @param event Monitor
	 */
	public void backBank(ActionEvent event) {
		String namevalue=name.getText();
		String accountvalue=account.getText();
		String json="@#@getBank_"+namevalue+"_"+accountvalue;

		try {
			Main.sc.write(Main.charset.encode(json));
			Thread.sleep(1000);
			if(BankScoket.msg.length()>0){
				String[] msgArr = BankScoket.msg.toString().split("_");
				if(msgArr[0].equals("$genbankOk")){
					BankAccount b=new BankAccount();
					b.setBankAccount(accountvalue);
					b.setBankName(namevalue);
					b.setBankKey(msgArr[1]);
					Utils.bankuser=b;
					new AlertBox().display(Utils.PROMPT, "Successfully open: "+namevalue+"'s Account");
				}else if(msgArr[0].equals("$genbankNo")){
					new AlertBox().display(Utils.PROMPT, "Cannot open, user name or account is wrong.");
				}
			}

		} catch (IOException | InterruptedException e) {
			new AlertBox().display(Utils.PROMPT, "Cannot open user page");
		}

	}
	/**
	 * Return to the home page
	 * @param event Monitor
	 */
	public void returnIndex(ActionEvent event) {
		Utils.start(Utils.stage);
	}
}
