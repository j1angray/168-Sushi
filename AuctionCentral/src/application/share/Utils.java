package application.share;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.AuctionList;
import application.AuctionUser;
import application.LotList;
import application.UserRequestList;
import application.share.entity.Auction;
import application.share.entity.AuctionRecord;
import application.share.entity.BankAccount;
import application.share.entity.Lot;
import application.socket.BankScoket;
import application.socket.Server;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Utils {
	public static List<AuctionRecord> records=new ArrayList<AuctionRecord>();

	public static List<Auction> auctions=new ArrayList<>();

	public static List<Lot> lots=new ArrayList<>();

	public static List<BankAccount> bankAccounts=new ArrayList<BankAccount>();

	public static List<BankAccount> auctionBankUser=new ArrayList<BankAccount>();


	/**
	 * Return Home Page
	 * @param primaryStage
	 */
	public static void start(Stage primaryStage) {
		BankScoket bank=new BankScoket();
		Utils.thread();

		BorderPane root = new BorderPane();

		primaryStage.setTitle("Auction center");


		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		root.setTop(menuBar);
		Menu fileMenu = new Menu("Menu options");
		MenuItem auctionList=new MenuItem("Auction list");
		MenuItem lotList=new MenuItem("Lot List");
		MenuItem userRequest=new MenuItem("User request list");
		MenuItem auctionUser=new MenuItem("A list of users participating in the auction");
		MenuItem exitMenuItem = new MenuItem("Exit procedure");
		fileMenu.getItems().add(auctionList);
		fileMenu.getItems().add(lotList);
		fileMenu.getItems().add(userRequest);
		fileMenu.getItems().add(auctionUser);
		fileMenu.getItems().add(exitMenuItem);
		auctionList.setOnAction(
				event->{
					new AuctionList(primaryStage);
				}
				);
		lotList.setOnAction(
				event->{
					new LotList(primaryStage);
				}
				);
		userRequest.setOnAction(
				event->{
					new UserRequestList(primaryStage);
				}
				);
		auctionUser.setOnAction(
				event->{
					new AuctionUser(primaryStage);
				}
				);
		exitMenuItem.setOnAction(actionEvent -> Platform.exit());
		menuBar.getMenus().addAll(fileMenu);

		final Label lbl = new Label();

		lbl.setLayoutX(270);
		lbl.setLayoutY(400);
		Font font=new Font(18);
		lbl.setFont(font);
		lbl.setText("'Welcome to the auction center.");
		root.setCenter(lbl);
		Scene scene = new Scene(root, 500, 450, Color.WHITE);
		primaryStage.setScene(scene);

		primaryStage.show();
	}
	/**
	 * Maximum value
	 */
	public static AuctionRecord max(){
		List<AuctionRecord> trecords=Utils.records;
		AuctionRecord num=null;
		if(trecords.size()>0){
			num=trecords.get(0);
			double numdouble=Double.valueOf(trecords.get(0).getMoney());
			for(int i=0;i<trecords.size();i++){
				if (Double.valueOf(trecords.get(i).getMoney()) > numdouble&&trecords.get(i).getState().equals("0")) {
					num = trecords.get(i);
					numdouble=Double.valueOf(trecords.get(i).getMoney());
				}
			}
		}
		return num;
	}
	/**
	 * Seek the maximum according to the auction ID
	 * @return
	 */
	public static AuctionRecord max(String id){
		List<AuctionRecord> trecords=Utils.records;
		AuctionRecord num=null;
		if(trecords.size()>0){
			for(int j=0;j<trecords.size();j++){
				if(id.equals(trecords.get(j).getPrijectId())){
					num=trecords.get(j);
					double numdouble=Double.valueOf(trecords.get(0).getMoney());
					for(int i=0;i<trecords.size();i++){
						if (Double.valueOf(trecords.get(i).getMoney()) > numdouble&&trecords.get(i).getState().equals("0")) {
							num = trecords.get(i);
							numdouble=Double.valueOf(trecords.get(i).getMoney());
						}
					}
				}
			}

		}
		return num;
	}




	/**
	 * Calculate the minimum value according to the auction ID
	 * @return
	 */
	public static AuctionRecord min(String id){
		List<AuctionRecord> trecords=Utils.records;
		AuctionRecord num=null;
		if(trecords.size()>0){
			for(int j=0;j<trecords.size();j++){
				if(id.equals(trecords.get(j).getPrijectId())){
					num=trecords.get(j);
					double numdouble=Double.valueOf(trecords.get(0).getMoney());
					for(int i=0;i<trecords.size();i++){
						if (Double.valueOf(trecords.get(i).getMoney()) < numdouble&&trecords.get(i).getState().equals("0")) {
							num = trecords.get(i);
							numdouble=Double.valueOf(trecords.get(i).getMoney());
						}
					}
				}
			}

		}
		return num;
	}

	/**
	 * Refresh an auction record in 30 seconds
	 */
	public static void thread(){


		final long timeInterval = 60000;
		Runnable runnable = new Runnable() {
			public void run() {
				while (true) {

					try {
						Thread.sleep(timeInterval);
						up();
					} catch (InterruptedException e) {
						System.out.println("Exception timer, please restart the project");
						System.exit(1);
					}
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}
	/**
	 * Refresh the auction record
	 */
	public static void up(){
		AuctionRecord ar=max();
		if(ar!=null){
			for(int i=0;i<Utils.lots.size();i++){
				Lot lot=Utils.lots.get(i);
				if(ar.getPrijectId().equals(lot.getPrijectId())){
					lot.setState("1");
					lot.setUserName(ar.getUserName());
					lot.setMoney(ar.getMoney());
					Utils.lots.set(i, lot);
					break;
				}
			}


			for(int i=0;i<Utils.records.size();i++){
				AuctionRecord r=Utils.records.get(i);
				if(r.getPrijectId().equals(ar.getPrijectId())){
					r.setState("1");
					Utils.records.set(i,r);
				}
			}

		}
	}



	/**
	 * Designation of auction
	 */
	public static void upbyId(String id,String state){
		AuctionRecord ar=max(id);
		List<Lot> lo=Utils.lots;
		if(ar!=null){
			 	for(int i=0;i<lo.size();i++){
				Lot lot=Utils.lots.get(i);
				if(ar.getPrijectId().equals(lot.getPrijectId())){
					lot.setState(state);
					lot.setUserName(ar.getUserName());
					lot.setMoney(ar.getMoney());
					lo.set(i, lot);
					break;
				}
			}
			 Utils.lots=lo;
			 List<AuctionRecord> record=Utils.records;
			for(int i=0;i<record.size();i++){
				AuctionRecord r=record.get(i);
				if(r.getPrijectId().equals(ar.getPrijectId())){
					r.setState(state);
					record.set(i,r);
				}
			}
			Utils.records=record;
		}
	}

	/**
	 * Obtaining the fixed mark and ending the bid according to the state of the auction
	 * @param type 1 the auction to end the auction by 2
	 * @param auticonId Auction house
	 * @return
	 */
	public static List<Lot> getLotByType(String type,String auticonId){
		List<Lot> ls=new ArrayList<Lot>();
		if(Utils.lots.size()>0){
			for (Lot lot : Utils.lots) {
				if(lot.getState().equals(type)&&lot.getAuctionId().equals(auticonId)){
					if(""==lot.getMoney()||"".equals(lot.getMoney())){
						lot.setMoney("0.0");
					}
					ls.add(lot);
				}
			}
		}
		return ls;
	}



	/**
	 * Obtaining the fixed mark and ending the bid according to the state of the auction
	 * @param type 1 the auction to end the auction by 2
	 * @param username user
	 * @return
	 */
	public static List<Lot> getLotUserByType(String type,String username){

		List<Lot> ls=new ArrayList<Lot>();
		if(Utils.lots.size()>0){
			for (Lot lot : Utils.lots) {
				if(lot.getState().equals(type)&&lot.getUserName().equals(username)){
					if(""==lot.getMoney()||"".equals(lot.getMoney())){
						lot.setMoney("0.0");
					}
					ls.add(lot);
				}
			}
		}
		return ls;
	}


}
