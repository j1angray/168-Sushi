
 
package application;

import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import application.share.AlertBox;
import application.share.Scoket;
import application.share.Utils;
import application.share.entity.Auction;
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
 * Register auction house
 * @author
 *
 */
public class MyController implements Initializable {
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
	 * Registered auction house
	 * @param event
	 */
	public void redistAuction(ActionEvent event) {
		String idvalue=id.getText();
		String namevalue=name.getText();
		String keyvalue=key.getText();
		String json="@#@add_"+idvalue+"_"+namevalue+"_"+keyvalue;
		try {
			Main.sc.write(Main.charset.encode(json));
		} catch (IOException e1) {
			new AlertBox().display("System notification", "Please check whether the auction center is online. Please restart it!");
			System.exit(1);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			new AlertBox().display("System notification", "Please check whether the auction center is online. Please restart it!");
			System.exit(1);
		}
		String msg=Utils.msg;
		if(msg!=null&&""!=msg){
			msg=Utils.msg;
			if(msg.equals("auctionfail")){
				new AlertBox().display("System notification", "login has failed");
				System.out.println(msg);
			}else if(msg.equals("auctionOk")){
				new AlertBox().display("System notification", "Successful registration");
				Utils.auction=new Auction(idvalue,namevalue,keyvalue);
				Parent root;
				Stage stage=Main.stage;
				try {
					root = FXMLLoader.load(getClass()
							.getResource("/application/LotList.fxml"));
					// Initialize new items maximum three
					stage.setTitle("Auctioneer registration");
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					new AlertBox().display("System notification", "Please check whether the auction center is online. Please restart it!");
					System.exit(1);
				}
			}
		}
	}

	/**
	 *Return Home Page
	 * @param event
	 */
	public void retunindex(ActionEvent event) {
		Utils.start();

	}




}