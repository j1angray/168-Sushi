/***************************************************
 * Group member: Zihao Liu, Sun Su
 * This class is the list of the items that do not work.
 **************************************************/
package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import application.share.AlertBox;
import application.share.AuctionScoket;
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
 * View the set of auctions that have been set
 * @throws IOException
 */
public class LotNoList  {

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

	    public  LotNoList() {
	    }

	    public  LotNoList(Stage stage) {
	        Scene scene = new Scene(new Group());
	        stage.setTitle("List of approved Auctions");
	        stage.setWidth(600);
	        stage.setHeight(730);
	        List<Lot> lots=Utils.lots;
	        final Label label = new Label("To be confirmed");
	        label.setFont(new Font("Arial", 20));

	        TableColumn nameCol = new TableColumn("Auctioneer ID");
	        nameCol.setCellValueFactory(
	                new PropertyValueFactory<Lot, String>("prijectId"));

	        TableColumn keyCol = new TableColumn("Auction name");
	        keyCol.setMinWidth(200);
	        keyCol.setCellValueFactory(
	                new PropertyValueFactory<Lot, String>("name"));

	        TableColumn usernameCol = new TableColumn("Bid winner");
	        usernameCol.setMinWidth(200);
	        usernameCol.setCellValueFactory(
	                new PropertyValueFactory<Lot, String>("userName"));

	        TableColumn moneCol = new TableColumn("Bid price");
	        moneCol.setMinWidth(200);
	        moneCol.setCellValueFactory(
	                new PropertyValueFactory<Lot, String>("deposit"));

	        table.setItems(data);
	        table.getColumns().addAll(nameCol, keyCol,usernameCol,moneCol);
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



	        final Button okButton = new Button("Confirmation of items");
	        okButton.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent e) {
	            	if(Utils.lots.size()>0){
	            		if(selectedLot!=null){
	            			String msg="@#@oklot_"+selectedLot.getPrijectId()+"_"+Utils.bankuser.getBankName()+"_"+Utils.bankuser.getBankKey();
	            			 	try {
									Main.scAuction.write(Main.charset.encode(msg));
		            				Thread.sleep(3000);
		            				if("saleOk".equals(AuctionScoket.msg)){
										new AlertBox().display(Utils.PROMPT, "Confirm the success of the auction");
		            				}else if("saleNo".equals(AuctionScoket.msg)){
										new AlertBox().display(Utils.PROMPT, "Confirmation of abnormal auction");
		            				}
								} catch (IOException | InterruptedException e1) {
									new AlertBox().display(Utils.PROMPT, Utils.ERROR);
								}



	            			new AlertBox().display(Utils.PROMPT, "You have confirmed that the auction is unmistakable. The auction center has been withdrawn successfully. Please go to the list and check the record.");
	            		}else{
	            			new AlertBox().display(Utils.PROMPT, "Please choose an auction to check the details");
	            		}
	            	}
	            }
	        });



	        final Button returnindex = new Button("Return");
	        returnindex.setOnAction(actionEvent -> new Utils().start(stage));
	        vbox.getChildren().addAll(label,okButton, table, returnindex );



	        vbox.setPadding(new Insets(10, 0, 0, 10));

	        ((Group) scene.getRoot()).getChildren().addAll(vbox);

	        stage.setScene(scene);
	        stage.show();
	    }
	}



