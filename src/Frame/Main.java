package Frame;

import Bean.Staff;
import MysqlMethod.StaffDao;
import Tools.GUITools;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Main extends JFrame implements ActionListener {

    static String sname;
    JPanel panelMain;//主面板
    JLabel labelUserName, labelPassWord;//标签
    JTextField textUserName;//单行文本框
    JPasswordField textPassWord;//密码框
    JButton buttonLogin, buttonExit;//按钮

    public Main() {
        // 设置欢迎标题
        super("汽车4s店信息管理系统欢迎您");
        panelMain = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("images/login.png");//登录界面背景
                g.drawImage(img.getImage(), 0, 0, null);
            }
        };

        panelMain.setLayout(null);//空布局

        //用户登录页面
        labelUserName = new JLabel("用户名:");
        labelPassWord = new JLabel("密码:");
        labelUserName.setBounds(100, 110, 50, 20);
        labelPassWord.setBounds(104, 140, 50, 20);

        textUserName = new JTextField(26);
        textUserName.setBounds(160, 110, 125, 20);
        textPassWord = new JPasswordField(26);
        textPassWord.setBounds(160, 140, 125, 20);

        //登录按钮
        buttonLogin = new JButton("登录");
        buttonLogin.setBounds(145, 190, 60, 40);
        buttonLogin.setToolTipText("点击这里登录");
        buttonLogin.addActionListener(this);

        //退出按钮
        buttonExit = new JButton("退出");
        buttonExit.setBounds(225, 190, 60, 40);
        buttonExit.setToolTipText("点击这里退出系统");
        buttonExit.addActionListener(this);

        //把各个组件添加到界面上
        panelMain.add(labelUserName);
        panelMain.add(labelPassWord);
        panelMain.add(textUserName);
        panelMain.add(textPassWord);
        panelMain.add(buttonLogin);
        panelMain.add(buttonExit);

        this.setContentPane(panelMain);
        this.setSize(416, 310);    //大小
        this.setVisible(true);                  //可视
        GUITools.setCenter(this);
        // 在这里设置了不能最大化界面
        this.setResizable(false);
        // 设置默认关闭操作
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonLogin) {
            String sid = textUserName.getText();
            String password = textPassWord.getText();
            if (StaffDao.login(sid, password)) {
                JOptionPane.showMessageDialog(null, "登录成功");
                try {
                    Staff staff = StaffDao.select(sid);
                    sname = staff.getsName();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                new MainFrame();//创建主窗体
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "用户名和密码错误");
            }
        }
        if (e.getSource() == buttonExit) {
            System.exit(0);//系统关闭退出
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
