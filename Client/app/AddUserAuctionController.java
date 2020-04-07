/***************************************************
 * Group member: Zihao Liu, Sun Su
 * This class add a controller of Aucrion.
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
import application.share.AuctionScoket;
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
 *Apply for an auction
 * @author
 *
 */
public class AddUserAuctionController implements Initializable {
	@FXML
	private Button submitButton;
	@FXML
	private Button returnindex;
	@FXML
	private TextField key;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// TODO (don't really need to do anything here).

	}

	/**
	 * Apply for an auction
	 * @param event Monitor
	 */
	public void addBankUser(ActionEvent event) {
		String keyvalue=key.getText();
		String json="@#@apply_"+keyvalue+"_"+Utils.bankuser.getBankName()+"_"+Utils.bankuser.bankKey;
		try {
			Main.scAuction.write((Main.charset.encode(json)));
			Thread.sleep(1000);
			if("applayAcutionOk".equals(AuctionScoket.msg)){
				BankAccount b=Utils.bankuser;
				b.setState("3");
				new AlertBox().display(Utils.PROMPT, "Participate auction success.");
			}else if("applayAcutionNo".equals(AuctionScoket.msg)){
				new AlertBox().display(Utils.PROMPT, "Entering the application status, please wait patiently");
			}
		} catch (IOException | InterruptedException e) {
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