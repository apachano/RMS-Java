package server;
import java.io.IOException;
import java.net.InetAddress;
import networking.Connection;

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
		
		(new ConnectionManager(connection, ss)).start(); //Start the connection manager with the server socket
		
		//Start scanning for input
		Scanner scanner = new Scanner(System.in);
		String buffer;
		while(scanner.hasNextLine()){
			buffer = scanner.nextLine();
			if (buffer.startsWith("/")){
				command(buffer);
			}
			else{
				System.out.println("Type /help for help");
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
		//ConnectionManager.active = false;
	}
	public static void command(String command){

	}
}
