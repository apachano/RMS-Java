package server;

/**
 * Author austin
 * REV 9/4/2017
 * github.com/apachano
 */
public class Receiver extends networking.Receiver{
    private static String username;
    private static Server server;
    private static String id;
    public Receiver(String identifier) {
        id = identifier;
    }

    public void receive(String message){
        if (message.startsWith("register")){
            connection.push("register 1");
            System.out.println("Register 1 registered");
        }
        else{
            System.out.println(id + message);
        }
    }
}
