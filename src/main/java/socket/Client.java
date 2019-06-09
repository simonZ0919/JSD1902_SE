package socket;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	// socket: API for TCP
	private Socket socket;
	public Client() {
		try {
			 // Socket(server IP, port number)
			 //  start connection, throw exception if no response
			socket=new Socket("localhost",8088);//localhost: local IP
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	public void start() {
		try {
			// create a thread to read server message
			Thread thread=new Thread(new ServerHandler());
			thread.start();
			Scanner scanner=new Scanner(System.in);
			// send to server through OutputStream
			OutputStream out= socket.getOutputStream();
			// connect stream to printWriter, true: flush each line
			PrintWriter pw=new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(out,"UTF-8")),true);
			while (true) {
				pw.println(scanner.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Client client=new Client();
		client.start();
	}
	
	private class ServerHandler implements Runnable{
		public void run() {
			try {
				// get response from client
				InputStream is=socket.getInputStream();
				BufferedReader br=new BufferedReader(
						new InputStreamReader(is,"UTF-8"));
				String line=null;
				while((line=br.readLine())!=null)
					System.out.println(line);
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}
}
