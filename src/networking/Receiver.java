package networking;

/**
 * Author austin
 * REV 9/10/2017
 * github.com/apachano
 */
public class Receiver extends Thread{
    public Connection connection;
    public Receiver(){

    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public void receive(String message){
        System.out.println("message received: " + message);
    }
}