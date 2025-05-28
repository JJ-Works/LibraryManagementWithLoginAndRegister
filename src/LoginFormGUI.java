package guis;

import constants.CommonConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFormGUI extends guis.Form {
    public LoginFormGUI(){
        super("Login");


        addGuiComponents();
    }

    private void addGuiComponents(){
        JLabel loginLabel = new JLabel("Login");

        loginLabel.setBounds(0,20,520,100);

        loginLabel.setForeground(CommonConstants.TEXT_COLOR);

        loginLabel.setFont(new Font("Dialog",Font.BOLD,48));

        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loginLabel);

        // Username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 150, 400, 30);
        usernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog",Font.BOLD,24));

        add(usernameLabel);

        // Username text field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(50, 190, 400, 30);
        add(usernameField);

        // Password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 240, 400, 30);
        passwordLabel.setFont(new Font("Dialog",Font.BOLD,24));
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        add(passwordLabel);

        // Password text field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 280, 400, 30);
        add(passwordField);

        //Add button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(200, 350, 120, 40);
        loginButton.setBackground(CommonConstants.SECONDARY_COLOR);
        loginButton.setForeground(CommonConstants.TEXT_COLOR);
        loginButton.setFont(new Font("Dialog", Font.BOLD, 18));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();

                String password = new String(passwordField.getPassword());

                // Aba validate garna paryo

                if (db.MyJDBC.validateLogin(username,password)){
                    JOptionPane.showMessageDialog(LoginFormGUI.this,"Login Successful!");

                    //we need to open the Library management gui
                    //But first we need to close the LoginGUI

                    LoginFormGUI.this.dispose();

                    guis.LibraryManagementGUI libraryManagementGUI = new guis.LibraryManagementGUI();
                    libraryManagementGUI.setVisible(true);
                } else{
                    JOptionPane.showMessageDialog(LoginFormGUI.this,"Failed to Login!");
                }
            }
        });


        add(loginButton);

        JLabel registerLabel = new JLabel("Not a user? Register");
        registerLabel.setBounds(160, 400, 200, 30);
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);
        registerLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                registerLabel.setForeground(new Color(70,130,180)); // Change to your desired color
            }
            @Override
            public void mouseExited(MouseEvent e) {
                registerLabel.setForeground(CommonConstants.TEXT_COLOR); // Change to your desired color
            }
            @Override
            public void mouseClicked(MouseEvent e) {

                //To dispose the login window
                LoginFormGUI.this.dispose();

                //To launch the register window
                new guis.RegisterFormGUI().setVisible(true);
            }
        });
        // Add the label to the container
        add(registerLabel);
    }
}
