package networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Connection extends Thread{
	OutputStream os;
	InputStream is;
	static Socket socket;
	static Scanner scan;
	
	public Connection(Socket sock) throws IOException{
		socket = sock;
		os = sock.getOutputStream();
		is = sock.getInputStream();
	}
	
	public void run(){
		scan = new Scanner(is);
		while(scan.hasNextLine()){
			Connection.read(scan.nextLine());
		}
		close();
	}
	
	public static void read(String input){
		System.out.println(input);
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
