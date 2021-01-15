import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class Profile extends JFrame {

	private JPanel contentPane;
	private JTextField textplayer;
	private JTextField textname;
	private JTextField textpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile p = new Profile();
					p.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public Profile()  throws IOException{
		init();
	}
	public void init() throws IOException{
		
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("New label");
		label.setBounds(27, 29, 46, 14);
		getContentPane().add(label);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 298, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Player:");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 15));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 151, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblusername = new JLabel("Username:");
		lblusername.setForeground(Color.WHITE);
		lblusername.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 15));
		lblusername.setBounds(10, 212, 83, 14);
		contentPane.add(lblusername);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 15));
		lblNewLabel_2.setBounds(10, 261, 83, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblscore = new JLabel("Score: 0");
		lblscore.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 15));
		lblscore.setForeground(Color.WHITE);
		lblscore.setBounds(10, 307, 46, 14);
		contentPane.add(lblscore);
		
		textplayer = new JTextField();
		textplayer.getText();
		textplayer.setBounds(85, 149, 162, 20);
		contentPane.add(textplayer);
		textplayer.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\ADMIN\\eclipse-workspace\\ExampleSQL\\src\\avt.png"));
		lblNewLabel_4.setBounds(77, 0, 129, 127);
		contentPane.add(lblNewLabel_4);
		
		textname = new JTextField();
		textname.setEditable(false);
		textname.setBounds(85, 210, 162, 20);
		contentPane.add(textname);
		textname.setColumns(10);
		
		textpass = new JTextField();
		textpass.setEditable(false);
		textpass.setBounds(85, 259, 162, 20);
		contentPane.add(textpass);
		textpass.setColumns(10);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setBounds(85, 343, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\Picture\\Wallaper\\Untitled-1.jpg"));
		lblNewLabel_1.setBounds(0, 0, 282, 413);
		contentPane.add(lblNewLabel_1);
	}
	
	
}

