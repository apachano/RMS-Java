package menuManager;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class GUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	JPanel filePaths;
	JButton btnFiles;
	JMenuBar menuBar;
	JMenuItem menuFileOpen;
	JMenuItem menuFileSave;
	JMenuItem menuFileNew;
	JMenuItem menuFileExit;
	
	JPanel contentMenuItem;
	JPanel contentIngredient;
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	public GUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
		setBounds(100, 100, 1200, 800);
		/*
		 * Code to create the menuBar
		 */
			menuBar = new JMenuBar();
			
			JMenu menuFile = new JMenu("File");
				menuBar.add(menuFile);
				menuFileOpen = new JMenuItem("Open");
				menuFile.add(menuFileOpen);
				menuFileOpen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Main.load();
					}
				});
				menuFileNew = new JMenuItem("New");
				menuFile.add(menuFileNew);
				menuFileNew.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Main.create();
					}
				});
				menuFileSave = new JMenuItem("Save");
				menuFile.add(menuFileSave);
				menuFileSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Main.save();
					}
				});
				menuFileExit = new JMenuItem("Exit");
				menuFile.add(menuFileExit);
				menuFileExit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
		setJMenuBar(menuBar);
		
		/*
		 * Create content pane and editor
		 */
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	}

}
