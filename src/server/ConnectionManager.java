package server;

import java.io.IOException;
import networking.Connection;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionManager extends Thread{
	static boolean active;
	static int i;
	Connection[] connection;
	ServerSocket ss;
	
	public ConnectionManager(Connection[] connection, ServerSocket ss){
		this.connection = connection;
		this.ss = ss;
	}
	public void run(){
		System.out.println("Waiting for connection");
		active = true;
		while(active){
			Socket sock = null;
			try {
				sock = ss.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Recieved connection request from" + sock);
			try {
				for(i=0; connection[i] != null; i++){}
				connection[i] = new Connection(sock, new Receiver("connection " + i + ": " ));
				connection[i].start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
