package posGui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PosGui extends JFrame{
	private JPanel contentPane;
	
	
	
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PosGui frame = new PosGui();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	
	public PosGui(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea order = new JTextArea();
		order.setBounds(5, 25, 500, 1050);
		contentPane.add(order);
		
		JLabel ks = new JLabel();
		ks.setBounds(5,5,100,20);
		contentPane.add(ks);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(1815, 975, 100, 100);
		contentPane.add(btnQuit);
		
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
