package networking;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server{
	List<Connection> connections;
	Server(){
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(1201);
			System.out.println("Inet address=" + InetAddress.getLocalHost());
			
			//(new Writer(sock)).start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		while(true){
			System.out.println("Waiting for connection");
			Socket sock = null;
			try {
				sock = ss.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Recieved connection request from" + sock);
			(new Reader(sock)).start();
		}
	}
	public static void main(String[] args){
		Server server = new Server();
	}
	
}
