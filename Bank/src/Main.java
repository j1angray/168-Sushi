
package application;

import application.socket.Server;
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
public class Main extends Application { //https://www.yiibai.com/javafx/javafx_borderpane.html
	/**
	 * Start the home page
	 */
	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();

		primaryStage.setTitle("Bank");
		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		root.setTop(menuBar);
		Menu fileMenu = new Menu("Menu options");
		MenuItem userList=new MenuItem("User account list");
		MenuItem transactionList=new MenuItem("Trading flow");
		MenuItem exitMenuItem = new MenuItem("Exit procedure");
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

		Thread server=new Server();
		server.start();
	}
	/**
	 * Start the main method
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
