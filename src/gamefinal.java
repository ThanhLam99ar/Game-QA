

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;



public class gamefinal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private int point;
    private int question_num = 0;

    // Swing components
    private Container container;
    private JPanel answerPane;
    private static JPanel FilePane,DatabasePane;
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
    private int isStack;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    
    
    public gamefinal() throws IOException {
        super("Challenge Gameshow"); // use this instead of setTitle for JFrame
        init();
    }
    
    

    private void init() throws IOException {
        // readFile(); // add this line to fix bug

        this.point = 0;
        container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // arrange vertialy

        renderFileChooser();
        // container.add(FilePane);

        renderQuestionPane();
        renderDatabasePane();
        renderAnswerPane();
        renderNextChallengePane();
        container.add(answerPane);
        
                question = new JLabel("Quesiton: ");
                question.setForeground(Color.WHITE);
                question.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 20));
                question.setBounds(40, 50, 315, 14);
                answerPane.add(question);
                JButton next = new JButton("Next Challenge");
                next.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 13));
                next.setBounds(247, 246, 131, 23);
                answerPane.add(next);
                
                lblNewLabel = new JLabel("Player:");
                lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 13));
                lblNewLabel.setForeground(Color.WHITE);
                lblNewLabel.setBounds(31, 15, 46, 14);
                answerPane.add(lblNewLabel);
                
                lblNewLabel_1 = new JLabel("New label");
                lblNewLabel_1.setIcon(new ImageIcon("D:\\Picture\\Wallaper\\ocean-night-game-background_7814-303.jpg"));
                lblNewLabel_1.setBounds(0, 0, 625, 297);
                answerPane.add(lblNewLabel_1);
                next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // read file
                    if (isStack == 0) { // Means choose Queue	
                    	handleQueue();
                    } else if (isStack == 1) { // Means choose Stack
                    	handleStack();
                    }
                    makeNewGame();
                } catch (Exception ecep) {
                    JOptionPane.showMessageDialog(container, "Cannot Open file", "Error", JOptionPane.CANCEL_OPTION);
                }
            }
        });

        menuBar = new Menu(FilePane,DatabasePane);

        this.setJMenuBar(menuBar);
        renderWindow();
    }

    /**
     * Add this funciton to fix bug
     * 
     * @throws IOException
     */
    private void readFile() throws IOException {
    	// String question_name = questionFile.getName();
        // answerFile = new File(questionFile.getParentFile().getAbsolutePath() + "\\"
                // + question_name.substring(0, question_name.lastIndexOf("_")) + "_answer.txt");
        InputStream inputStream_Ques = new FileInputStream(questionFile);
        // InputStream inputStream_Ans = new FileInputStream(answerFile);
        InputStreamReader inputStreamReader_Ques = new InputStreamReader(inputStream_Ques);
        // InputStreamReader inputStreamReader_Ans = new InputStreamReader(inputStream_Ans);
        reader_Ques = new BufferedReader(inputStreamReader_Ques);
        // reader_Ans = new BufferedReader(inputStreamReader_Ans);

        // clear list
        ques.clear();
        ans.clear();
        String line_Ques = "";
        // String line_Ans = "";
        while ((line_Ques = reader_Ques.readLine()) != null) {
            String[] challenges = line_Ques.split("\\?");
            ques.add(challenges[0]);
            ans.add(challenges[1]);
        }

        // close stream
        inputStream_Ques.close();
        // inputStreamReader_Ans.close();
    }

    private void renderWindow() {
        setSize(630, 348);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
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
        
		 
        
        // Dropdown list
        // get list of question type from datbase and put to array questionType
        // SELECT UNIQUE questionType FROM challenge_table
        
        JComboBox<String> list2 = new JComboBox<String>(questionType);

        // Confirm button
        JButton confirmBtn = new JButton("Play");
        confirmBtn.setPreferredSize(new Dimension(160, 30));
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String fileLocaton = "BG.wav";
        		ArrayList<SoundThread > threads = new ArrayList<>();
        		int numberOfThreads = 5;
        		int delay =500;
        		for(int i=0;i<numberOfThreads;i++) {
        			SoundThread thread= new SoundThread (fileLocaton,delay+=50);
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

    private void renderFileChooser() {
        FilePane = new JPanel();
        FilePane.setPreferredSize(new Dimension(400, 100));

        // question
        JPanel questionFilePane = new JPanel(new FlowLayout(1, 10, 0));
        JButton ques_choose_file = new JButton("Choose File");
        JLabel ques_file_choosed = new JLabel("");

        // JFileChooser config
        fileDialog.setDialogTitle("Choose Question file");
        fileDialog.setCurrentDirectory(new java.io.File(".")); // get current directory
        fileDialog.setMultiSelectionEnabled(true);

        ques_choose_file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int res = fileDialog.showOpenDialog(null);
                if (res == JFileChooser.APPROVE_OPTION) {
                	String[] options = {"Queue", "Stack"};
                	 isStack = JOptionPane.showOptionDialog(null, "Choose play type",
                             "Select play type",
                             JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
             		File[] files = fileDialog.getSelectedFiles();
                	for (File file : files) { 
                		if (isStack == 0) { // Means choose queue
                			queue.add(file);
                		} else if (isStack == 1) { // Means choose stack
                			stack.add(file);
                		}	
                	}
//                	questionFile = fileDialog.getSelectedFile();
//                    ques_file_choosed.setText(questionFile.getName());
                }
            }

        });

        questionFilePane.add(new JLabel("Question File Path: "));
        questionFilePane.add(ques_choose_file);
        questionFilePane.add(ques_file_choosed);

        // Confirm button
        JButton confirmBtn = new JButton("Play");
        confirmBtn.setPreferredSize(new Dimension(160, 30));
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String fileLocaton = "BG.wav";
        		ArrayList<SoundThread> threads = new ArrayList<>();
        		int numberOfThreads = 5;
        		int delay =500;
        		for(int i=0;i<numberOfThreads;i++) {
        			SoundThread thread= new SoundThread(fileLocaton,delay+=50);
        			threads.add(thread);
        			
        		}
        		do {
        			new Thread(threads.get(0)).start();
        			threads.remove(0);
        		}while(threads.size()>1);
        		threads.clear();

                try {
                    // read file
                    if (isStack == 0) { // Means choose Queue	
                    	handleQueue();
                    } else if (isStack == 1) { // Means choose Stack
                    	handleStack();
                    }
                    makeNewGame();
                } catch (Exception ecep) {
                    JOptionPane.showMessageDialog(container, "Cannot Open file", "Error", JOptionPane.CANCEL_OPTION);
                }
            }
        });

        FilePane.add(questionFilePane);
        // FilePane.add(answerFilePane);
        FilePane.add(confirmBtn);
        FilePane.add(new JLabel("====================================="));
    }
    
    private void handleQueue() throws IOException {
    	questionFile = queue.remove();
        readFile();
    }
    private void handleStack() throws IOException {
    	questionFile = stack.pop();
        readFile();
    }
    private void makeNewGame() {
        // config new game
        question_num = 0;
        point = 0;
        question.setText("Quesiton: " + ques.get(question_num));
        score.setText("Score: " + point);
        answer.setEnabled(true);
    }

    private void renderQuestionPane() {
    }

    private void renderAnswerPane() {
        answerPane = new JPanel();

        answer = new JTextField(20);
        answer.setBounds(174, 197, 292, 20);
        answer.addActionListener(this); // add event listener
        checker = new JLabel("");
        checker.setBounds(329, 15, 0, 0);
        score = new JLabel("Score: " + this.point);
        score.setForeground(Color.WHITE);
        score.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 13));
        score.setBounds(491, 15, 105, 14);

        answer.setEnabled(false);
        answerPane.setLayout(null);

        JLabel label = new JLabel("Answer: ");
        label.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 20));
        label.setForeground(Color.WHITE);
        label.setBounds(47, 200, 80, 14);
        answerPane.add(label);
        answerPane.add(answer);
        answerPane.add(score);
        answerPane.add(checker);
    }
    
    private void renderNextChallengePane() {
    }

    public static void main(String[] args) throws IOException {
    	gamefinal g = new gamefinal();
        String fileLocation = "src/soundbackground.wav";
       
       
    }

    // Catch event
    @Override
    public void actionPerformed(ActionEvent e) {
        String res = answer.getText();

        if (res.equalsIgnoreCase(ans.get(question_num))) {
            question_num++;
            point++;
            checker.setText("");
            answer.setText("");
            score.setText("Score: " + point);
           try {
			RightMusic right = new RightMusic();
			right.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
           
           

            // try and catch error when out of quesion ==> end game
            try {
                question.setText("Question: " + ques.get(question_num));
            } /*catch (Exception exception) {
                question.setText("Game Over!!!");
                answer.setEnabled(false);*/ // disable answer when game end
            catch (IndexOutOfBoundsException exception) {
                question.setText("Game Over!!!");
                answer.setEnabled(false); // disable answer when game end
            }
        } else {
        	try {
				WrongMusic wrong = new WrongMusic();
				wrong.start();
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
            checker.setText("Wrong aswer!!!");
            
        }
    }

}