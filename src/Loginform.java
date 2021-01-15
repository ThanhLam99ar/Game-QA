
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



// import java.util.regex.*; no use of this

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * Promp for login
 * @author ten nhom
 */
public class Loginform {

	private JFrame frmLoginForm;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	public static String tendn="";


	public Loginform() { 
		//connect();
		initialize();
	}

	/**
	 * Create the application.
	 */
	private void initialize() {
		frmLoginForm = new JFrame();
		frmLoginForm.getContentPane().setLayout(null);

		// render login content
		// Login input field
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(21, 53, 113, 33);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 20));
		frmLoginForm.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(21, 112, 97, 28);
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		frmLoginForm.getContentPane().add(lblNewLabel_1);

		txtUserName = new JTextField();
		txtUserName.getText();
		txtUserName.setBounds(144, 59, 212, 20);
		frmLoginForm.getContentPane().add(txtUserName);
		txtUserName.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(144, 116, 212, 20);
		frmLoginForm.getContentPane().add(txtPassword);
		
		// Login button
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(50, 175, 89, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tendn = txtUserName.getText();

//				String uEnter = txtUserName.getText();
//				String pEnter = new String(txtPassword.getPassword()); // change getText to getPassword
//				File inFile = new File("Login.txt");
//				FileReader fileReader = null;
//				try {
//					fileReader = new FileReader(inFile);
//				} catch (FileNotFoundException e2) {
//					e2.printStackTrace();
//				}
//				BufferedReader bufReader = new BufferedReader(fileReader);
//				boolean accept = false;
//				String line = ",";
//				try {
//					while ((line = bufReader.readLine()) != null) {
//						String[] credential = line.split(","); // Slipt by comma
//						accept = uEnter.equals(credential[0]) && pEnter.equals(credential[1]) ? true : false;
//						// in case more than 1 credential
//						if (accept) {
//							frmLoginForm.setVisible(false);
//							break;
//						}
//					}
//					if (!accept)
//						// Make a notification when wrong
//						JOptionPane.showMessageDialog(frmLoginForm, "Wrong username and password", "Invalid login", JOptionPane.CANCEL_OPTION);
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//
//				if (accept == true) {
//					try {
//						Game g = new Game();
//					} catch (Exception exception) {
//						System.out.println(exception.getMessage());
//					}
//				}
//
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltl","root","laithanhlam");
					java.sql.Statement stmt = con.createStatement();
					String url ="select * from account where Username ='"+txtUserName.getText()+"' and Password ='"+txtPassword.getText()+"'";
					ResultSet rs = ((java.sql.Statement) stmt).executeQuery(url);
					
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
						Menu2 m = new Menu2();
						m.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
					}
					con.close();
					}catch(Exception e1) {
						System.out.println(e1);
					}
			}
		});
		frmLoginForm.getContentPane().add(btnLogin);

		// Reset button
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(168, 175, 89, 23);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUserName.setText(null);
				txtPassword.setText(null);
			}

		});
		frmLoginForm.getContentPane().add(btnReset);

		// Register button
		JButton registerBtn = new JButton("Register");
		registerBtn.setBounds(277, 175, 89, 23);
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Signup form = new Signup();
			}

		});
		frmLoginForm.getContentPane().add(registerBtn);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\Picture\\Wallaper\\ocean-night-game-background_7814-303.jpg"));
		lblNewLabel_2.setBounds(0, 0, 434, 261);
		frmLoginForm.getContentPane().add(lblNewLabel_2);

		// render window
		frmLoginForm.setTitle("Login Form");
		frmLoginForm.setLocationRelativeTo(null);
		frmLoginForm.setBounds(100, 100, 450, 300);
		frmLoginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loginform promp = new Loginform();
					promp.frmLoginForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

