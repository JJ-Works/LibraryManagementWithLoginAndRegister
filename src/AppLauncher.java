//we create this class because if we run the program in LoginFormGUI it may be unable to render all the components successfully.

import guis.LoginFormGUI;
import guis.RegisterFormGUI;
import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new LoginFormGUI().setVisible(true);

                //test to check if there is username in the database
//                System.out.println(db.MyJDBC.checkUser("username123"));

//                System.out.println(db.MyJDBC.register("James Moriarty","Sherlock"));
                System.out.println("Returned value is "+ db.MyJDBC.validateLogin("James Moriarty","Sherlock"));
            }
        });
    }
}
