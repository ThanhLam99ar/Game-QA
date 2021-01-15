import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Menu extends JMenuBar {
    private JPanel FilePane;
    private JPanel DatabasePane;
    JMenu qa, acc; 
    JMenuItem miNew,miDataNew, miOpen;

    Menu(JPanel FilePane, JPanel DatabasePane) { // access in package only
        this.FilePane = FilePane;
        this.DatabasePane = DatabasePane;
        init();
    }

    private void init() {
        // Build the first menu.
        renderQAManager();
        // Build second menu in the menu bar.
        renderAccountManager();

        this.add(qa);
        this.add(acc);
    }

    private void renderQAManager() {
        qa = new JMenu("QA manager");
        // menu.setMnemonic(KeyEvent.VK_A);
        qa.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
        this.add(qa);

        // a group of JMenuItems
        miNew = new JMenuItem("New File", new ImageIcon("images/middle.gif"));
        miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        // menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really
        // do anything");
        miNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	JFrame fr = new JFrame();

                // JFrame config
                fr.setSize(500, 400);
                fr.setResizable(false);

                // Instruction Pane
                JPanel instructionPane = new JPanel(new FlowLayout());
                instructionPane.add(new JLabel("Format: <Question>?<Answer>"));
                instructionPane.add(new JLabel("Example: What is capital of VN?ha noi"));

                JButton btnSave = new JButton("Save");
                JButton btnCancel = new JButton("Cancel");

                JPanel listPane = new JPanel();
                listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

                JTextArea ta = new JTextArea(300, 300);

                // Lay out the label and scroll pane from top to bottom.

                listPane.add(ta);
                listPane.add(Box.createRigidArea(new Dimension(0, 5)));
                // listPane.add(listScroller);
                listPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                // Lay out the buttons from left to right.
                JPanel buttonPane = new JPanel();
                buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
                buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
                buttonPane.add(Box.createHorizontalGlue());
                buttonPane.add(btnCancel);
                buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
                buttonPane.add(btnSave);

                // Put everything together, using the content pane's BorderLayout.

                fr.add(instructionPane, BorderLayout.PAGE_START);
                fr.getContentPane().add(listPane, BorderLayout.CENTER);
                fr.getContentPane().add(buttonPane, BorderLayout.PAGE_END);
                fr.setVisible(true);

                btnSave.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	JFileChooser fc = new JFileChooser();

                        // FileChoose configuation
                        fc.setDialogTitle("Choose Directory to save files");
                        fc.setCurrentDirectory(new java.io.File(".")); // get current directory
                        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        fc.setAcceptAllFileFilterUsed(false);

                        // Function
                        int res = fc.showOpenDialog(null);
                        if (res == JFileChooser.APPROVE_OPTION) {
                            String file_name = JOptionPane.showInputDialog(fr, "File name: ");

                            String path = fc.getSelectedFile().getAbsolutePath() + "\\" + file_name;
                            System.out.println(path);

                            String[] lines = ta.getText().split("\n");

                            FileWriter ques_writer = null;
                            // FileWriter ans_writer = null;
                            try {
                                ques_writer = new FileWriter(new File(path + ".txt"));
                                // ans_writer = new FileWriter(new File(path + "_answer.txt"));
                                for (String line : lines) {
                                    // String[] questions = line.split("\\?");
                                    // System.out.println(questions[0]);
                                    // System.out.println(questions[1]);
                                    ques_writer.append(line +  "\n");
                                    // ans_writer.append(questions[1] + "\n");
                                }
                                ta.setText("");
                            } catch (IOException exeption) {
                                exeption.printStackTrace();
                                JOptionPane.showMessageDialog(fr, "Cannot save file", "Error", JOptionPane.ERROR_MESSAGE);
                            } finally {
                                if (ques_writer != null) {
                                    try {
                                        ques_writer.close();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                });
            }
        });
        qa.add(miNew);
        
        miDataNew = new JMenuItem("New Database", new ImageIcon("images/middle.gif"));
        miDataNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        // menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really
        // do anything");
        miDataNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	JFrame fr = new JFrame();
            	
                // JFrame config
                fr.setSize(500, 400);
                fr.setResizable(false);

                // Instruction Pane
                JPanel instructionPane = new JPanel(new FlowLayout());
                instructionPane.add(new JLabel("Enter Question & Answer"));
                //instructionPane.add(new JLabel());

                
                
                JButton btnSave = new JButton("Save");
                JButton btnCancel = new JButton("Cancel");

                JPanel listPane = new JPanel();
              
                 
                // Set the Boxayout to be Y_AXIS from top to down
                BoxLayout boxlayout = new BoxLayout(listPane, BoxLayout.Y_AXIS);
         
                listPane.setLayout(boxlayout);
                 
                // Set border for the panel
                //listPane.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));
                
                //JTextArea ta = new JTextArea(300, 300);
                JLabel idlabel= new JLabel("Enter ID:              ");
                JLabel questionlabel= new JLabel("Enter Question: ");
                JLabel answerlabel= new JLabel("Enter Answer:    ");
                JLabel typelabel= new JLabel("Enter Type:        ");
                //JLabel noticelabel= new JLabel("Want to add more question and answer, enter c. Not enter s:");
                JTextField idfield, quesfield, ansfield, typefield, noticefiled;
                idfield = new JTextField(20);
                quesfield = new JTextField(20);
                ansfield = new JTextField(20);
                typefield = new JTextField(20);
                //noticefiled = new JTextField(20);
                
                JPanel idPane = new JPanel(new FlowLayout());
                idPane.add(idlabel);
                idPane.add(idfield);
                
                JPanel quesPane = new JPanel(new FlowLayout());
                quesPane.add(questionlabel);
                quesPane.add(quesfield);
                
                JPanel answerPane = new JPanel(new FlowLayout());
                answerPane.add(answerlabel);
                answerPane.add(ansfield);
                
                JPanel typePane = new JPanel(new FlowLayout());
                typePane.add(typelabel);
                typePane.add(typefield);
                
                /*JPanel noticePane = new JPanel(new FlowLayout());
                noticePane.add(noticelabel);
                noticePane.add(noticefiled);*/
                
                // Lay out the label and scroll pane from top to bottom.

                //listPane.add(ta);
                listPane.add(idPane);
                listPane.add(quesPane);
                listPane.add(answerPane);
                listPane.add(typePane);
                //listPane.add(noticePane);
                
                listPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

                // Lay out the buttons from left to right.
                JPanel buttonPane = new JPanel();
                buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
                buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
                buttonPane.add(Box.createHorizontalGlue());
                buttonPane.add(btnCancel);
                buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
                buttonPane.add(btnSave);
				
					
                // Put everything together, using the content pane's BorderLayout.

                fr.add(instructionPane, BorderLayout.PAGE_START);
                fr.getContentPane().add(listPane, BorderLayout.CENTER);
                fr.getContentPane().add(buttonPane, BorderLayout.PAGE_END);
                fr.setVisible(true);

                btnSave.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	try{ 
                    		  
                    		Class.forName("com.mysql.cj.jdbc.Driver");
                    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltl","root","laithanhlam"); 
                    	  
                    		PreparedStatement ps=conn.prepareStatement("insert into CauHoi values(?,?,?,?)");  
                    	  
                    		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
                    		while(true){  
                    	  
                    			String s1= idfield.getText();
                    			s1=br.readLine();  
                    			int id=Integer.parseInt(s1);  
                    	       
                    			String ques = quesfield.getText();
                    	        ques=br.readLine();
                    			
                    			  
                    			String answer= ansfield.getText(); 
                    			answer=br.readLine();
 
                    			String type= typefield.getText();
                    			type=br.readLine();
                    	
                    	  
                    			ps.setInt(4,id);  
                    			ps.setString(1,ques);  
                    			ps.setString(2,answer);
                    			ps.setString(3,type);
                    	  
                    			ps.addBatch();
                    			break;
                    			/*String ans= noticefiled.getText(); 
                    			ans=br.readLine();  
                    			if(ans.equals("s")){  
                    				break;
                    			}*/
                    		}  
                    		ps.executeBatch();  
                    		conn.commit();
                    	  
                    		//conn.close();  
                    		}catch(Exception e1)
                    			{System.out.println(e1);}  
                    }
                });
            }
        });
        qa.add(miDataNew);

        miOpen = new JMenuItem("Open", new ImageIcon("images/middle.gif"));
        miOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        // menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really
        // do anything");
        miOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = { "Database", "File" };
                int isFile = JOptionPane.showOptionDialog(null, "Choose play type",
                                "Select play type",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                JFrame fr = new JFrame("Choose Your Challenge");
                // Config JFrame
                fr.setSize(400, 150);
                fr.setVisible(true);
                fr.setLocation(100, 100);
                fr.setResizable(false);

                // render File Chooser
                if (isFile == 1)
                    fr.add(FilePane);
                else if (isFile == 0) {
                    fr.add(DatabasePane);
                }
            }

        });
        qa.add(miOpen);
    }

    private void renderAccountManager() {
        acc = new JMenu("Account Manager");
        acc.setMnemonic(KeyEvent.VK_N);
        acc.getAccessibleContext().setAccessibleDescription("This menu does nothing");
    }
}
