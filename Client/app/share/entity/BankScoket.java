package application.share.entity;

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

import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * Establish a connection with a bank server
 *
 */
public class BankScoket {
	private  Selector selector = null;
	public static String msg=null;
	static final int port = 6666;
	private Charset charset = Charset.forName("UTF-8");
	private SocketChannel sc = null;
	private String name = "";
	private static String USER_EXIST = "system message: user exist, please change a name";
	/**
	 *Link auction center server
	 * @return SocketChannel
	 * @throws IOException
	 */
	public SocketChannel init() throws IOException  {
		selector = Selector.open();

		sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", port));

		sc.configureBlocking(false);
		sc.register(selector, SelectionKey.OP_READ);

		new Thread(new ClientThread()).start();
		return sc;

	}
	/**
	 * Internal class, implementation of thread interface
	 *
	 */
	private class ClientThread   implements Runnable{
		public void run() {
			try {
				while (true) {
					int readyChannels = selector.select();
					if (readyChannels == 0)
						continue;
					Set selectedKeys = selector.selectedKeys();
					Iterator keyIterator = selectedKeys.iterator();
					while (keyIterator.hasNext()) {
						SelectionKey sk = (SelectionKey) keyIterator.next();
						keyIterator.remove();
						dealWithSelectionKey(sk);
					}
				}
			} catch (IOException io) {

				System.out.print("Link exception, the program is about to close, please restart");
				System.exit(1);
			}
		}
		/**
		 *Monitor the server to return the message
		 * @param sk SelectionKey
		 * @throws IOException
		 */
		private void dealWithSelectionKey(SelectionKey sk) {
			try {
				if (sk.isReadable()) {
					SocketChannel sc = (SocketChannel) sk.channel();
					ByteBuffer buff = ByteBuffer.allocate(1024);
					String content = "";
					while (sc.read(buff) > 0) {
						buff.flip();
						content += charset.decode(buff);
					}

					if (USER_EXIST.equals(content)) {
						name = "";
					}
					sk.interestOps(SelectionKey.OP_READ);
					BankScoket.msg=content;

				}
			} catch (IOException e) {
				System.out.print("Link exception, the program is about to close, please restart");

			System.exit(1);
			}
		}


	}


}