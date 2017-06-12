package pos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PosGui extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//TODO figure out what this means serial version UID
	private JPanel contentPane;
	static int i;
	int j;
	int m;
	int n;
	String number = new String("");
	JLabel numDisplay;
	static JPanel menuI[] = new JPanel[11];
	JTextArea output;
	static JButton btnMenuSelect[] = new JButton[10];
	static JButton btnMenu[][][] = new JButton[10][10][5];
	
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
		
		//Dynamic sizing values
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		double buttonWidth = (.75 * width)/13;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//Output panel for displaying orders
		output = new JTextArea();
		output.setBounds(5, 25, (int)Math.round((width * .25) -10), (int)Math.round(height - 35));
		output.setEditable(false);
		contentPane.add(output);
		
		/*
		 * Menu buttons code
		 */
		
		JPanel menu = new JPanel();
		menu.setBounds(
		/*X*/		(int)Math.round(width/4), 
		/*y*/		25, 
		/*width*/	(int)Math.round(12 * buttonWidth), 
		/*height*/	(int)Math.round(height - 35));
		menu.setBackground(new Color(200,200,200));
		contentPane.add(menu);
		menu.setVisible(true);
		menu.setLayout(null);

			/*
			 * Numbers Code
			 */
			numDisplay = new JLabel("");
			numDisplay.setBounds(5, 220, 100, 100);
			menu.add(numDisplay);
			
			JButton num[] = new JButton[10];
			for(n=0;n<10;n++){
				num[n] = new JButton(String.valueOf(n));
				num[n].setBounds((int)Math.round(5 + (n+1) * buttonWidth),220, (int) Math.round(buttonWidth - 10), 100);
				menu.add(num[n]);
				num[n].addActionListener(new ActionListener() {
					int select = n;
					public void actionPerformed(ActionEvent e) {
						number = number + select;
						numUpdate();
					}
				});
			}
			JButton numClear = new JButton("clear");
			numClear.setBounds((int)Math.round(5 + buttonWidth * 11), 220, (int)Math.round(buttonWidth - 10), 100);
			menu.add(numClear);
			numClear.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					number = "";
					numUpdate();
				}
			});
			
			/*
			 * Menu Button Code
			 */
			
			for(i=0;i<10;i++){
				btnMenuSelect[i] = new JButton("menu" + String.valueOf(i));
				btnMenuSelect[i].setBounds((int)Math.round(5 + (i+1) * buttonWidth),330, (int) Math.round(buttonWidth - 10), 100);
				menu.add(btnMenuSelect[i]);
				btnMenuSelect[i].addActionListener(new ActionListener() {
					int select = i;
					public void actionPerformed(ActionEvent e) {
						switchMenu(select);
					}
				});
				
			}
		
		JButton btnSizeLarge = new JButton("Large");
		btnSizeLarge.setBounds(5,440,(int)Math.round(buttonWidth - 10), 100);
		menu.add(btnSizeLarge);
			
		for(i=0;i<10;i++){
			menuI[i] = new JPanel();
			menuI[i].setBounds((int) Math.round(buttonWidth), 440, (int) Math.round(buttonWidth * 10), 525);
			menuI[i].setLayout(null);
			menuI[i].setVisible(false);
			menu.add(menuI[i]);			
			
			for(n=0;n<10;n++){
				for(m=0;m<5;m++){
					btnMenu[i][n][m] = new JButton(String.valueOf(i) + "," + String.valueOf(n) + "," + String.valueOf(m));
					btnMenu[i][n][m].setBounds((int)Math.round(5 + n * buttonWidth),(5 + 105*m), (int) Math.round(buttonWidth - 10), 100);
					menuI[i].add(btnMenu[i][n][m]);
					btnMenu[i][n][m].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
						}
					});
				}
			}
		}
		menuI[1].setVisible(true);
				
		
		/*
		 * Functions menu code
		 */
		JPanel functions = new JPanel();
		functions.setBounds(510, 25, 1300, 1050);
		functions.setBackground(new Color(220,220,220));
		contentPane.add(functions);
		functions.setVisible(false);
		functions.setLayout(null);

		JButton btnExitFunctions = new JButton("Exit");
		btnExitFunctions.setBounds(1190, 840, 100, 100);
		functions.add(btnExitFunctions);

		btnExitFunctions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				functions.setVisible(false);
				menu.setVisible(true);
			}
		});
		
		JButton btnMenuLoad = new JButton("Load Menu");
		btnMenuLoad.setBounds(1190, 730, 100, 100);
		functions.add(btnMenuLoad);

		btnMenuLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadMenu();
			}
		});

		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(1190, 950, 100, 100);
		functions.add(btnQuit);
		
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		/*
		 * Other Code
		 */
		
		JLabel ks = new JLabel();
		ks.setBounds(5,5,100,20);
		contentPane.add(ks);
		
		JButton btnFunctions = new JButton("Functions");
		btnFunctions.setBounds(1815, 850, 100, 100);
		contentPane.add(btnFunctions);

		btnFunctions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(functions.isVisible()){
				functions.setVisible(false);
				menu.setVisible(true);
				}else{
					functions.setVisible(true);
					menu.setVisible(false);
				}
			}
		});
		
		loadMenu();
	}
	
	/*========================================================================================================
	 * Functions
	 */

	protected void switchMenu(int select) {
		for(i=0;i<10;i++){
			menuI[i].setVisible(false);
		}
		menuI[select].setVisible(true);
		
	}

	private void numUpdate() {
		//TODO create code to remove leading "0" from number
		numDisplay.setText(number);
	}
	public static void loadMenu(){
		Pos.loadMenu();
		try {
			updateMenu(new File("database/pos/menu.dat"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	static Scanner file;
	static String buffer;
	static int a, active, id, b, c;
	static menu.Item temp;
	public static void updateMenu(File fp) throws FileNotFoundException {
		// TODO Auto-generated method stub
		file = new Scanner(fp);
		buffer = file.nextLine();
		for(i=0; i<10;i++){
			btnMenuSelect[i].setText(buffer);
			buffer = file.nextLine();
		}
		while(! buffer.equals("terminate")){
			a = Integer.parseInt(buffer.substring(0,1));
			b = Integer.parseInt(buffer.substring(2,3));
			c = Integer.parseInt(buffer.substring(4,5));
			active = Integer.parseInt(buffer.substring(6,7));
			id = Integer.parseInt(buffer.substring(8,14));
			System.out.print(id);
			temp = Pos.menu.getItem(id);
			System.out.println(temp);
			btnMenu[a][b][c].setText(temp.getName());
			buffer = file.nextLine();
		}
		
	}
	
}
