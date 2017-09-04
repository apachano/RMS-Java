package networking;

import java.net.Socket;

public class Client {
	public static Connection connect(String ip) throws Exception {
		
		//Create a new socket
		Socket sock = new Socket(ip, 1201);

		//Pass that socket to create a new connection
		Connection connection = new Connection(sock);
		connection.start();
		
		System.out.println("Connection made to <" + ip + ">");
		return connection;
	}
}
