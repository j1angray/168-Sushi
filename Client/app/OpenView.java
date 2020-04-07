/***************************************************
 * Group member: Zihao Liu, Sun Su
 * This class is the interface.
 **************************************************/
package application;

import java.io.IOException;

import application.share.AlertBox;
import application.share.Utils;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Open the form
 * @author
 *
 */
public class OpenView {


	private IntegerProperty index = new SimpleIntegerProperty();


	public final double getIndex() {
		return index.get();
	}


	public final void setIndex(Integer value) {
		index.set(value);
	}


	public IntegerProperty indexProperty() {
		return index;
	}

	/**
	 *Create an account
	 * @param stage
	 */
	public static void OpenViewBank(Stage stage) {
		Parent root;
		try {
			root = FXMLLoader.load(new OpenView().getClass().getResource("/application/AddBankUser.fxml"));
			stage.setTitle("Create an account");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e) {
			new AlertBox().display(Utils.PROMPT, Utils.ERROR);
			System.exit(1);
		}


	}
	/**
	 * Open a bank account
	 * @param stage
	 */
	public static void OpenViewBackBank(Stage stage) {
		Parent root;
		try {
			root = FXMLLoader.load(new OpenView().getClass().getResource("/application/Back.fxml"));
			stage.setTitle("Open a bank account");
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			new AlertBox().display(Utils.PROMPT, Utils.ERROR);
			System.exit(1);
		}


	}

	/**
	 * Apply for participation in bidders
	 * @param stage
	 */
	public static void OpenViewAddUserAuction(Stage stage) {
		Parent root;
		try {
			root = FXMLLoader.load(new OpenView().getClass().getResource("/application/AddUserAuction.fxml"));
			stage.setTitle("Apply to participate in bidding");
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			new AlertBox().display(Utils.PROMPT, Utils.ERROR);
			System.exit(1);
		}


	}

}
