package pos;

import networking.Connection;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Author austin
 * REV 9/11/2017
 * github.com/apachano
 */
public class Register {
    static Connection connection;
    Receiver receiver;
    static Scanner scanner = null;
    static String buffer;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PosGui frame = new PosGui();
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setUndecorated(true);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        connect();
        System.out.println("Connection made");
        connection.push("register");
    }

    private static Connection connect() {

        Socket sock = null;

        //Create a new socket
        try {
            sock = new Socket("localhost", 1201);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Pass that socket to create a new connection
        try {
            connection = new Connection(sock, new Receiver());
            connection.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Connection made to <" + buffer + ">");
        return connection;
    }
}