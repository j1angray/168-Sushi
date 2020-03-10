package application.share;


import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import application.AuctionList;
import application.LotList;
import application.LotNoList;
import application.LotOkList;
import application.Main;
import application.share.entity.Auction;
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

public class Utils {
	public static Auction auction=null;
	public static List<Lot>lots=new ArrayList<>();
	public static String msg="";


	/**
	 * Return Home Page
	 * @param primaryStage
	 */
	public static void start() {
		Stage primaryStage=new Stage();
		BorderPane root = new BorderPane();


		primaryStage.setTitle("Auction house");


		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		root.setTop(menuBar);
		Menu fileMenu = new Menu("Menu options");
		MenuItem addAuction=new MenuItem("Registered auction house");
		MenuItem auctionDateils=new MenuItem("Details of the auction");
		MenuItem nobid=new MenuItem("No Bid");
		MenuItem bid=new MenuItem("Bid History");
		MenuItem exitMenuItem = new MenuItem("Exit procedure");
		fileMenu.getItems().add(addAuction);
		fileMenu.getItems().add(auctionDateils);
		fileMenu.getItems().add(nobid);
		fileMenu.getItems().add(bid);
		fileMenu.getItems().add(exitMenuItem);
		addAuction.setOnAction(
				event->{
					new AuctionList(primaryStage);
				}
				);
		auctionDateils.setOnAction(
				event->{
					Utils.getDetails();
					new LotList(primaryStage);
				}
				);
		bid.setOnAction(
				event->{
					Utils.getDetailsByType("2");
					new LotOkList(primaryStage);
				}
				);
		nobid.setOnAction(
				event->{
					Utils.getDetailsByType("1");
					new LotNoList(primaryStage);
				}
				);

		exitMenuItem.setOnAction(actionEvent -> Platform.exit());

		menuBar.getMenus().addAll(fileMenu);


		final Label lbl = new Label();


		lbl.setLayoutX(370);
		lbl.setLayoutY(400);
		Font font=new Font(18);
		lbl.setFont(font);
		lbl.setText("Welcome to the auction house.");

		root.setCenter(lbl);

		Scene scene = new Scene(root, 700, 650, Color.WHITE);
		primaryStage.setScene(scene);

		primaryStage.show();
		Main.stage=primaryStage;
	}


	/**
	 * Back to the home page
	 * @param primaryStage
	 */
	public static void start(Stage primaryStage) {
		BorderPane root = new BorderPane();

		primaryStage.setTitle("Auction house");


		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		root.setTop(menuBar);
		Menu fileMenu = new Menu("Menu options");
		MenuItem addAuction=new MenuItem("Registered auction house");
		MenuItem auctionDateils=new MenuItem("Details of the auction");
		MenuItem nobid=new MenuItem("No Bid");
		MenuItem bid=new MenuItem("Bid History");
		MenuItem exitMenuItem = new MenuItem("Exit procedure");
		fileMenu.getItems().add(addAuction);
		fileMenu.getItems().add(auctionDateils);
		fileMenu.getItems().add(nobid);
		fileMenu.getItems().add(bid);
		fileMenu.getItems().add(exitMenuItem);
		addAuction.setOnAction(
				event->{
					new AuctionList(primaryStage);
				}
				);
		auctionDateils.setOnAction(
				event->{
					Utils.getDetails();
					new LotList(primaryStage);
				}
				);
		bid.setOnAction(
				event->{
					Utils.getDetailsByType("2");
					new LotOkList(primaryStage);
				}
				);
		nobid.setOnAction(
				event->{
					Utils.getDetailsByType("1");
					new LotNoList(primaryStage);
				}
				);

		exitMenuItem.setOnAction(actionEvent -> Platform.exit());

		menuBar.getMenus().addAll(fileMenu);


		final Label lbl = new Label();


		lbl.setLayoutX(170);
		lbl.setLayoutY(200);
		Font font=new Font(18);
		lbl.setFont(font);
		lbl.setText("Welcome to the auction house.");

		root.setCenter(lbl);

		Scene scene = new Scene(root, 700, 650, Color.WHITE);
		primaryStage.setScene(scene);

		primaryStage.show();
		Main.stage=primaryStage;
	}


	/**
	 * Check the details of the auction house
	 */
	public static void getDetails(){
		if(Utils.auction!=null){
			String msg="@#@getdetails_"+Utils.auction.getPublicId();
			try {
				Main.sc.write(Main.charset.encode(msg));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {

				new AlertBox().display("System notification", "Please check whether the auction center is online. Please restart it!");
				System.exit(1);
			}
			msg=Utils.msg;
			if(msg!=null){
				String[] msgArr = msg.toString().split(":");
				Utils.lots.clear();
				for(int i=0;i<100;i++){
					msg=Utils.msg;
				}

				if(msgArr.length>1){
					for(String m:msgArr){
						String[] arr = m.toString().split("_");
						Lot lot=new Lot(Utils.auction.getPublicId(),arr[0],arr[1]);
						Utils.lots.add(lot);
					}
				}

			}
		}

	}



	/**
	 * Check the auction house
	 */
	public static void getDetailsByType(String type){
		if(Utils.auction!=null){
			Utils.lots.clear();
			String msg="";
			msg="@#@getlotnot_"+type+"_"+Utils.auction.getPublicId();
			System.out.println("Msg: "+msg);
			try {
				Main.sc.write(Main.charset.encode(msg));
				Thread.sleep(3000);
				msg=Utils.msg;
			} catch (IOException | InterruptedException e) {
				new AlertBox().display("System notification", "Please check whether the auction center is online. Please restart it!");
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
								Lot lot=new Lot(Utils.auction.getPublicId(),arr[0],arr[1],arr[2],arr[3]);
								Utils.lots.add(lot);
							}

						}
					}
				}


			}
		}

	}
}
