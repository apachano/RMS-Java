package networking;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class Reader extends Thread{
	Socket socket;
	Reader(Socket sock){
		socket = sock;
	}
	
	public void run() {
		InputStream is = null;
		try {
			is = socket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner scan = new Scanner(is);
		while(scan.hasNextLine()){
			System.out.println(scan.nextLine());
		}
		scan.close();
	}

}
