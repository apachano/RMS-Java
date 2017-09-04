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
	String number = new String("");	JLabel numDisplay; 
	String MenuSize = new String(""); JLabel sizeDisplay; 
	String cnumber = new String(""); JLabel cnumDisplay;
	
	//Panels that need to be accessed statically 
	static JPanel menuI[] = new JPanel[11];
	static JButton btnMenuSelect[] = new JButton[10];
	static JButton btnMenu[][][] = new JButton[10][10][5];
	static int MenuItemID[][][] = new int[11][11][6];
	JTextArea output;
	static JPanel functions;
	static JPanel menu;
	static JPanel cashout;
	
	//Look and Feel
	Font f1 = new Font("Monospaced", Font.PLAIN, 25);
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
		clockOut.setEditable(false);
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
		
		menu = new JPanel();
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
		menu.add(numDisplay); //TODO Fix number centering
		
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
			menuI[i].setBounds(
			/*X*/(int) Math.round(buttonWidth), 
			/*Y*/440, 
			/*Width*/(int) Math.round(buttonWidth * 10), 
			/*Height*/525);
			menuI[i].setLayout(null);
			menuI[i].setVisible(false);
			menu.add(menuI[i]);			
			
			for(j=0;j<10;j++){
				for(k=0;k<5;k++){
					btnMenu[i][j][k] = new JButton(String.valueOf(i) + "," + String.valueOf(j) + "," + String.valueOf(k));
					btnMenu[i][j][k].setBounds(
					/*X*/(int)Math.round(5 + j * buttonWidth),
					/*Y*/(5 + 105*k), 
					/*Width*/(int) Math.round(buttonWidth - 10), 
					/*Height*/100);
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
			
		/*================================================================
		 * Cash out menu code
		 */
		cashout = new JPanel();
		cashout.setBounds(
				/*X*/		(int)Math.round(width/4), 
				/*y*/		25, 
				/*width*/	(int)Math.round((13 * buttonWidth)-5), 
				/*height*/	(int)Math.round(height - 35));
		cashout.setBackground(new Color(220,200,200));
		contentPane.add(cashout);
		cashout.setVisible(false);
		cashout.setLayout(null);
		
		/*
		 * Numbers Code
		 */
		
		cnumDisplay = new JLabel("");
		cnumDisplay.setBounds(
				/*X*/(int) (5 + buttonWidth), 
				/*Y*/220, 
				/*Width*/330, 
				/*Height*/100);
		cnumDisplay.setBackground(new Color(100,100,100));
		cnumDisplay.setFont(f2);
		cnumDisplay.setHorizontalTextPosition(SwingConstants.CENTER);
		cashout.add(cnumDisplay); //TODO Fix number centering
		
		JButton cnum[] = new JButton[11];
		for(i=0;i<3;i++){
			for(j=0;j<3;j++){
				cnum[(1+i)*(1+j)] = new JButton(String.valueOf((1+i)*(1+j)));
				cnum[(1+i)*(1+j)].setBounds((int)Math.round(
				/*X*/		5 + (i+1) * buttonWidth),
				/*Y*/		220 + (j+1) * 100, 
				/*width*/	(int) Math.round(buttonWidth - 10), 
				/*height*/	90);
				cnum[(1+i)*(1+j)].setFont(f2);
				cashout.add(cnum[(1+i)*(1+j)]);
				cnum[(1+i)*(1+j)].addActionListener(new ActionListener() {
					int select = (1+i)*(1+j);
					public void actionPerformed(ActionEvent e) {
						if(cnumber.length() < 10){
							cnumber = cnumber + select;
							Update();
						}
					}
				});
			}
		}
		cnum[0] = new JButton(String.valueOf(0));
		cnum[0].setBounds((int)Math.round(
		/*X*/		5 + 2 * buttonWidth),
		/*Y*/		620, 
		/*width*/	(int) Math.round(buttonWidth - 10), 
		/*height*/	90);
		cnum[0].setFont(f2);
		cashout.add(cnum[0]);
		cnum[0].addActionListener(new ActionListener() {
			int select = 0;
			public void actionPerformed(ActionEvent e) {
				if(cnumber.length() < 10){
					cnumber = cnumber + select;
					Update();
				}
			}
		});
		cnum[10] = new JButton(String.valueOf("00"));
		cnum[10].setBounds((int)Math.round(
		/*X*/		5 + 3 * buttonWidth),
		/*Y*/		620, 
		/*width*/	(int) Math.round(buttonWidth - 10), 
		/*height*/	90);
		cnum[10].setFont(f2);
		cashout.add(cnum[10]);
		cnum[10].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cnumber.length() < 10){
					cnumber = cnumber + "00";
					Update();
				}
			}
		});
		JButton cnumClear = new JButton("clear");
		cnumClear.setBounds(
				/*X*/(int)Math.round(5 + buttonWidth), 
				/*Y*/620, 
				/*Width*/(int)Math.round(buttonWidth - 10), 
				/*Height*/90);
		cashout.add(cnumClear);
		cnumClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cnumber = "";
				Update();
			}
		});
		JButton modifyOrder = new JButton("Modify \n Order");
		modifyOrder.setBounds((int) (5+5*buttonWidth), 220, (int)buttonWidth - 10, 90);
		cashout.add(modifyOrder);
		modifyOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				cashoutToggle();
			}
		});
		
		/*================================================================
		 * Functions menu code
		 */
		
		functions = new JPanel();
		functions.setBounds(510, 25, 1300, 1050);
		functions.setBackground(new Color(220,220,220));
		contentPane.add(functions);
		functions.setVisible(false);
		functions.setLayout(null);
		
		JButton btnOrderNew = new JButton("New Order");
		btnOrderNew.setBounds(1190, 620, 100, 100);
		functions.add(btnOrderNew);
		btnOrderNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pos.newOrder();
				Update();
				functionMenuToggle();
			}
		});
		
		JButton btnMenuLoad = new JButton("Load Menu");
		btnMenuLoad.setBounds(1190, 730, 100, 100);
		functions.add(btnMenuLoad);
		btnMenuLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadMenu();
				functionMenuToggle();
			}
		});

		JButton btnExitFunctions = new JButton("Exit");
		btnExitFunctions.setBounds(1190, 840, 100, 100);
		functions.add(btnExitFunctions);
		btnExitFunctions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				functions.setVisible(false);
				menu.setVisible(true);
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
		
		/*================================================================
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
				functionMenuToggle();
			}
		});
		
		JButton btnCashOut = new JButton("CashOut");
		btnCashOut.setBounds(1815, 740, 100, 100);
		contentPane.add(btnCashOut);
		btnCashOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				cashoutToggle();
			}
		});
		
		loadMenu();
		Update();
	}
	
	/*========================================================================================================
	 * Functions
	 */
	private static void functionMenuToggle(){
		if(functions.isVisible()){
		functions.setVisible(false);
		menu.setVisible(true);
		}else{
			functions.setVisible(true);
			menu.setVisible(false);
		}
		
	}
	private static void cashoutToggle(){
		if(cashout.isVisible()){
		cashout.setVisible(false);
		menu.setVisible(true);
		}else{
			cashout.setVisible(true);
			menu.setVisible(false);
		}
	}
	
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
		while(cnumber.startsWith("0")){cnumber = cnumber.substring(1);}
		int temp = cnumber.length();
		if(temp > 2)cnumDisplay.setText("$" + cnumber.substring(0, temp - 2) + "." + cnumber.substring(temp - 2));
		else cnumDisplay.setText("$0." + cnumber);
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
