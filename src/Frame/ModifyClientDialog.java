package Frame;

import Bean.Client;
import MysqlMethod.ClientDao;
import Tools.GUITools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyClientDialog extends JDialog implements ActionListener {
    private JTextField clname;
    private JComboBox clsex;
    private JTextField clage;
    private JTextField clplace;
    private JTextField clnum;
    private JTextField clrecord;
    private JFrame main;//对话框依附的窗体
    private Client Log;
    public ModifyClientDialog(JFrame jFrame, Client client) {
        super(jFrame);
        this.Log = client;
        main = jFrame;
        this.setLayout(null);
        this.setSize(400, 500);
        GUITools.setCenter(this);
        this.setModal(true);
        this.setTitle("修改用户");

        //名字
        JLabel Jclname = new JLabel("名字：");
        Jclname.setBounds(70, 40, 70, 30);
        this.add(Jclname);
        clname = new JTextField(10);
        clname.setBounds(140, 40, 150, 30);
        this.add(clname);

        //性别
        JLabel Jclsex = new JLabel("性别：");
        Jclsex.setBounds(70, 70, 70, 30);
        this.add(Jclsex);
        clsex = new JComboBox(new String[]{"男","女"});
        clsex.setBounds(140, 70, 150, 30);
        this.add(clsex);

        //年龄
        JLabel Jclage = new JLabel("年龄：");
        Jclage.setBounds(70, 100, 70, 30);
        this.add(Jclage);
        clage = new JTextField(10);
        clage.setBounds(140, 100, 150, 30);
        this.add(clage);

        //住址
        JLabel Jclplace = new JLabel("住址：");
        Jclplace.setBounds(70, 130, 70, 30);
        this.add(Jclplace);
        clplace = new JTextField(10);
        clplace.setBounds(140, 130, 150, 30);
        this.add(clplace);

        //联系方式
        JLabel Jclnum = new JLabel("联系方式：");
        Jclnum.setBounds(70, 160, 70, 30);
        this.add(Jclnum);
        clnum = new JTextField(10);
        clnum.setBounds(140, 160, 150, 30);
        this.add(clnum);

        //备注
        JLabel Jclrecord = new JLabel("备注：");
        Jclrecord.setBounds(70, 190, 70, 30);
        this.add(Jclrecord);
        clrecord = new JTextField(10);
        clrecord.setBounds(140, 190, 150, 30);
        this.add(clrecord);

        //提交
        JButton submit = new JButton("提交");
        submit.setBounds(90, 350, 60, 30);
        submit.addActionListener(this);
        this.add(submit);

        //取消
        JButton cancel = new JButton("取消");
        cancel.setBounds(210, 350, 60, 30);
        cancel.addActionListener(this);
        this.add(cancel);
        System.out.println(String.valueOf(client.getClId()));
        //赋值
//        clid.setText(String.valueOf(client.getClId()));
        clname.setText(client.getClName());
        clsex.setSelectedItem(client.getClSex());
        clage.setText(String.valueOf(client.getClAge()));
        clplace.setText(client.getClPlace());
        clnum.setText(client.getClNum());
        clrecord.setText(client.getClRecord());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String common = e.getActionCommand();
        if (common.equals("提交")) {
            try {
                Client client = new Client();
                client.setClId(Log.getClId());
                client.setClName(clname.getText());
                client.setClSex(String.valueOf(clsex.getSelectedItem()));
                int clientAge = Integer.parseInt(clage.getText());
                client.setClAge(clientAge);
                client.setClPlace(clplace.getText());
                client.setClNum(clnum.getText());
                client.setClRecord(clrecord.getText());
                ClientDao.update(client);
                MessageDialog d = new MessageDialog(main, "修改成功");
                d.setVisible(true);
                this.dispose();
                // 验证年龄格式
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "年龄格式不对", "错误", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } catch (Exception e1) {
                MessageDialog d = new MessageDialog(main, "修改失败");
                d.setVisible(true);
                e1.printStackTrace();
            }
        } else {
            this.dispose();
        }
    }

}

