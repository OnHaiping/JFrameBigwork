package Frame;

import Tools.GUITools;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainFrame extends JFrame {
    JPanel panelMain, panelNorth, panelSouth, panelWest, panelCenter;//面板
    WelcomeJPanel welcomeJPanel;//欢迎面板
    CarPanel carPanel;//汽车管理面板
    ClientPanel clientPanel;//客户管理面板
    SalesPanel salesPanel;//销售信息管理面板
    StaffPanel staffPanel;//员工管理面板
    DigitalClockFrame clockPanel;

    public MainFrame() {
        panelMain = new JPanel(new BorderLayout());//创建主面板
        panelNorth = new JPanel();
        panelSouth = new JPanel();
        panelWest = new JPanel();
        panelCenter = new JPanel();
        // 时钟创建
        clockPanel = new DigitalClockFrame();
        panelNorth.add(clockPanel);

        welcomeJPanel = new WelcomeJPanel();
        panelCenter.add(welcomeJPanel);

        carPanel = new CarPanel();
        panelCenter.add(carPanel);
        carPanel.setVisible(false);

        clientPanel = new ClientPanel();
        panelCenter.add(clientPanel);
        clientPanel.setVisible(false);

        salesPanel = new SalesPanel();
        panelCenter.add(salesPanel);
        salesPanel.setVisible(false);

        staffPanel = new StaffPanel();
        panelCenter.add(staffPanel);
        staffPanel.setVisible(false);

        panelNorth.setBorder(BorderFactory.createEtchedBorder());//设置边框
        panelNorth.setPreferredSize(new Dimension(0, 70));
        panelWest.setBorder(BorderFactory.createEtchedBorder());//设置边框
        panelWest.setPreferredSize(new Dimension(140, 0));
        panelSouth.setBorder(BorderFactory.createEtchedBorder());//设置边框
        panelSouth.setPreferredSize(new Dimension(0, 50));

        JButton WelcomeManager = new JButton("欢迎页面");
        WelcomeManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeJPanel.setVisible(true);
                carPanel.setVisible(false);
                clientPanel.setVisible(false);
                salesPanel.setVisible(false);
                staffPanel.setVisible(false);
            }
        });
        JButton CarManager = new JButton("汽车管理");
        CarManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeJPanel.setVisible(false);
                carPanel.setVisible(true);
                clientPanel.setVisible(false);
                salesPanel.setVisible(false);
                staffPanel.setVisible(false);
            }
        });
        JButton ClientManager = new JButton("客户管理");
        ClientManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeJPanel.setVisible(false);
                carPanel.setVisible(false);
                clientPanel.setVisible(true);
                salesPanel.setVisible(false);
                staffPanel.setVisible(false);
            }
        });
        JButton SalesManager = new JButton("销售信息管理");
        SalesManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeJPanel.setVisible(false);
                carPanel.setVisible(false);
                clientPanel.setVisible(false);
                salesPanel.setVisible(true);
                staffPanel.setVisible(false);
            }
        });
        JButton StaffManager = new JButton("员工管理");
        StaffManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeJPanel.setVisible(false);
                carPanel.setVisible(false);
                clientPanel.setVisible(false);
                salesPanel.setVisible(false);
                staffPanel.setVisible(true);
            }
        });


        panelWest.setLayout(new GridLayout(8, 1));
        panelWest.add(WelcomeManager);
        panelWest.add(CarManager);
        panelWest.add(ClientManager);
        panelWest.add(SalesManager);
        panelWest.add(StaffManager);

        JLabel SchoolAddress = new JLabel("地址：山东青岛黄岛区嘉陵江西路425号   电话：400-888-888   邮编：266555");
        panelSouth.add(SchoolAddress);

        panelMain.add(panelNorth, BorderLayout.NORTH);     //北
        panelMain.add(panelWest, BorderLayout.WEST);       //西
        panelMain.add(panelSouth, BorderLayout.SOUTH);     //南
        panelMain.add(panelCenter, BorderLayout.CENTER);   //中

        this.setContentPane(panelMain);
        this.setTitle("汽车4s店信息管理系统");
        this.setSize(1200, 650);
        GUITools.setCenter(this);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}