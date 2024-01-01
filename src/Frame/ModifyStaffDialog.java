package Frame;

import MysqlMethod.StaffDao;
import Tools.GUITools;
import Bean.Staff;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyStaffDialog extends JDialog implements ActionListener {
    private JTextField sid;
    private JTextField sname;
    private JComboBox ssex;
    private JTextField sage;
    private JTextField sedu;
    private JTextField splace;
    private JFrame main;//对话框依附的窗体

    public ModifyStaffDialog(JFrame jFrame, Staff staff) {
        super(jFrame);
        main = jFrame;
        this.setLayout(null);
        this.setSize(400, 500);
        GUITools.setCenter(this);
        this.setModal(true);
        this.setTitle("修改员工信息");

        //编号的设置
        sid = new JTextField();

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
        ssex = new JComboBox(new String[]{"男","女"});
        ssex.setBounds(140, 70, 150, 30);
        this.add(ssex);

        //年龄
        JLabel Jsage = new JLabel("密码：");
        Jsage.setBounds(70, 100, 70, 30);
        this.add(Jsage);
        sage = new JTextField(10);
        sage.setBounds(140, 100, 150, 30);
        this.add(sage);

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

        //赋值
        sid.setText(String.valueOf(staff.getsId()));
        sname.setText(staff.getsName());
        ssex.setSelectedItem(staff.getsSex());
        sage.setText(staff.getsPwd());
        sedu.setText(staff.getsEdu());
        splace.setText(staff.getsPlace());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String common = e.getActionCommand();  //文本
        if (common.equals("提交")) {   //常用，左右是否相等
            Staff staff = new Staff();
            staff.setsId(Integer.parseInt(sid.getText()));
            staff.setsName(sname.getText());
            staff.setsSex(String.valueOf(ssex.getSelectedItem()));
            staff.setsPwd(sage.getText());
            staff.setsEdu(sedu.getText());
            staff.setsPlace(splace.getText());

            try {
                StaffDao.update(staff);
                //提示保存成功
                MessageDialog d = new MessageDialog(main, "修改成功");
                d.setVisible(true);
                //关闭对话框
                this.dispose();
            } catch (Exception e1) {
                //提示保存失败
                MessageDialog d = new MessageDialog(main, "修改失败");
                d.setVisible(true);
                e1.printStackTrace();
            }

        } else {
            this.dispose();
        }
    }
}

