/***************************************************
 * Group member: Rongbing Xu, Shouyu Yang,
 * Xiao Liang, Haisen Li.
 * This Class is server.
 **************************************************/
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import application.Utils;
import application.share.entity.BankAccount;
import application.share.entity.UserRequest;


/**
 * bankserver
 *
 */
public class Server extends Thread{

	private Selector selector;

	private final static int PORT=6666;

	private HashSet<String> online = new HashSet<String>();

	private Charset charset =  Charset.forName("UTF-8");

	private Map<String,SocketChannel> objects=new HashMap<>();

	private Map<String,String> ipobjects=new HashMap<>();

	/**
	 *initialize server
	 * @author
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

			System.out.println("The bank server is waiting for the client connection... ");

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
			System.out.println("The initialization of server failed, please restart the server!");
			System.exit(1);
		}
	}


	/**
	 *  Processing select key
	 * @param server ServerSocketChannel
	 * @param key SelectionKey
	 * @author
	 * @throws IOException
	 */
	private void dealWithSelectionKey(ServerSocketChannel server, SelectionKey key) throws IOException {
		if (key.isAcceptable()) {

			SocketChannel sChannel = server.accept();

			sChannel.configureBlocking(false);

			 sChannel.register(selector, SelectionKey.OP_READ);

			 key.interestOps(SelectionKey.OP_ACCEPT);

			System.out.println("The client connection is successful and the server is listening to the client :" + sChannel.socket().getRemoteSocketAddress());

			sChannel.write(charset.encode("The client connect to bank successfully"));
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
				System.out.println("The savings user of this IP address:"+ip+", has been Disconnected.");
				String name=ipobjects.get(ip);

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
					if(standard.equals("@#@addBank")){
						String bankName=msgArr[1];
						String bankAccount=msgArr[2];
						String bankKey=msgArr[3];
						String money=msgArr[4];
						BankAccount bank=new BankAccount(bankName,bankAccount,bankKey,money,"","");
						bank.setBankName(bankName);
						bank.setBankAccount(bankAccount);
						bank.setBankKey(bankKey);
						bank.setMoney(money);
						int count =0;
						for(BankAccount b:Utils.bankAccounts){
							if(b.getBankAccount().equals(bankAccount)){
								count+=1;
								break;
							}
							if(b.getBankName().equals(bankName)){
								count+=1;
								break;
							}
						}
						if(count==0){
							Utils.bankAccounts.add(bank);
							sc.write(charset.encode("bankUserOk"));
						}else{
							Utils.bankAccounts.add(bank);
							sc.write(charset.encode("bankUserNotOk"));
						}
					}else if(standard.equals("@#@getBank")){
						String bankName=msgArr[1];
						String bankAccount=msgArr[2];
						BankAccount bbj=null;
						for(BankAccount b:Utils.bankAccounts){
							if(bankName.equals(b.getBankName())&&bankAccount.equals(b.getBankAccount())){
								bbj=b;
								break;
							}
						}
						if(bbj!=null){
							sc.write(charset.encode("$genbankOk_"+bbj.getBankKey()));
						}else{
							sc.write(charset.encode("$genbankNo_98&*"));
						}
					}else if(standard.equals("@#@httprecorls")){
						String bankName=msgArr[1];
						String bankKey=msgArr[2];
						BankAccount bbj=null;

						 String type=msgArr[3];
						for(int i=0;i<Utils.bankAccounts.size();i++){
							BankAccount b=Utils.bankAccounts.get(i);
							if(bankName.equals(b.getBankName())&&bankKey.equals(b.getBankKey())){
								bbj=b;
								 if(type.equals("lock")){
									 type="Account locked";
								 }
								 if(type.equals("sale")){
									 type="Transaction deductions";
									 double  yu=Double.valueOf(bbj.getMoney());
									 double  jian=Double.valueOf(msgArr[4]);
									 bbj.setMoney(String.valueOf((yu-jian)));
								 }
								Utils.bankAccounts.set(i, bbj);
								break;
							}
						}
						 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


						 String ip=sc.socket().getRemoteSocketAddress().toString();
						UserRequest u=new UserRequest(bbj.getBankName(), bbj.getBankKey(), df.format(new Date()), type, ip, msgArr[3]);
						Utils.resusts.add(u);

					}




				}

			}
		}
	}





}
