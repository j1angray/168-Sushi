

package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.List;
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
 * Open the register list page of the auction
 * @throws IOException
 */
public class LotList  {
	private Lot selectedLot=null;
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
    private TableView<Lot> table = new TableView<Lot>();
    private final ObservableList<Lot> data =
    FXCollections.observableArrayList(Utils.lots);
    public  LotList() {
    }
    /**
     * Open the register list page of the auction
     * @param stage
     */
    public  LotList(Stage stage) {
      Scene scene = new Scene(new Group());
      stage.setTitle("View the auction register register");
      stage.setWidth(600);
      stage.setHeight(730);
      final Label label = new Label("LotList");
      label.setFont(new Font("Arial", 20));
      TableColumn publicIdCol = new TableColumn("AuctionOpenId");
      publicIdCol.setCellValueFactory(
        new PropertyValueFactory<Lot, String>("auctionId"));
        TableColumn nameCol = new TableColumn("LotId");
	    nameCol.setCellValueFactory(
	      new PropertyValueFactory<Lot, String>("prijectId"));
	      TableColumn keyCol = new TableColumn("LotName");
	      keyCol.setMinWidth(200);
	      keyCol.setCellValueFactory(
            new PropertyValueFactory<Lot, String>("name"));
            table.setItems(data);
	        table.getColumns().addAll(publicIdCol, nameCol, keyCol);
	        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            final VBox vbox = new VBox();
	        vbox.setSpacing(5);
            table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
	          @Override
	          public void changed(ObservableValue observable, Object oldvalue, Object newValue) {
                System.out.println(newValue+"---newvalue");
	            selectedLot = (Lot) newValue;
		        setIndex(data.indexOf(newValue));
	          }
	      });
	    final Button delButton = new Button("Revoke");
	    delButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
	      public void handle(ActionEvent e) {
	      	if(Utils.lots.size()>0){
	          Utils.lots.remove(selectedLot);
	        }
	        data.remove(index.get());
            table.getSelectionModel().clearSelection();
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



