

package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import application.share.Utils;
import application.share.entity.Lot;
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
 * Look at the auction that has ended the auction
 * @throws IOException
 */
public class endedItemList {
	private Lot selectedLot=null;
	  private IntegerProperty index = new SimpleIntegerProperty();
	  //get index.get()
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
      private TableView<Lot> table = new TableView<Lot>();
	  private final ObservableList<Lot> data =
	    FXCollections.observableArrayList(Utils.lots);

	  public endedItemList() {
	  }
	  /**
	   * Look at the auction that has ended the auction
	   * @param stage
	   */
	  public endedItemList(Stage stage) {
	    Scene scene = new Scene(new Group());
	    stage.setTitle("Check the ended auction");
	    stage.setWidth(600);
	    stage.setHeight(730);

	    final Label label = new Label("Item List");
	    label.setFont(new Font("Arial", 20));

	    TableColumn publicIdCol = new TableColumn("Auction House Id");
	    publicIdCol.setCellValueFactory(
	            new PropertyValueFactory<Lot, String>("auctionId"));

	    TableColumn nameCol = new TableColumn("Item ID");
	    nameCol.setCellValueFactory(
	            new PropertyValueFactory<Lot, String>("Item ID"));

	    TableColumn keyCol = new TableColumn("Item Name");
	    keyCol.setMinWidth(200);
	        keyCol.setCellValueFactory(
	                new PropertyValueFactory<Lot, String>("name"));

	        TableColumn usernameCol = new TableColumn("Client Name");
	        usernameCol.setMinWidth(200);
	        usernameCol.setCellValueFactory(
	                new PropertyValueFactory<Lot, String>("userName"));

	        TableColumn moneCol = new TableColumn("Final Price");
	        moneCol.setMinWidth(200);
	        moneCol.setCellValueFactory(
	                new PropertyValueFactory<Lot, String>("price"));

	        table.setItems(data);
	        table.getColumns().addAll(publicIdCol, nameCol, keyCol,usernameCol,moneCol);
	        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

	        final VBox vbox = new VBox();
	        vbox.setSpacing(5);

	        final Button returnindex = new Button("Return");
	        returnindex.setOnAction(actionEvent -> new Utils().start(stage));
	        vbox.getChildren().addAll(label, table, returnindex );

	        vbox.setPadding(new Insets(10, 0, 0, 10));

	        ((Group) scene.getRoot()).getChildren().addAll(vbox);

	        stage.setScene(scene);
	        stage.show();
	    }
	}



