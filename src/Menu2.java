import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.awt.event.ActionEvent;

public class Menu2 extends JFrame {

    private int point;
    private int question_num = 0;

    // Swing components
    private Container container;
    private JPanel questionPane, answerPane, nextChallengePane,contentPane;
    private static JPanel FilePane, DatabasePane;
    private JTextField answer;
    private JLabel checker, question, score;
    private BufferedReader reader_Ans;
    private BufferedReader reader_Ques;
    private File questionFile, answerFile;
    private JMenuBar menuBar;
    final JFileChooser fileDialog = new JFileChooser();
    static ArrayList<String> ques = new ArrayList<String>();
    static ArrayList<String> ans = new ArrayList<String>();
    // Queue and Stack
    private Queue<File> queue = new LinkedList<> ();
    private Stack<File> stack = new Stack<File> ();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu2 frame = new Menu2();
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
    private void init() throws IOException {
        // readFile(); // add this line to fix bug
    	
        this.point = 0;
        container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // arrange vertialy

        renderDatabasePane();
    }
	public Menu2() {

	    
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Open Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Profile p;
				try {
					Profile p1 = new Profile();
					p1.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//open database
				renderDatabasePane();
				
			}
		});
		btnNewButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 25));
		btnNewButton.setBounds(10, 155, 158, 82);
		contentPane.add(btnNewButton);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					gamefinal g = new gamefinal();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewGame.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 25));
		btnNewGame.setBounds(10, 38, 158, 82);
		contentPane.add(btnNewGame);
		
		JButton btnProfile = new JButton("Profile");
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Profile p;
				try {
					p = new Profile();
					p.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnProfile.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 25));
		btnProfile.setBounds(201, 38, 158, 82);
		contentPane.add(btnProfile);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 25));
		btnExit.setBounds(201, 155, 158, 82);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("GROUP NINE");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 50));
		lblNewLabel.setBounds(369, 95, 262, 69);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\Picture\\Wallaper\\create-vector-2d-game-background-for-your-game.jpg"));
		lblNewLabel_1.setBounds(0, 0, 673, 285);
		contentPane.add(lblNewLabel_1);}
		
    private void makeNewGame() {
        // config new game
        System.out.println(ques.size());
        question_num = 0;
        //point = 0;
        question.setText("Quesiton: " + ques.get(question_num));
        score.setText("Score: " + point);
        answer.setEnabled(true);
    }
    
		private void renderDatabasePane() {
	        DatabasePane = new JPanel();
	        DatabasePane.setPreferredSize(new Dimension(400, 100));
	        String typelist = null;
	        String[] questionType= {} /*= { "Country", "Animal", "Facts" }*/;
	        
	        try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/ltl","root","laithanhlam");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from CauHoi");
				while(rs.next()) {
					
					typelist= rs.getString(3);
					
					List<String> list = new ArrayList<String>(Arrays.asList(questionType));
			        // Add the new element 
					if(!(list.contains(typelist))) {
						list.add(typelist); 
					}
			        // Convert the Arraylist back to array 
			        questionType = list.toArray(questionType);
			        
				}
				//con.close();
			}
			catch (Exception e){
					System.out.println(e);
			}
	        JComboBox<String> list2 = new JComboBox<String>(questionType);

	        // Confirm button
	        JButton confirmBtn = new JButton("Play");
	        confirmBtn.setPreferredSize(new Dimension(160, 30));
	        confirmBtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e)  {
	            	String fileLocation = "src/SoundBG.wav";
                    ArrayList<SoundThread> threads = new ArrayList<>();
                    int numberOfThread = 50;
                    int delay = 500;
                    for(int i=0;i< numberOfThread;i++) {
                    	SoundThread thread = new SoundThread(fileLocation,delay+=50);
                    	threads.add(thread);
                    }
	        		do {
	        			new Thread(threads.get(0)).start();
	        			threads.remove(0);
	        		}while(threads.size()>1);
	        		threads.clear();
	        		String type = (String) list2.getSelectedItem();
	                System.out.println(type);
	                
	                // Get question and answer from database to 2 array ques and ans
	                // SELECT question, answer FROM challenge_table WHERE questionType = "";
	                String questlist = null;
	                String[] questiontype = {  };
	                String anslist = null;
	                String[] answertype = {  };
	                PreparedStatement pst=null;
	                Connection conn=null;
	                ResultSet rs=null;
	                try {
	        			
	                	String sql = "select * from CauHoi where Type=?";
	                    conn = JVConnected.getConnection();
	                    pst=conn.prepareStatement(sql);
	                    pst.setString(1, type);
	                    
	                    rs = pst.executeQuery();
	        			while(rs.next()) {
	        				
	        				questlist= rs.getString(1);
	        				anslist = rs.getString(2);
	        				
	        				List<String> queslist = new ArrayList<String>(Arrays.asList(questiontype));
	        				List<String> anlist = new ArrayList<String>(Arrays.asList(answertype));
	        		        // Add the new element 
	        				queslist.add(questlist); 
	        				anlist.add(anslist);
	        		        // Convert the Arraylist back to array 
	        		        questiontype = queslist.toArray(questiontype);
	        		        answertype = anlist.toArray(answertype);
	        		        
	        			}
	        			//con.close();
	        		}
	        		catch (Exception e1){
	        				System.out.println(e1);
	        		}
	                
	                // clear list
	                ques.clear();
	                ans.clear();
	               
	                // Make a for loop to add answer and question to 2 array ques and ans
	                for (int i = 0; i < questiontype.length; i++) {
	                    // accessing each element of array 
	                	ques.add(questiontype[i]);
	                } 
	                
	               
	                for (int i = 0; i < answertype.length; i++) { 
	                    // accessing each element of array 
	                	ans.add(answertype[i]);
	                }

	                makeNewGame();
	            }
	        });

	        DatabasePane.add(new JLabel("Choose question type: "));
	        DatabasePane.add(list2);
	        DatabasePane.add(confirmBtn);
	}
}
