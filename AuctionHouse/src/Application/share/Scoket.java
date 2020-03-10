package application.share;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;

import application.LotListController;
import application.Main;
import application.MyController;
import application.share.entity.Auction;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Scoket linked with the auction center
 *
 */
public class Scoket {
	private  Selector selector = null;
	// A selector could manage on several SocketChannels
	static final int port = 2333;
	public static String msg="";
	private Charset charset = Charset.forName("UTF-8");
	private SocketChannel sc = null;
	private String name = "";
	private static String USER_EXIST = "system message: user exist, please change a name";

	/**
	 *Initialization
	 * @return SocketChannel
	 * @throws IOException
	 */
	public SocketChannel init() throws IOException  {
		selector = Selector.open();
		sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", port));
		//
		sc.configureBlocking(false);
		sc.register(selector, SelectionKey.OP_READ);
		// register the sc object to the selector and read

		new Thread(new ClientThread()).start();
		// open a new client thread and start
		return sc;
	}
	/**
	 * Internal class, implementation of thread interface
	 * @author
	 *
	 */
	private class ClientThread   implements Runnable{
		public void run() {
			try {
				while (true) {
					System.out.println("Run a new client Thread!");
					int readyChannels = selector.select();
					// select new channel
					if (readyChannels == 0)
						continue;
					Set selectedKeys = selector.selectedKeys();
					// a set of all the selected Keys
					Iterator keyIterator = selectedKeys.iterator();
					while (keyIterator.hasNext()) {
						SelectionKey sk = (SelectionKey) keyIterator.next();
						keyIterator.remove();
						dealWithSelectionKey(sk);
					}
				}
			} catch (IOException io) {
				System.out.println("Client initialization failed, please restart!");
				System.exit(1);
			}
		}
		/**
		 * Listening to server messages
		 * @param sk SelectionKey
		 * @throws IOException
		 */
		private void dealWithSelectionKey(SelectionKey sk) {
			// waiting for server messages using the sk
			try {
				if (sk.isReadable()) {
					// select the channel in sk and open a new byte buffer
					SocketChannel sc = (SocketChannel) sk.channel();
					ByteBuffer buff = ByteBuffer.allocate(1024);
					String content = "";
					System.out.println("Add content to the bytebuffer selected by sk.");
					while (sc.read(buff) > 0) {
						buff.flip();
						content += charset.decode(buff);
					} // write inside buffer and change to read then read from buffer.

					if (USER_EXIST.equals(content)) {
						name = "";
					}
					sk.interestOps(SelectionKey.OP_READ);
					Utils.msg=content;

				}
			} catch (IOException e) {
				System.out.println("The client reads the message exception to be restarted");
				System.exit(1);
			}
		}


	}


}