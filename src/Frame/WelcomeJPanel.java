package Frame;

import javax.swing.*;

public class WelcomeJPanel extends JPanel {

    public WelcomeJPanel() {
        JLabel lblWelcome = new JLabel();
        add(lblWelcome);
        lblWelcome.setIcon(new ImageIcon("images/R-C.jpg"));
    }
}