/***************************************************
 * Group member: Rongbing Xu, Shouyu Yang,
 * Xiao Liang, Haisen Li.
 * This Class are bulid a user list of bank.
 **************************************************/

package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import application.share.AlertBox;
import application.share.Utils;
import application.share.entity.BankAccount;
import application.share.entity.UserRequest;
import application.share.entity.UserRequest;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
/**
 *Opening a user request to participate in the bid page
 * @throws IOException
 */
public class UserRequestList  {
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
	public IntegerProperty indexProperty() {
	  return index;
	}
    private TableView<BankAccount> table = new TableView<BankAccount>();
    private final ObservableList<BankAccount> data =
         FXCollections.observableArrayList(Utils.bankAccounts);
//
    public  UserRequestList() {
    }

    public  UserRequestList(Stage stage) {
      Scene scene = new Scene(new Group());
      stage.setTitle("View the user's request list");
      stage.setWidth(600);
      stage.setHeight(730);
      final Label label = new Label("User request list");
      label.setFont(new Font("Arial", 20));
      TableColumn publicIdCol = new TableColumn("UserName");
      publicIdCol.setCellValueFactory(
        new PropertyValueFactory<BankAccount, String>("bankName"));
        TableColumn nameCol = new TableColumn("BankKeys");
        nameCol.setCellValueFactory(
        new PropertyValueFactory<BankAccount, String>("bankKey"));
        TableColumn keyCol = new TableColumn("getKey");
        keyCol.setMinWidth(200);
        keyCol.setCellValueFactory(
          new PropertyValueFactory<BankAccount, String>("getKey"));
		  table.getSelectionModel().select(0);//默认第一行
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
				final Button delButton = new Button("Agree");
    	        delButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
	            	if(selectedBankAccount!=null){
	            		Utils.bankAccounts.remove(selectedBankAccount);
	            		Utils.auctionBankUser.add(selectedBankAccount);
		                data.remove(index.get());
		                table.getSelectionModel().clearSelection();
						}else{
							new AlertBox().display("System hints", "Selected object is invalid");
						}
					}
				});

	        final Button returnindex = new Button("Return");
	        returnindex.setOnAction(actionEvent ->new Utils().start(stage));
	        vbox.getChildren().addAll(label,delButton, table, returnindex );



	        vbox.setPadding(new Insets(10, 0, 0, 10));

	        ((Group) scene.getRoot()).getChildren().addAll(vbox);

	        stage.setScene(scene);
	        stage.show();
	    }
	}



