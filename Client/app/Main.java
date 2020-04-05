/***************************************************
 * Group member: Zihao Liu, Su Sun
 * This class is the main.
 * **************************************************/
package application;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import application.share.AlertBox;
import application.share.AuctionScoket;
import application.share.BankScoket;
import application.share.Utils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *Start the client agent
 * @author
 *
 */
public class Main extends Application {
public static Charset charset = Charset.forName("UTF-8");
//bank
public static SocketChannel sc=null;
//auction
public static SocketChannel scAuction=null;
	/**
	 * Starting method
	 */
	@Override
	public void start(Stage primaryStage) {
		BankScoket banks=new BankScoket();
		AuctionScoket as=new AuctionScoket();
		try {
			sc=banks.init();
			scAuction=as.init();
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
		} catch (IOException e) {
			new AlertBox().display(Utils.PROMPT, Utils.ERROR);
			System.exit(1);
		}

	}

	/**
	 *Boot program master thread method
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
