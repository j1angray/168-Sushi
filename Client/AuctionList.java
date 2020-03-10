/***************************************************
 * Group member: Su Sun.
 * This class is the list of auction.
 **************************************************/
package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import application.share.AlertBox;
import application.share.Utils;
import application.share.entity.Auction;
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
 *Auction window
 * @author
 *
 */
public class AuctionList  {



	private Lot selectedLot=null;

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


	    private TableView<Lot> table = new TableView<Lot>();
	    private final ObservableList<Lot> data =
	            FXCollections.observableArrayList(Utils.lots);

	    public  AuctionList() {
	    }

	    /**
	     * View the list of auction items
	     * @param stage
	     */
	    public  AuctionList(Stage stage) {
	    	table.getSelectionModel().select(0);
	    	Scene scene = new Scene(new Group());
		        stage.setTitle("Look at the details of the auction");
		        stage.setWidth(600);
		        stage.setHeight(730);

		        final Label label = new Label("List of auctions");
		        label.setFont(new Font("Arial", 20));


		        TableColumn nameCol = new TableColumn("Auctioneer ID");
		        nameCol.setMinWidth(200);
		        nameCol.setCellValueFactory(
		                new PropertyValueFactory<Lot, String>("project Id"));

		        TableColumn keyCol = new TableColumn("Auction name");
		        keyCol.setMinWidth(200);
		        keyCol.setCellValueFactory(
		                new PropertyValueFactory<Lot, String>("name"));

		        table.setItems(data);
		        table.getColumns().addAll(nameCol, keyCol);
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



		        final Button delButton = new Button("details");
		        delButton.setOnAction(new EventHandler<ActionEvent>() {

		            @Override
		            public void handle(ActionEvent e) {
		            	if(Utils.lots.size()>0){
		            		if(selectedLot!=null){
		            			if(Utils.bankuser!=null){
			            			new BiddingList(stage,selectedLot);
		            			}else{
			            			new AlertBox().display(Utils.PROMPT, "Please register or open the account first");
		            			}
		            		}else{
		            			new AlertBox().display(Utils.PROMPT, "Please choose a item to check the details");
		            		}
		            	}
		            }
		        });

		        final Button returnindex = new Button("Back to the home page");
		        returnindex.setOnAction(actionEvent -> new Utils().start(stage));

		        vbox.getChildren().addAll(label,delButton, table, returnindex );



		        vbox.setPadding(new Insets(10, 0, 0, 10));

		        ((Group) scene.getRoot()).getChildren().addAll(vbox);

		        stage.setScene(scene);
		        stage.show();
	    }
	}



