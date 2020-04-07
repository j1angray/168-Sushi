
package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import application.share.entity.UserRequest;
import application.share.entity.UserRequest;
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
 * Transaction record
 * @throws IOException
 */
public class requestList  {




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


	    private TableView<UserRequest> table = new TableView<UserRequest>();
	    private final ObservableList<UserRequest> data =
	            FXCollections.observableArrayList(Utils.resusts);

	    public  requestList() {
	    }
	    /**
	     * Transaction record
	     * @param stage
	     */
	    public  requestList(Stage stage) {
	        Scene scene = new Scene(new Group());
	        stage.setTitle("View the transaction records");
	        stage.setWidth(680);
	        stage.setHeight(530);
	        int i=Utils.resusts.size();
	        final Label label = new Label("Transaction record trading flow");
	        label.setFont(new Font("Arial", 20));

	        TableColumn bankNameCol = new TableColumn("User name");
	        bankNameCol.setCellValueFactory(
	                new PropertyValueFactory<UserRequest, String>("userName"));

	        TableColumn bankKeyCol = new TableColumn("Bank keys");
	        bankKeyCol.setCellValueFactory(
	                new PropertyValueFactory<UserRequest, String>("bankKey"));

	        TableColumn dateCol  = new TableColumn("Trading time");
	        dateCol.setMinWidth(200);
	        dateCol.setCellValueFactory(
	                new PropertyValueFactory<UserRequest, String>("date"));

	        TableColumn typeCol = new TableColumn("Type of transaction");
	        typeCol.setCellValueFactory(
	                new PropertyValueFactory<UserRequest, String>("type"));
	        TableColumn ipCol = new TableColumn("Ip");

	        ipCol.setCellValueFactory(
	                new PropertyValueFactory<UserRequest, String>("id"));




	        table.setItems(data);
	        table.getColumns().addAll(bankNameCol, bankKeyCol, dateCol,typeCol,ipCol);
	        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

	        final VBox vbox = new VBox();
	        vbox.setSpacing(5);


	        final Button returnindex = new Button("Return");
	        returnindex.setOnAction(actionEvent ->new Utils().start(stage));
	        vbox.getChildren().addAll(label, table, returnindex );
	        vbox.setPadding(new Insets(10, 0, 0, 10));
	        ((Group) scene.getRoot()).getChildren().addAll(vbox);
	        stage.setScene(scene);
	        stage.show();
	    }
	}



