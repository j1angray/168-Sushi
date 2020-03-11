/***************************************************
 * Group member: Rongbing Xu, Shouyu Yang,
 * Xiao Liang, Haisen Li.
 * This Class are bulid a user list of bank.
 **************************************************/

package application;

import application.share.AlertBox;
import application.share.Utils;
import application.share.entity.BankAccount;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * A user participating in the auction
 *
 */
public class AuctionUser {
	private static BankAccount selectedBankAccount = null;
	private IntegerProperty index = new SimpleIntegerProperty();
	//get index
	public final double getIndex() {
		return index.get();
	}
	//set index
	public final void setIndex(Integer value) {
		index.set(value);
	}
    //get index property
	public IntegerProperty indexProperty() {
		return index;
	}
	private TableView<BankAccount> table = new TableView<BankAccount>();
	private final ObservableList<BankAccount> data =
			FXCollections.observableArrayList(Utils.auctionBankUser);
	public  AuctionUser() {
	}
//***********************************
//Each parameter's 
//type and name: 
//input: stage
//output; N/A 
//***********************************
	public  AuctionUser(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Bidders");
		stage.setWidth(600);
		stage.setHeight(730);
		final Label label = new Label("Bidder List");
		label.setFont(new Font("Arial", 20));
		TableColumn publicIdCol = new TableColumn("user name");
		publicIdCol.setCellValueFactory(
				new PropertyValueFactory<BankAccount, String>("bank name"));

		TableColumn nameCol = new TableColumn("bank keys");
		nameCol.setCellValueFactory(
				new PropertyValueFactory<BankAccount, String>("bank key"));

		TableColumn keyCol = new TableColumn("Secret key");
		keyCol.setMinWidth(200);
		keyCol.setCellValueFactory(
				new PropertyValueFactory<BankAccount, String>("get key"));
		table.getSelectionModel().select(0);
		table.setItems(data);
		table.getColumns().addAll(publicIdCol, nameCol, keyCol);
		table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);

		table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldvalue, Object newValue) {
				System.out.println(newValue+"---newvalue");
				selectedBankAccount = (BankAccount) newValue;
				setIndex(data.indexOf(newValue));
				System.out.println("OK");
			}
		});
		final Button returnindex = new Button("back to home");
		returnindex.setOnAction(actionEvent ->new Utils().start(stage));
		vbox.getChildren().addAll(label, table, returnindex );
		vbox.setPadding(new Insets(10, 0, 0, 10));
		((Group) scene.getRoot()).getChildren().addAll(vbox);
		stage.setScene(scene);
		stage.show();
	}
}
