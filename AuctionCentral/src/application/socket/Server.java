package application.socket;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import application.Main;
import application.share.Utils;
import application.share.entity.Auction;
import application.share.entity.AuctionRecord;
import application.share.entity.BankAccount;
import application.share.entity.Lot;

/**
 * Auction center server
 */
public class Server extends Thread{

	private Selector selector;

	private final static int PORT=9900;

	private HashSet<String> online = new HashSet<String>();

	private Charset charset =  Charset.forName("UTF-8");


	private Map<String,SocketChannel> objects=new HashMap<>();



	private Map<String,String> ipobjects=new HashMap<>();

	public static void main(String[] args) {
		Server server= new Server();
		try {
			server.run ();
		} catch (Exception e) {
			System.out.println("Server exception, please restart!");
			System.exit(1);
		}
	}


	/**
	 *initialize server
	 *Inherit from the Thread thread to implement the Run method
	 * @author
	 * @throws IOException
	 */
	public void run() {

		try {
			this.selector = Selector.open();

			ServerSocketChannel server = ServerSocketChannel.open();
			ServerSocket serverSocket = server.socket();
			InetSocketAddress address = new InetSocketAddress(PORT);
			serverSocket.bind(address);

			server.configureBlocking(false);
			server.register(selector, SelectionKey.OP_ACCEPT);

			System.out.println("The server is waiting for the client connection...");

			while(true){
				int nums = this.selector.select();
				if (nums<=0) {
					continue;
				}

				Set<SelectionKey> selectionKeys = this.selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectionKeys.iterator();
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					iterator.remove();
					dealWithSelectionKey(server,key);
				}
			}
		} catch (Exception e) {
			System.out.println("Server exception, please restart!");
			System.exit(1);
		}
	}


	/**
	 * Processing select key
	 * @param server
	 * @param key
	 * @author 1
	 * @throws IOException
	 */
	private void dealWithSelectionKey(ServerSocketChannel server, SelectionKey key) throws IOException {
		if (key.isAcceptable()) {

			SocketChannel sChannel = server.accept();

			sChannel.configureBlocking(false);

			 sChannel.register(selector, SelectionKey.OP_READ);

			 key.interestOps(SelectionKey.OP_ACCEPT);

			System.out.println("The client connection is successful and the server is listening to the client :" + sChannel.socket().getRemoteSocketAddress());

			sChannel.write(charset.encode("Auction house is successfully connected with auction center"));
		}
		else if (key.isReadable()) {

			SocketChannel sc = (SocketChannel) key.channel();
			StringBuffer content = new StringBuffer();
			ByteBuffer buffer = ByteBuffer.allocate(1024);

			try {
				while(sc.read(buffer)>0){
					buffer.flip();
					content.append(charset.decode(buffer));
				}

				key.interestOps(SelectionKey.OP_READ);
			} catch (Exception e) {
				String ip=sc.socket().getRemoteSocketAddress().toString();
				System.out.println("The IP address is:"+ip+"Disconnected user connections!");
				String name=ipobjects.get(ip);
				if(selector.keys().size()>0){
					broadCast(selector, sc, "System notification: user<"+name+">Downline!");
				}
				ipobjects.remove(ip);
				online.remove(name);
				objects.remove(name);
				key.cancel();
				sc.close();
			}


			if (content.length()>0) {

				String[] msgArr = content.toString().split("_");
				if (msgArr!=null && msgArr.length>1){
					String standard = msgArr[0];
					if(standard.equals("@#@add")){
						String publicId=msgArr[1];
						String aname=msgArr[2];
						String akey=msgArr[3];
						Auction auction=new Auction(publicId, aname, akey);
						int count=0;
						for (Auction a : Utils.auctions) {
							if(a.getKey().equals(akey)){
								count+=1;
							}
							if(a.getName().equals(aname)){
								count+=1;
							}
							if(a.getPublicId().equals(publicId)){
								count+=1;
							}
						}
						if(count==0){
							Utils.auctions.add(auction);
							broadCastThis(selector,sc,"auctionOk");
						}else{
							broadCastThis(selector,sc,"auctionfail");
						}
					}else if(standard.equals("@#@addauction")){

						String auctionid=msgArr[1];
						int count=0;
						for(Lot lot:Utils.lots){
							if(lot.getAuctionId().equals(auctionid)){
								count+=1;
							}
						}
						if(count==0){
							String name1=msgArr[2];
							String name2=msgArr[3];
							String name3=msgArr[4];
							Lot lot1=new Lot(auctionid, UUID.randomUUID().toString().replaceAll("-", ""),name1);
							lot1.setState("0");
							Lot lot2=new Lot(auctionid, UUID.randomUUID().toString().replaceAll("-", ""),name2);
							lot2.setState("0");
							Lot lot3=new Lot(auctionid, UUID.randomUUID().toString().replaceAll("-", ""),name3);
							lot3.setState("0");
							Utils.lots.add(lot1);
							Utils.lots.add(lot2);
							Utils.lots.add(lot3);
							broadCastThis(selector,sc,"addauctionDteilsOk");
						}else{
							broadCastThis(selector,sc,"addauctionDteilsNo");
						}
					}else if(standard.equals("@#@getdetails")){
						String auctionid=msgArr[1];
						String result="";
						for(int i=0;i<Utils.lots.size();i++){
							Lot lot=Utils.lots.get(i);

							if(lot.getAuctionId().equals(auctionid)&&lot.getState().equals("0")){
								if(i==Utils.lots.size()){

									result+=lot.getPrijectId()+"_"+lot.getName();
								}else{

									result+=lot.getPrijectId()+"_"+lot.getName()+":";
								}
							}
						}
						broadCastThis(selector,sc,result);
					}else if(standard.equals("@#@getdetailsall")){

						String result="";
						for(int i=0;i<Utils.lots.size();i++){
							Lot lot=Utils.lots.get(i);
							if(lot.getState().equals("0")){
								if(i==Utils.lots.size()){
									result+=lot.getPrijectId()+"_"+lot.getName();
								}else{
									result+=lot.getPrijectId()+"_"+lot.getName()+":";
								}
							}

						}


						broadCastThis(selector,sc,result);
					}else if(standard.equals("@#@bidding")){
						String bankName=msgArr[1];
						String lotId=msgArr[3];
						String money=msgArr[4];
						int count=0;
						List<BankAccount> banks=Utils.bankAccounts;
						for(BankAccount b:banks){
							if(b.getBankName().equals(bankName)){
								count+=1;
								break;
							}
						}
						if(count>0){
							broadCastThis(selector,sc,"notThrough");
						}
						banks=Utils.auctionBankUser;
						for(BankAccount b:banks){
							if(b.getBankName().equals(bankName)){
								count=0;
								break;
							}
						}
						if(count>0){
							broadCastThis(selector,sc,"notApplyingFor");
						}
						if(count==0){
							AuctionRecord record=new AuctionRecord();
							record.setIp(sc.socket().getRemoteSocketAddress().toString());
							record.setMoney(money);
							record.setPrijectId(lotId);
							record.setUserName(bankName);
							record.setState("0");
							Utils.records.add(record);
						}


					}else if(standard.equals("@#@getrecord")){
						String lotId=msgArr[1];
						List<AuctionRecord> reds=Utils.records;
						if(reds.size()<1){
							broadCastThis(selector,sc,"lowhigNo");
						}

						AuctionRecord max=Utils.max(lotId);
						AuctionRecord min=Utils.min(lotId);
						String maxValue="";
						String minValue="";
						if(max!=null){
							maxValue=max.getMoney();
						}else{
							maxValue="0.0";
						}
						if(min!=null){
							minValue=min.getMoney();
						}else{
							minValue="0.0";
						}
						broadCastThis(selector,sc,"resultMaxAndMin:"+maxValue+":"+minValue);

					}
					else if(standard.equals("@#@lotbyid")){
						String lotId=msgArr[1];
						Utils.upbyId(lotId,"1");
					}

					else if(standard.equals("@#@getlotnot")){

						String auctionid=msgArr[2];
						String type=msgArr[1];
						List<Lot> lots=Utils.getLotByType(type,auctionid);
						String result="";
						for(int i=0;i<lots.size();i++){
							Lot lot=Utils.lots.get(i);

							if(lot.getAuctionId().equals(auctionid)){
								if(i==Utils.lots.size()){
									result+=lot.getPrijectId()+"_"+lot.getName()+"_"+lot.getUserName()+"_"+lot.getMoney();
								}else{
									result+=lot.getPrijectId()+"_"+lot.getName()+"_"+lot.getUserName()+"_"+lot.getMoney()+":";
								}
							}
						}
						if("".equals(result)||""==result){
							result="lotokn:";
						}else{
							result="lotok:"+result;
						}
						broadCastThis(selector,sc,result);


					}


					else if(standard.equals("@#@getlotnotbank")){

						String type=msgArr[1];
						String bankkey=msgArr[2];
						String name=msgArr[3];
						List<Lot> lots=Utils.getLotUserByType(type,name);
						String result="";
						for(int i=0;i<lots.size();i++){
							Lot lot=Utils.lots.get(i);
								if(i==Utils.lots.size()){
									result+=lot.getPrijectId()+"_"+lot.getName()+"_"+lot.getUserName()+"_"+lot.getMoney();
								}else{
									result+=lot.getPrijectId()+"_"+lot.getName()+"_"+lot.getUserName()+"_"+lot.getMoney()+":";
								}
						}
						if("".equals(result)||""==result){
							result="lotokn:";
						}else{
							result="lotok:"+result;
						}
						broadCastThis(selector,sc,result);


					}

					else if(standard.equals("@#@getlotok")){
						String auctionid=msgArr[1];
						List<Lot>lots=Utils.getLotByType("2",auctionid);
						String result="";
						for(int i=0;i<lots.size();i++){
							Lot lot=Utils.lots.get(i);

							if(lot.getAuctionId().equals(auctionid)){
								if(i==Utils.lots.size()){
									result+=lot.getPrijectId()+"_"+lot.getName()+"_"+lot.getUserName()+"_"+lot.getMoney();
								}else{
									result+=lot.getPrijectId()+"_"+lot.getName()+"_"+lot.getUserName()+"_"+lot.getMoney()+":";
								}
							}
						}

						broadCastThis(selector,sc,result);

					}

					else if(standard.equals("@#@oklot")){
						String lotid=msgArr[1];
						String bankname=msgArr[2];
						String bankkey=msgArr[3];
						String result="saleNo";
						for(int i=0;i<Utils.lots.size();i++){
							Lot lot=Utils.lots.get(i);
							if(lotid.equals(lot.getPrijectId())){
								String http="@#@httprecorls_"+bankname+"_"+bankkey+"_sale_"+lot.getMoney();
								Main.sc.write(Main.charset.encode(http));
								result="saleOk";
								break;
							}
						}

						Utils.upbyId(lotid,"2");
						broadCastThis(selector,sc,result);

					}

					else if(standard.equals("@#@apply")){


						String getKey=msgArr[1];
						String bankName=msgArr[2];
						String bankKey=msgArr[3];
						List<BankAccount> banks=Utils.bankAccounts;
						int count=0;
						for(BankAccount b:banks){
							if(b.getBankName().equals(bankName)){
								count+=1;
								break;
							}
						}
						banks=Utils.auctionBankUser;
						for(BankAccount b:banks){
							if(b.getBankName().equals(bankName)){
								count+=1;
								break;
							}
						}
						BankAccount ba=new BankAccount(bankName,"",bankKey,"","0",getKey);

						String http="@#@httprecorls_"+bankName+"_"+bankKey+"_lock";
						Main.sc.write(Main.charset.encode(http));
						if(count==0){
							Utils.bankAccounts.add(ba);
							broadCastThis(selector,sc,"applayAcutionOk");
						}else{
							broadCastThis(selector,sc,"applayAcutionNo");

						}

					}
				}

			}
		}
	}

	/**
	 * Notify all clients
	 * @param selector   Selector
	 * @param sc    SocketChannel
	 * @param msg
	 * @throws IOException
	 */
	private void broadCast(Selector selector, SocketChannel sc, String msg)  {
		try {
			for(SelectionKey key : selector.keys()){
				Channel channel = key.channel();
				if (channel instanceof SocketChannel && channel != sc) {
					SocketChannel socketChannel = (SocketChannel) channel;

					socketChannel.write(charset.encode(msg));

				}
			}
		} catch (IOException e) {
			System.out.println("Server exception, please restart!");
			System.exit(1);
		}
	}
	/**
	 * Only notify one of the clients
	 * @param selector  Selector
	 * @param sc    SocketChannel
	 * @param msg
	 * @author
	 * @throws IOException
	 */
	private void broadCastThis(Selector selector, SocketChannel sc, String msg) throws IOException {
		for(SelectionKey key : selector.keys()){
			Channel channel = key.channel();
			if (channel instanceof SocketChannel && channel == sc) {
				SocketChannel socketChannel = (SocketChannel) channel;
				socketChannel.write(charset.encode(msg));
			}
		}

	}









}
