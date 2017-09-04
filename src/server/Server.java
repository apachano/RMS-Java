package server;
import java.io.IOException;
import java.net.InetAddress;
import networking.Connection;
import networking.ConnectionManager;

import java.net.ServerSocket;
import java.util.Scanner;

public class Server extends Thread{
	static networking.Connection[] connection = new Connection[20];
	static int i;
	static ServerSocket ss;
	
	public static void main(String[] args){
		ss = null;
		try { // Create a new server socket
			ss = new ServerSocket(1201);
			System.out.println("Inet address=" + InetAddress.getLocalHost());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		(new networking.ConnectionManager(connection, ss)).start(); //Start the connection manager with the server socket
		
		//Start scanning for input
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()){
			for(i=0;i<10;i++){
				if(connection[i] != null){
					connection[i].push(scanner.nextLine());
				}
			}
		}
		
		//Close all resource leaks?
		scanner.close();
		try {
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.active = false;
	}
}
