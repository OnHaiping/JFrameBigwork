package Frame;

import Bean.Staff;
import MysqlMethod.IDDao;
import MysqlMethod.StaffDao;
import Tools.GUITools;
import Tools.toolUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStaffDialog extends JDialog implements ActionListener {
    private JTextField sid;
    private JTextField sname;
    private JComboBox ssex;
    private JTextField spwd;
    private JTextField sedu;
    private JTextField splace;
    private JFrame main;//对话框依附的窗体

    public AddStaffDialog(JFrame jFrame) {
        super(jFrame);
        main = jFrame;
        this.setLayout(null);
        this.setSize(400, 500);
        GUITools.setCenter(this);
        this.setModal(true);
        this.setTitle("添加用户");

        //编号
        JLabel Jsid = new JLabel("编号：");
        Jsid.setBounds(70, 10, 70, 30);
        this.add(Jsid);
        sid = new JTextField(10);
        sid.setBounds(140, 10, 150, 30);
        this.add(sid);

        //名字
        JLabel Jsname = new JLabel("名字：");
        Jsname.setBounds(70, 40, 70, 30);
        this.add(Jsname);
        sname = new JTextField(10);
        sname.setBounds(140, 40, 150, 30);
        this.add(sname);

        //性别
        JLabel Jssex = new JLabel("性别：");
        Jssex.setBounds(70, 70, 70, 30);
        this.add(Jssex);
        ssex = new JComboBox(new String[]{"男", "女"});
        ssex.setBounds(140, 70, 150, 30);
        this.add(ssex);

        //年龄
        JLabel Jsage = new JLabel("密码：");
        Jsage.setBounds(70, 100, 70, 30);
        this.add(Jsage);
        spwd = new JTextField(10);
        spwd.setBounds(140, 100, 150, 30);
        this.add(spwd);

        //学历
        JLabel Jsedu = new JLabel("学历：");
        Jsedu.setBounds(70, 130, 70, 30);
        this.add(Jsedu);
        sedu = new JTextField(10);
        sedu.setBounds(140, 130, 150, 30);
        this.add(sedu);

        //地址
        JLabel Jsplace = new JLabel("地址：");
        Jsplace.setBounds(70, 160, 70, 30);
        this.add(Jsplace);
        splace = new JTextField(10);
        splace.setBounds(140, 160, 150, 30);
        this.add(splace);

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
            Staff staff = new Staff();
            if (toolUtil.isEmpty(sid.getText()) || toolUtil.isEmpty(sname.getText()) ||
                    toolUtil.isEmpty(splace.getText()) || toolUtil.isEmpty(sedu.getText()) ||
                    toolUtil.isEmpty(spwd.getText())) {
                JOptionPane.showMessageDialog(null, "不能为空");
                return;
            }
            try {
                int staffId = Integer.parseInt(sid.getText());
                // 判断用户是否存在
                if (IDDao.isStaffIdExists(staffId)) {
                    JOptionPane.showMessageDialog(null, "用户ID已存在!请输入其他ID", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                staff.setsId(staffId);
                staff.setsName(sname.getText());
                staff.setsSex(String.valueOf(ssex.getSelectedItem()));
                staff.setsPlace((splace.getText()));
                staff.setsEdu((sedu.getText()));
                staff.setsPwd(spwd.getText());

                StaffDao.insert(staff);

                MessageDialog d = new MessageDialog(main, "保存成功");
                d.setVisible(true);

                this.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "编号必须为整数", "错误", JOptionPane.ERROR_MESSAGE);
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