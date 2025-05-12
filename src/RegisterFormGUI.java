package guis;
import constants.CommonConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterFormGUI extends guis.Form {
    public RegisterFormGUI() {
        super("Register");
        addGuiComponents();
    }

    private void addGuiComponents() {
        JLabel registerLabel = new JLabel("Register");

        registerLabel.setBounds(0, 20, 520, 100);

        registerLabel.setForeground(CommonConstants.TEXT_COLOR);

        registerLabel.setFont(new Font("Dialog", Font.BOLD, 48));

        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(registerLabel);

        // Username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 150, 400, 30);
        usernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.BOLD, 24));

        add(usernameLabel);

        // Username text field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(50, 190, 400, 30);
        add(usernameField);

        // Password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 240, 400, 30);
        passwordLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        add(passwordLabel);

        // Password text field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 280, 400, 30);
        add(passwordField);

        // Re-enter Password label
        JLabel reenterPasswordLabel = new JLabel("Re-enter Password:");
        reenterPasswordLabel.setBounds(50, 330, 400, 30);
        reenterPasswordLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        reenterPasswordLabel.setForeground(CommonConstants.TEXT_COLOR);
        add(reenterPasswordLabel);

        // Re-enter Password text field
        JPasswordField reenterPasswordField = new JPasswordField();
        reenterPasswordField.setBounds(50, 370, 400, 30);
        add(reenterPasswordField);

        // Add a button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(200, 430, 120, 40);
        registerButton.setBackground(CommonConstants.SECONDARY_COLOR);
        registerButton.setForeground(CommonConstants.TEXT_COLOR);
        registerButton.setFont(new Font("Dialog", Font.BOLD, 18));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();

                String password = new String(passwordField.getPassword());

                String rePassword = new String(reenterPasswordField.getPassword());

                //Aba validate user password
                if (validateUserInput(username,password,rePassword)){
                    //If condition true we register to the DB and then we dispose this window

                    if(db.MyJDBC.register(username, password)){
                        RegisterFormGUI.this.dispose();

                        /*We have to create this manually meaning we can't just do new guis.Login...... because the setVisible is a method and inorder
                          to use the method we must create an object and since RegisterFormGUI doesn't have constructor we Cannot do that like
                          LoginFormGUI as it has constructor that calls upon the method getGuiComponents();
                        * */
                        guis.LoginFormGUI loginFormGUI = new guis.LoginFormGUI();

                        JOptionPane.showMessageDialog(loginFormGUI, "Registration Successful");
                        loginFormGUI.setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(RegisterFormGUI.this,"Error: Username Already Taken!!");
                    }
                }else{
                    JOptionPane.showMessageDialog(RegisterFormGUI.this,"Username must be at least 6 characters \n"
                    + "and/or Password must match");
                }
            }
        });
        // Add the button to the container
        add(registerButton);

        JLabel loginLabel = new JLabel("Have an account? Login Here");
        loginLabel.setBounds(160, 480, 200, 30);
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);
        loginLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // mouse listener add garna lako
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterFormGUI.this.dispose();

                new guis.LoginFormGUI().setVisible(true);

            }
        });

        // Add the label to the container
        add(loginLabel);
    }

    private boolean validateUserInput(String username,String password,String reenterPassword){

        if (username.isEmpty() || password.isEmpty() || reenterPassword.isEmpty())
            return false;
        else if (username.length() < 6)
            return false;
        else if (!password.equals(reenterPassword))
            return false;
        else
            return true;
    }

}