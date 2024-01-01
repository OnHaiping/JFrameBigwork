package Frame;

import Tools.GUITools;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageDialog extends JDialog implements ActionListener {
    public MessageDialog(JFrame frame, String message) {
        super(frame);
        this.setLayout(null);
        this.setSize(200, 180);
        this.setTitle("提示");
        GUITools.setCenter(this);
        JLabel label = new JLabel();
        label.setText(message);
        label.setFont(new Font("宋体", Font.PLAIN, 14));
        label.setBounds(30, 30, 150, 40);
        this.add(label);
        JButton queding = new JButton("确定");
        queding.setBounds(70, 90, 60, 40);
        queding.addActionListener(this);
        this.add(queding);
        this.setModal(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
