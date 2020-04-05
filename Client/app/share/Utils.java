/***************************************************
 * Group member: Zihao Liu, Sun Su
 **************************************************/
package application.share;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import application.AuctionList;
import application.LotNoList;
import application.LotOkList;
import application.Main;
import application.OpenView;
import application.share.entity.Auction;
import application.share.entity.BankAccount;
import application.share.entity.Lot;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 *Public method
 * @author
 *
 */
public class Utils {
	public static Charset charset = Charset.forName("UTF-8");
	public static SocketChannel sc=null;
	public static List<Auction> auctions=new ArrayList<>();
	public static BankAccount bankuser=null;
	public static Stage stage=null;
	public static List<Lot> lots=new ArrayList<Lot>();
	public static final String PROMPT="Prompt";
	public static final String ERROR="Start the exception to check whether the auction center and the bank are open";
	/**
	 * Return to the home page
	 * @param primaryStage
	 */
	public static void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		primaryStage.setTitle("Client");
		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		root.setTop(menuBar);
		Menu fileMenu = new Menu("Options");
		MenuItem addUser=new MenuItem("Create a Bidding Account");
		MenuItem openBankUser=new MenuItem("Open a Bank Account");
		MenuItem addAuction=new MenuItem("Participate in Bidding");
		MenuItem auctionList=new MenuItem("View Auction Items");
		MenuItem getlogbadding=new MenuItem("Show the auctioneer");
		MenuItem getlogok=new MenuItem("Check the bid");
		MenuItem exitMenuItem = new MenuItem("Exit Client");
		fileMenu.getItems().add(addUser);
		fileMenu.getItems().add(openBankUser);
		fileMenu.getItems().add(addAuction);
		fileMenu.getItems().add(auctionList);
		fileMenu.getItems().add(getlogbadding);
		fileMenu.getItems().add(getlogok);
		fileMenu.getItems().add(exitMenuItem);
		addUser.setOnAction(
				event->{
					if(Utils.bankuser==null){
					new OpenView().OpenViewBank(primaryStage);
					}else{
						new AlertBox().display("Utils.PROMPT", "Have already registered");
					}
				}
				);
		openBankUser.setOnAction(
				event->{
					if(Utils.bankuser!=null){
							new AlertBox().display("Utils.PROMPT", "Already have a bank account");
						}else{
							new OpenView().OpenViewBackBank(primaryStage);
						}
				}
				);
		addAuction.setOnAction(
				event->{
					if(Utils.bankuser!=null){
						new OpenView().OpenViewAddUserAuction(primaryStage);
					}else{
						new AlertBox().display("Utils.PROMPT", "Please open a bank account first");
					}
				}
				);
		getlogbadding.setOnAction(
				event->{
					if(Utils.bankuser!=null){
						Utils.getDetailsByType("1");
						new LotNoList(primaryStage);
					}else{
						new AlertBox().display("Utils.PROMPT", "Please open a bank account first");
					}
				}
				);
		getlogok.setOnAction(
				event->{
					if(Utils.bankuser!=null){
						Utils.getDetailsByType("2");
						new LotOkList(primaryStage);
					}else{
						new AlertBox().display("Utils.PROMPT", "Please open a bank account first");
					}
				}
				);

		auctionList.setOnAction(
				event->{
					Utils.getDetails();
					new AuctionList(primaryStage);
				}
				);
		exitMenuItem.setOnAction(actionEvent -> Platform.exit());
		menuBar.getMenus().addAll(fileMenu);

		final Label lbl = new Label();
		lbl.setLayoutX(170);
		lbl.setLayoutY(80);
		Font font=new Font(18);
		lbl.setFont(font);
		lbl.setText("Welcome to Client GUI\n\n Please discover the Options");

		root.setCenter(lbl);
		Scene scene = new Scene(root, 700, 650, Color.WHITE);
		primaryStage.setScene(scene);
		primaryStage.show();
		Utils.stage=primaryStage;
	}




	/**
	 * Look at the auction
	 */
	public static void getDetails(){
		String msg="@#@getdetailsall_all";
		try {
			Main.scAuction.write(Main.charset.encode(msg));
			msg=AuctionScoket.msg;
			Thread.sleep(1000);
			msg=AuctionScoket.msg;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}

		if(msg!=null){
			String[] msgArr = msg.toString().split(":");
			Utils.lots.clear();

			if(msgArr.length>1){
				for(String m:msgArr){
					String[] arr = m.toString().split("_");
					Lot lot=new Lot("",arr[0],arr[1]);
					Utils.lots.add(lot);
				}
			}

		}

	}


	/**
	 * View the user's calibration
	 */
	public static void getDetailsByType(String type){
		if(Utils.bankuser!=null){
			Utils.lots.clear();
			String msg="";
			msg="@#@getlotnotbank_"+type+"_"+Utils.bankuser.getBankKey()+"_"+Utils.bankuser.getBankName();
			try {
				Main.scAuction.write(Main.charset.encode(msg));
				Thread.sleep(3000);
				msg=AuctionScoket.msg;
			} catch (IOException | InterruptedException e) {
				new AlertBox().display(Utils.PROMPT, Utils.ERROR);
				System.exit(1);
			}
			if(msg!=null){

				String[] msgArr = msg.toString().split(":");
				if(msgArr[0].equals("lotok")){
					Utils.lots.clear();
					if(msgArr.length>1){
						for(String m:msgArr){
							if(!m.equals("lotok")){
								String[] arr = m.toString().split("_");
								Lot lot=new Lot(arr[0],arr[1],arr[2],arr[3]);
								Utils.lots.add(lot);
							}

						}
					}
				}


			}
		}

	}
}
