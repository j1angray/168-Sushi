/***************************************************
 * Group member: Rongbing Xu, Shouyu Yang,
 * Xiao Liang, Haisen Li.
 * This Class are bulid a user list of bank.
 **************************************************/

package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import application.share.Utils;
import application.share.entity.Auction;
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
 * Open the auction list page
 * @throws IOException
 */
public class AuctionList{
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
    private TableView<Auction> table = new TableView<Auction>();
    private final ObservableList<Auction> data =
      FXCollections.observableArrayList(new Utils().auctions);
      public  AuctionList() {
	  }
//***********************************
//Each parameter's 
//type and name: 
//  input: stage
//  output; N/A 
//***********************************
    public  AuctionList(Stage stage) {
      Scene scene = new Scene(new Group());
        stage.setTitle("Check auction house");
        stage.setWidth(600);
        stage.setHeight(730);
        final Label label = new Label("List of auction house");
        label.setFont(new Font("Arial", 20));
        TableColumn publicIdCol = new TableColumn("Auction list");
        publicIdCol.setCellValueFactory(
          new PropertyValueFactory<Auction, String>("Public ID"));
          TableColumn nameCol = new TableColumn("Auction Name");
	      nameCol.setCellValueFactory(
	        new PropertyValueFactory<Auction, String>("name"));
	        TableColumn keyCol = new TableColumn("IP address");
	        keyCol.setMinWidth(200);
	        keyCol.setCellValueFactory(
              new PropertyValueFactory<Auction, String>("key"));
	        table.setItems(data);
	        table.getColumns().addAll(publicIdCol, nameCol, keyCol);
	        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	        final VBox vbox = new VBox();
	        vbox.setSpacing(5);
	        final Button returnindex = new Button("Back to home");
	        returnindex.setOnAction(actionEvent ->new Utils().start(stage));
            vbox.getChildren().addAll(label, table, returnindex );
	        vbox.setPadding(new Insets(10, 0, 0, 10));

	        ((Group) scene.getRoot()).getChildren().addAll(vbox);

	        stage.setScene(scene);
	        stage.show();
    }
}



