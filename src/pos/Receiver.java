package pos;

/**
 * Author austin
 * REV 9/10/2017
 * github.com/apachano
 */
public class Receiver extends Thread{
    public Receiver(){

    }
    public void receive(String message){
        System.out.println("message received: " + message);
    }
}