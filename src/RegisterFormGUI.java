package guis;
import constants.CommonConstants;

import javax.swing.*;
import java.awt.*;
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

        // Set cursor to hand cursor
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

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
}