package guis;
import constants.CommonConstants;

import javax.swing.*;

public class Form extends JFrame{
    public Form(String title){
        super(title);

        setSize(520,680);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(null);

        getContentPane().setBackground(CommonConstants.PRIMARY_COLOR);
        //Load GUI on center of screen
        setLocationRelativeTo(null);

        setResizable(false);
    }
}
