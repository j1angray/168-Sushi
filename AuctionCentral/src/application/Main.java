

package application;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import application.share.AlertBox;
import application.share.Utils;
import application.socket.BankScoket;
import application.socket.Server;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 *Program entrance
 * @author
 *
 */
public class Main extends Application {
	public static Charset charset = Charset.forName("UTF-8");
	//bank
	public static SocketChannel sc=null;
	@Override
	//start
	public void start(Stage primaryStage) {
		try {
			Thread server=new Server();
			server.start();
			BankScoket bank=new BankScoket();
			sc=bank.init();
			Utils.thread();
			BorderPane root = new BorderPane();
			primaryStage.setTitle("Auction Central");
			MenuBar menuBar = new MenuBar();
			menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
			root.setTop(menuBar);
			 Menu fileMenu = new Menu("Menu options");
			MenuItem auctionList=new MenuItem("Auction List");
			MenuItem lotList=new MenuItem("Lot List");
			MenuItem userRequest=new MenuItem("User Request List");
			MenuItem auctionUser=new MenuItem("Users in the Auction");
			MenuItem exitMenuItem = new MenuItem("Exit procedure");
			fileMenu.getItems().add(auctionList);
			fileMenu.getItems().add(lotList);
			fileMenu.getItems().add(userRequest);
			fileMenu.getItems().add(auctionUser);
			fileMenu.getItems().add(exitMenuItem);
            auctionList.setOnAction(
			  event->{
			  new AuctionList(primaryStage);
			});
		 	lotList.setOnAction(
			  event->{
				new LotList(primaryStage);
			});
			userRequest.setOnAction(
			  event->{
				new UserRequestList(primaryStage);
			});
			auctionUser.setOnAction(
			  event->{
				new AuctionUser(primaryStage);
			});
			exitMenuItem.setOnAction(actionEvent -> Platform.exit());
		 	menuBar.getMenus().addAll(fileMenu);
			final Label lbl = new Label();
		 	lbl.setLayoutX(370);
			lbl.setLayoutY(400);
			Font font=new Font(18);
			lbl.setFont(font);
			lbl.setText("'Welcome to the auction central.");
			root.setCenter(lbl);
			Scene scene = new Scene(root, 500, 450, Color.WHITE);
			primaryStage.setScene(scene);
			primaryStage.show();
			} catch(IOException e){
				new AlertBox().display("System hints", "The server starts an exception and is about to close. Please restart");
		        System.exit(1);
		  }
	}
	public static void main(String[] args) {
		launch(args);
	}
}


