package application;

import java.util.ArrayList;
import java.util.List;

import application.share.entity.BankAccount;
import application.share.entity.UserRequest;
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
 * Common method class
 * @author
 *
 */
public class Utils {
	public static List<BankAccount> bankAccounts=new ArrayList<BankAccount>();
	public static List<UserRequest> resusts=new ArrayList<UserRequest>();
	/**
	 * Back to the home page
	 * @param primaryStage
	 */
	public static void start(Stage primaryStage){
		BorderPane root = new BorderPane();

		primaryStage.setTitle("Bank");
		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		root.setTop(menuBar);
		Menu fileMenu = new Menu("Menu");
		MenuItem userList=new MenuItem("User account list");
		MenuItem transactionList=new MenuItem("Trading flow");
		MenuItem exitMenuItem = new MenuItem("Exit");
		fileMenu.getItems().add(userList);
		fileMenu.getItems().add(transactionList);
		fileMenu.getItems().add(exitMenuItem);
		userList.setOnAction(
				event->{
					new BankUserList(primaryStage);
				}
				);
		transactionList.setOnAction(
				event->{
					new requestList(primaryStage);
				}
				);

		exitMenuItem.setOnAction(actionEvent -> Platform.exit());
		menuBar.getMenus().addAll(fileMenu);

		final Label lbl = new Label();

		lbl.setLayoutX(370);
		lbl.setLayoutY(400);
		Font font=new Font(18);
		lbl.setFont(font);
		lbl.setText("Welcome.");
		root.setCenter(lbl);
		Scene scene = new Scene(root, 700, 650, Color.WHITE);
		primaryStage.setScene(scene);

		primaryStage.show();

	}

}
