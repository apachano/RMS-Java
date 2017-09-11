package networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Connection extends Thread{
	private OutputStream os;
	private InputStream is;
	private static Socket socket;
	private static Scanner scan;
	private static Receiver receiver;
	
	public Connection(Socket sock, Receiver receiver) throws IOException{
		socket = sock;
		os = sock.getOutputStream();
		is = sock.getInputStream();
		this.receiver = receiver;
	}
	
	public void run(){
		scan = new Scanner(is);
		while(scan.hasNextLine()){
			receiver.receive(scan.nextLine());
		}
		close();
	}
	
	public void push(String output){
		PrintWriter pw = new PrintWriter(os);
		pw.println(output);
		pw.flush();
	}

	public void close() {
		scan.close();
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
