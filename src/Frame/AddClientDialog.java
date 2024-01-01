package Frame;

import Bean.Client;
import MysqlMethod.ClientDao;
import MysqlMethod.IDDao;
import Tools.GUITools;
import Tools.toolUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClientDialog extends JDialog implements ActionListener {
    private JTextField clid;
    private JTextField clname;
    private JComboBox clsex;
    private JTextField clage;
    private JTextField clplace;
    private JTextField clnum;
    private JTextField clrecord;
    private JFrame main;//对话框依附的窗体

    public AddClientDialog(JFrame jFrame) {
        super(jFrame);
        main = jFrame;
        this.setLayout(null);
        this.setSize(400, 500);
        GUITools.setCenter(this);
        this.setModal(true);
        this.setTitle("添加客户信息");

        //编号
        JLabel Jclid = new JLabel("编号：");
        Jclid.setBounds(70, 10, 70, 30);
        this.add(Jclid);
        clid = new JTextField(10);
        clid.setBounds(140, 10, 150, 30);
        this.add(clid);

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String common = e.getActionCommand();
        if (common.equals("提交")) {
            Client client = new Client();
            // 判断非空
            if (toolUtil.isEmpty(clid.getText()) || toolUtil.isEmpty(clname.getText()) ||
                    toolUtil.isEmpty(clage.getText()) || toolUtil.isEmpty(clplace.getText()) ||
                    toolUtil.isEmpty(clnum.getText())) {
                JOptionPane.showMessageDialog(null, "不能为空");
                return;
            }

            try {
                // 要求client年龄为integer类型
                int clientId = Integer.parseInt(clid.getText());
                if (IDDao.isStaffIdExists(clientId)) {
                    JOptionPane.showMessageDialog(null, "客户ID已存在!请输入其他ID", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                client.setClId(clientId);
                client.setClName(clname.getText());
                client.setClSex(String.valueOf(clsex.getSelectedItem()));

                try {
                    int clientAge = Integer.parseInt(clage.getText());
                    client.setClAge(clientAge);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "年龄必须为整数");
                    ex.printStackTrace();
                    return;  // 如果不是则退出
                }

                client.setClPlace(clplace.getText());
                client.setClNum(clnum.getText());
                client.setClRecord(clrecord.getText());

                ClientDao.insert(client);

                MessageDialog d = new MessageDialog(main, "保存成功");
                d.setVisible(true);

                this.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID必须为整数");
                ex.printStackTrace();
            } catch (Exception e1) {
                MessageDialog d = new MessageDialog(main, "保存失败");
                d.setVisible(true);
                e1.printStackTrace();
            }
        } else {
            this.dispose();
        }
    }

}
