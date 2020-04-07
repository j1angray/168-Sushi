

package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import application.share.entity.BankAccount;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
/**
 *Bank users
 * @throws IOException
 */
public class BankUserList  {

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


	    private TableView<BankAccount> table = new TableView<BankAccount>();
	    private final ObservableList<BankAccount> data =
	            FXCollections.observableArrayList(Utils.bankAccounts);

	    public  BankUserList() {
	    }
	    /**
	     * Open the bank user window
		 * Build the windows of UserList
	     * @param stage
	     */
	    public  BankUserList(Stage stage) {
	        Scene scene = new Scene(new Group());
	        stage.setTitle("User list");
	        stage.setWidth(480);
	        stage.setHeight(530);
	        int i=Utils.bankAccounts.size();
	        final Label label = new Label("Registered user list");
	        label.setFont(new Font("Arial", 20));

	        TableColumn bankNameCol = new TableColumn("UserName");
	        bankNameCol.setCellValueFactory(
	                new PropertyValueFactory<BankAccount, String>("bankName"));

	        TableColumn bankAccountCol = new TableColumn("account");
	        bankAccountCol.setCellValueFactory(
	                new PropertyValueFactory<BankAccount, String>("bankAccount"));

	        TableColumn bankKeyCol = new TableColumn("Bank keys");
	        bankKeyCol.setMinWidth(200);
	        bankKeyCol.setCellValueFactory(
	                new PropertyValueFactory<BankAccount, String>("bankKey"));

	        TableColumn moneyCol = new TableColumn("Deposit");
	        moneyCol.setCellValueFactory(
	                new PropertyValueFactory<BankAccount, String>("Deposit"));




	        table.setItems(data);
	        table.getColumns().addAll(bankNameCol, bankAccountCol, bankKeyCol,moneyCol);
	        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

	        final VBox vbox = new VBox();
	        vbox.setSpacing(5);


	        final Button returnindex = new Button("Back Home");
	        returnindex.setOnAction(actionEvent ->new Utils().start(stage));
	        vbox.getChildren().addAll(label, table, returnindex );


	        vbox.setPadding(new Insets(10, 0, 0, 10));

	        ((Group) scene.getRoot()).getChildren().addAll(vbox);

	        stage.setScene(scene);
	        stage.show();
	    }
	}



