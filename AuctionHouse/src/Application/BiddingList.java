

package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
/**
 * Opening bid details page
 * @throws IOException
 */
public class BiddingList  {
	static   String  l="";
	static  String h="";

	private IntegerProperty index = new SimpleIntegerProperty();
	//return index
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
//
	public  BiddingList() {
	}

	/**
	 * View the details
	 * @param primaryStage
	 * @param lot
	 */
	public  BiddingList(Stage primaryStage,Lot lot) {
		primaryStage.setTitle("View Auction details");
		String json="@#@getrecord_"+lot.getPrijectId();
		try {
			String msg=Utils.msg;

			Main.sc.write(Main.charset.encode(json));
			Thread.sleep(1000);
			msg=Utils.msg;
			if("lowhigNo".equals(msg)){
				l="0.0";
				h="0.0";
			}else {
				String[]arr=Utils.msg.split(":");
				if("resultMaxAndMin".equals(arr[0])){
					l=arr[2];
					h=arr[1];
				}
				else{
					l="0.0";
					h="0.0";
				}
			}
		} catch (InterruptedException | IOException e1) {
			System.out.println("Booms");
		}




		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Details of the current auction");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);



		Label pw = new Label("Auctioneer ID:");
		grid.add(pw, 0, 2);

		Label pwBox = new Label(lot.getPrijectId());
		grid.add(pwBox, 1, 2);

		Label name = new Label("Auction name:");
		grid.add(name, 0, 3);

		Label nameBox = new Label(lot.getName());
		grid.add(nameBox, 1, 3);

		Label lowprice = new Label("lowest price:");
		grid.add(lowprice, 0, 4);

		Label lowpriceBox = new Label(l);
		grid.add(lowpriceBox, 1, 4);

		Label highest = new Label("highest price:");
		grid.add(highest, 0, 5);

		Label highestBox = new Label(h);
		grid.add(highestBox, 1,5);


		Button btn = new Button("highest bid");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 6);


		Button rel= new Button("Return");
		HBox relBtn = new HBox(10);
		relBtn.setAlignment(Pos.BOTTOM_RIGHT);
		relBtn.getChildren().add(rel);
		grid.add(relBtn, 1, 7);


		final Text actiontarget = new Text();
		grid.add(actiontarget, 0, 8);

		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				actiontarget.setFill(Color.FIREBRICK);

				try {
					String json="@#@lotbyid_"+lot.getPrijectId();
					Main.sc.write(Main.charset.encode(json));
					Thread.sleep(3000);
					Utils.start(primaryStage);
				} catch (IOException | InterruptedException e1) {
					new AlertBox().display("System notification", "Please check whether the auction center is online. Please restart it!");
					System.exit(1);
				}

			}
		});
		
		rel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				Utils.start(primaryStage);
			}
		});

		Scene scene = new Scene(grid, 500, 475);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}



