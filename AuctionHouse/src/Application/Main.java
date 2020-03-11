
 
package application;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import application.share.AlertBox;
import application.share.Scoket;
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
 *Program entrance
 * @author
 *
 */
public class Main extends Application {

	public static Charset charset = Charset.forName("UTF-8");
	public static SocketChannel sc=null;
	public static 	Stage stage=null;
	/**
	 * start-up
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			sc=new Scoket().init();
			BorderPane root = new BorderPane();
			primaryStage.setTitle("Auction house");

			MenuBar menuBar = new MenuBar();
			menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
			root.setTop(menuBar);
			Menu fileMenu = new Menu("Menu options");
			MenuItem newAuctionHouse=new MenuItem("New Auction House");
			MenuItem auctionDetail=new MenuItem("Auction Detail");
			MenuItem itemBiddingPrices=new MenuItem("Item Bidding prices");
			MenuItem endedBidding=new MenuItem("Ended Bidding");
			MenuItem exitMenuItem = new MenuItem("Exit procedure");
			fileMenu.getItems().add(newAuctionHouse);
			fileMenu.getItems().add(auctionDetail);
			fileMenu.getItems().add(itemBiddingPrices);
			fileMenu.getItems().add(endedBidding);
			fileMenu.getItems().add(exitMenuItem);
			newAuctionHouse.setOnAction(
					event->{
						new AuctionList(primaryStage);
					}
					);
			auctionDetail.setOnAction(
					event->{
						Utils.getDetails();
						new LotList(primaryStage);
					}
					);
			endedBidding.setOnAction(
					event->{
						Utils.getDetailsByType("2");
						new endedItemList(primaryStage);
					}
					);
			itemBiddingPrices.setOnAction(
					event->{
						Utils.getDetailsByType("1");
						new bidItemList(primaryStage);
					}
					);

			exitMenuItem.setOnAction(actionEvent -> Platform.exit());
			menuBar.getMenus().addAll(fileMenu);

			final Label lbl = new Label();

			lbl.setLayoutX(570);
			lbl.setLayoutY(600);
			Font font=new Font(18);
			lbl.setFont(font);
			lbl.setText("Welcome to the Auction House Menu.");

			root.setCenter(lbl);

			Scene scene = new Scene(root, 700, 650, Color.WHITE);
			primaryStage.setScene(scene);

			primaryStage.show();
			stage=primaryStage;
		} catch (IOException e1) {
			new AlertBox().display("System notification", "Please check whether the auction center is online. Please restart it!");
			System.exit(1);
		}
	}

	/**
	 * Boot program master method
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
