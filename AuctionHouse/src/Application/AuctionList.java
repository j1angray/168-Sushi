

package application;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import application.share.AlertBox;
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
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
/**
 * Open the auction list page
 * @throws IOException
 */
public class AuctionList  {
  private IntegerProperty index = new SimpleIntegerProperty();
  public final double getIndex() {
	return index.get();
  }
  //set index
  public final void setIndex(Integer value) {
	index.set(value);
  }
  //set index
  public IntegerProperty indexProperty() {
	return index;
  }
  //
  public  AuctionList(Stage stage) {
	Parent root;
	try {
      root = FXMLLoader.load(getClass().getResource("/application/MySecene.fxml"));
	  stage.setTitle("Auction House");
	  stage.setScene(new Scene(root));
	  stage.show();
	  } catch (IOException e) {
		  new AlertBox().display("System notification", "Please check whether the auction center is online. Please restart it!");
		  System.exit(1);
	    }
	}
}



