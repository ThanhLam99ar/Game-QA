import javax.swing.*;

import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.*;

public class Signup extends JFrame {
    private final static String login = "Login.txt";
    // Swing component
    private Container container = new Container();
    JTextField username;
    JPasswordField password;
    
    public Signup() {
        super("Register Form");
        init();
    }

    private void init() {
        container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        //===============render input section===============;
        JPanel inputSection = new JPanel();
        inputSection.setLayout(null);

        // Add to container
        container.add(inputSection);
        // confirm button
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 13));
        confirmButton.setBounds(87, 109, 98, 23);
        inputSection.add(confirmButton);
        // Cancel Button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 13));
        cancelButton.setBounds(228, 109, 98, 23);
        inputSection.add(cancelButton);
        JLabel label_1 = new JLabel("User name:");
        label_1.setBounds(57, 39, 73, 14);
        inputSection.add(label_1);
        label_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 13));
        label_1.setForeground(Color.WHITE);
        username = new JTextField(20);
        username.setBounds(125, 37, 217, 20);
        inputSection.add(username);
        JLabel label = new JLabel("Password:");
        label.setBounds(57, 73, 74, 14);
        inputSection.add(label);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 13));
        password = new JPasswordField(20);
        password.setBounds(125, 71, 217, 20);
        inputSection.add(password);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("D:\\Picture\\Wallaper\\ocean-night-game-background_7814-303.jpg"));
        lblNewLabel.setBounds(0, 0, 394, 203);
        inputSection.add(lblNewLabel);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String credential = username.getText() + "," + new String(password.getPassword());
                if (writeFile(credential)) 
                    JOptionPane.showMessageDialog(container, "Successfully create new user\nPlease Login again ", "Successful",
                            JOptionPane.PLAIN_MESSAGE);
                else
                    JOptionPane.showMessageDialog(container, "Cannot create new user", "Error",
                            JOptionPane.CANCEL_OPTION);
            }
        });

        renderWindow();
    }

    private void renderWindow() {
        setSize(400, 232);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    private boolean writeFile(String credential) {
        PrintWriter outputStream = null;

        try {
            outputStream = new PrintWriter(new FileWriter(login, true));
            outputStream.println(credential);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (outputStream != null)
                outputStream.close();
        }
        
    }
}
