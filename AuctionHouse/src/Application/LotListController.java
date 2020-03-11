
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
import application.share.Scoket;
import application.share.Utils;
import application.share.entity.Auction;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
/**
 * Registered auction
 * @author
 *
 */
public class LotListController implements Initializable {
	@FXML
	private Button submit;
	@FXML
	private Button index;
	@FXML
	private TextField id;
	@FXML
	private TextField name;
	@FXML
	private TextField key;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO (don't really need to do anything here).
	}

	/**
	 * Registered auction
	 * @param event
	 */
	public void redistAuction(ActionEvent event) {
		String idvalue=id.getText();
		String namevalue=name.getText();
		String keyvalue=key.getText();
		String json="@#@addauction_"+Utils.auction.getPublicId()+"_"+idvalue+"_"+namevalue+"_"+keyvalue;
		try {
			Main.sc.write(Main.charset.encode(json));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			new AlertBox().display("System notification", "Please check whether the auction center is online. Please restart it!");
			System.exit(1);
		}
		String msg=Utils.msg;
		if(msg.equals("addauctionDteilsOk")){
			new AlertBox().display("System notification", "The success of the registered auction!");
			Utils.start();
		}else if(msg.equals("addauctionDteilsNo")){
			new AlertBox().display("System notification","The auction has been registered.");
			Utils.start();

		}
	}

	/**
	 * Return Home Page
	 * @param event
	 */
	public void retunindex(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
		Utils.start();
	}
}