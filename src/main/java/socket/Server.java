package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * socket: encapsulated for TCP
 * @author zxh
 *
 */
public class Server {
	private ServerSocket serverSocket;
	// store all outputStreams(channel) in array for broadcasting
//	private PrintWriter[] allOut={};
	private Collection<PrintWriter> allOut=new ArrayList<PrintWriter>();
	public Server() {
		try {
			// apply for a port number
			serverSocket=new ServerSocket(8088);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		try {
			// listen to port, wait until client is connected, return socket
			while (true) {
				Socket socket=serverSocket.accept();
				// start a new thread for each client
				ClientHandler handler=new ClientHandler(socket);
				Thread thread=new Thread(handler);
				thread.start();
				System.out.println(handler.host+" is connected");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Server server=new Server();
		server.start();
	}
	
	private class ClientHandler implements Runnable{
		private Socket socket;
		private String host; 
		
		// pass socket from outside and read message from client
		public ClientHandler(Socket socket) {
			this.socket=socket;
			// get client's IP address
			InetAddress address =socket.getInetAddress();
			host=address.getHostAddress();
		}		
		public void run() {
			PrintWriter pw=null;	
			try {
				// receive message
				InputStream is=socket.getInputStream();	
				BufferedReader br=new BufferedReader(
						new InputStreamReader(is,"UTF-8"));
				
				// response to client
				OutputStream out=socket.getOutputStream();
				pw=new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(out,"UTF-8")),true);
				
				// grow allout and store pw at the end, run one client each time
				synchronized (allOut) {
//					allOut=Arrays.copyOf(allOut, allOut.length+1);
//					allOut[allOut.length-1]=pw;			
					allOut.add(pw);
				}

				// broadcast message
				String data=null;
				while((data=br.readLine())!=null) {
					System.out.println(host+":"+data);
					// traverse allOut, exclude other blocks
					synchronized (allOut) {
						for (PrintWriter po : allOut) {
							po.println(host+":"+data);						
						}
					}
				}
				
			} catch (Exception e) {
				e.getStackTrace();
				
			}finally {
				// remove pw from allOut, exclude other blocks and thread
				synchronized (allOut) {
//					for (int i = 0; i < allOut.length; i++) {
//						if(allOut[i].equals(pw)) {
//							allOut[i]=allOut[allOut.length-1];
//							allOut=Arrays.copyOf(allOut, allOut.length-1);
//							break;
//						}
//					}
					allOut.remove(pw);
				}
							
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
