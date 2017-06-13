package pos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.SwingConstants;

import order.Order;

public class PosGui extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//TODO figure out what this means serial version UID
	private JPanel contentPane;
	//Integers for counting
	static int i, j, k;
	static int a, b, c;
	
	//Strings and displays for numbers and sized
	String number = new String(""); String MenuSize = new String("");
	JLabel numDisplay; JLabel sizeDisplay;
	
	//Panels that need to be accessed statically 
	static JPanel menuI[] = new JPanel[11];
	static JButton btnMenuSelect[] = new JButton[10];
	static JButton btnMenu[][][] = new JButton[10][10][5];
	static int MenuItemID[][][] = new int[11][11][6];
	JTextArea output;
	
	//Look and Feel
	Font f1 = new Font("Monospaced", Font.PLAIN, 30);
	Font f2 = new Font("serif", Font.PLAIN, 40);
	static Color buttonEnabled = new Color(200, 255, 200);
	static Color buttonDisabled = new Color(255, 200, 200);
	
	
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
		
		//Output for clock
		
		JTextArea clockOut = new JTextArea();
		clockOut.setBounds(5, 5, 150, 20);
		clockOut.setVisible(true);
		contentPane.add(clockOut);
		clockOut.setText("00/00/0000 00:00");
		//TODO Create code to display a clock
		//(new ClockUpdater()).start();
		
		//Output panel for displaying orders
		output = new JTextArea();
		output.setBounds(5, 25, (int)Math.round((width * .25) -10), (int)Math.round(height - 35));
		output.setEditable(false);
		output.setFont(f1);
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
		numDisplay.setFont(f2);
		numDisplay.setHorizontalTextPosition(SwingConstants.CENTER);
		menu.add(numDisplay);
		
		JButton num[] = new JButton[10];
		for(i=0;i<10;i++){
			num[i] = new JButton(String.valueOf(i));
			num[i].setBounds((int)Math.round(
			/*X*/		5 + (i+1) * buttonWidth),
			/*Y*/		220, 
			/*width*/	(int) Math.round(buttonWidth - 10), 
			/*height*/	100);
			num[i].setFont(f2);
			menu.add(num[i]);
			num[i].addActionListener(new ActionListener() {
				int select = i;
				public void actionPerformed(ActionEvent e) {
					if(number.length() < 3){
						number = number + select;
						Update();
					}
				}
			});
		}
		JButton numClear = new JButton("clear");
		numClear.setBounds((int)Math.round(5 + buttonWidth * 11), 220, (int)Math.round(buttonWidth - 10), 100);
		menu.add(numClear);
		numClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				number = "";
				Update();
			}
		});
		
		/*
		 * Size Button Code
		 */
		
		sizeDisplay = new JLabel("");
		sizeDisplay.setBounds(5, 330, 100, 100);
		sizeDisplay.setFont(f2);
		sizeDisplay.setHorizontalTextPosition(SwingConstants.CENTER);
		menu.add(sizeDisplay);
		
		JButton btnSizeXLarge = new JButton("XLarge");
		btnSizeXLarge.setBounds(5,445,(int)Math.round(buttonWidth - 10), 100);
		menu.add(btnSizeXLarge);
		btnSizeXLarge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MenuSize.equals("XL")) MenuSize = "";
				else MenuSize = "XL";
				sizeDisplay.setText(MenuSize);
			}
		});

		JButton btnSizeLarge = new JButton("Large");
		btnSizeLarge.setBounds(5,550,(int)Math.round(buttonWidth - 10), 100);
		menu.add(btnSizeLarge);
		btnSizeLarge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MenuSize.equals("L")) MenuSize = "";
				else MenuSize = "L";
				sizeDisplay.setText(MenuSize);
			}
		});
		
		JButton btnSizeMedium = new JButton("Medium");
		btnSizeMedium.setBounds(5,655,(int)Math.round(buttonWidth - 10), 100);
		menu.add(btnSizeMedium);
		btnSizeMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MenuSize.equals("M")) MenuSize = "";
				else MenuSize = "M";
				sizeDisplay.setText(MenuSize);
			}
		});
		
		JButton btnSizeSmall = new JButton("Small");
		btnSizeSmall.setBounds(5,760,(int)Math.round(buttonWidth - 10), 100);
		menu.add(btnSizeSmall);
		btnSizeSmall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MenuSize.equals("S")) MenuSize = "";
				else MenuSize = "S";
				sizeDisplay.setText(MenuSize);
			}
		});

		JButton btnSizeXSmall = new JButton("XSmall");
		btnSizeXSmall.setBounds(5,865,(int)Math.round(buttonWidth - 10), 100);
		menu.add(btnSizeXSmall);
		btnSizeXSmall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MenuSize.equals("XS")) MenuSize = "";
				else MenuSize = "XS";
				sizeDisplay.setText(MenuSize);
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
		
		for(i=0;i<10;i++){
			menuI[i] = new JPanel();
			menuI[i].setBounds((int) Math.round(buttonWidth), 440, (int) Math.round(buttonWidth * 10), 525);
			menuI[i].setLayout(null);
			menuI[i].setVisible(false);
			menu.add(menuI[i]);			
			
			for(j=0;j<10;j++){
				for(k=0;k<5;k++){
					btnMenu[i][j][k] = new JButton(String.valueOf(i) + "," + String.valueOf(j) + "," + String.valueOf(k));
					btnMenu[i][j][k].setBounds((int)Math.round(5 + j * buttonWidth),(5 + 105*k), (int) Math.round(buttonWidth - 10), 100);
					menuI[i].add(btnMenu[i][j][k]);
					btnMenu[i][j][k].setBackground(buttonDisabled);
					//btnMenu[i][j][k].set
					btnMenu[i][j][k].addActionListener(new ActionListener() {
						
						int x = i;
						int y = j;
						int z = k;
						public void actionPerformed(ActionEvent e) {
							Pos.add(MenuItemID[x][y][z], getNumber());
							number = "";
							Update();
						}
						private int getNumber() {
							if(number.equals(""))return -1;
							return Integer.valueOf(number);
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
		Update();
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
	/**
	 * Updates the text displays on the screen
	 */
	private void Update() {
		if(number.length() > 1)if(number.startsWith("0"))number = number.substring(1);
		numDisplay.setText(number);
		sizeDisplay.setText(MenuSize);
		if(Pos.order != null)output.setText(Order.getOrder());
		else output.setText("There is no order started");
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
	static int active, id;
	static menu.Item temp;
	public static void updateMenu(File fp) throws FileNotFoundException {
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
			temp = Pos.menu.getItem(id);
			btnMenu[a][b][c].setText(temp.getName());
			if(active == 1)btnMenu[a][b][c].setBackground(buttonEnabled);
			else btnMenu[a][b][c].setBackground(buttonDisabled);
			MenuItemID[a][b][c] = id;
			buffer = file.nextLine();
		}
		
	}
	
}
