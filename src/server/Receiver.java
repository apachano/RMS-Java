package server;

/**
 * Author austin
 * REV 9/4/2017
 * github.com/apachano
 */
public class Receiver extends networking.Receiver{
    private static String username;
    private static Server server;
    public Receiver() {
       //this.server = server;
    }

    public void receive(String message){
        if (message.startsWith("/register")){
            username = message.substring(7, message.indexOf(" ", 7));
        }
        else{
            System.out.println(message);
        }
    }
}
