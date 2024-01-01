package Frame;


import Bean.Sales;
import MysqlMethod.SalesDao;
import Tools.GUITools;
import Tools.toolUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifySalesDialog extends JDialog implements ActionListener {
    private JTextField saleid;
    private JTextField salename;
    private JTextField saleclient;
    private JTextField saletime;
    private JTextField salestaff;
    private JFrame main;//对话框依附的窗体

    public ModifySalesDialog(JFrame jFrame, Sales sales) {
        super(jFrame);
        main = jFrame;
        this.setLayout(null);
        this.setSize(400, 500);
        GUITools.setCenter(this);
        this.setModal(true);
        this.setTitle("修改用户");

        //编号
        JLabel Jsaleid = new JLabel("编号：");
        Jsaleid.setBounds(70, 10, 70, 30);
        this.add(Jsaleid);
        saleid = new JTextField(10);
        saleid.setBounds(140, 10, 150, 30);
        this.add(saleid);

        //名字
        JLabel Jsalename = new JLabel("名字：");
        Jsalename.setBounds(70, 40, 70, 30);
        this.add(Jsalename);
        salename = new JTextField(10);
        salename.setBounds(140, 40, 150, 30);
        this.add(salename);

        //客户
        JLabel Jsalestaff = new JLabel("客户：");
        Jsalestaff.setBounds(70, 70, 70, 30);
        this.add(Jsalestaff);
        salestaff = new JTextField(10);
        salestaff.setBounds(140, 70, 150, 30);
        this.add(salestaff);

        //时间
        JLabel Jsaletime = new JLabel("时间：");
        Jsaletime.setBounds(70, 100, 70, 30);
        this.add(Jsaletime);
        saletime = new JTextField(10);
        saletime.setBounds(140, 100, 150, 30);
        this.add(saletime);

        //员工
        JLabel Jsaleclient = new JLabel("员工：");
        Jsaleclient.setBounds(70, 130, 70, 30);
        this.add(Jsaleclient);
        saleclient = new JTextField(10);
        saleclient.setBounds(140, 130, 150, 30);
        this.add(saleclient);

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
        saleid.setText(String.valueOf(sales.getSaleId()));
        salename.setText(sales.getSaleName());
        saleclient.setText(sales.getSaleClient());
        saletime.setText(sales.getSaleTime());
        salestaff.setText(sales.getSaleStaff());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String common = e.getActionCommand();  //文本
        if (common.equals("提交")) {   //常用，左右是否相等
            Sales sales = new Sales();
            if (toolUtil.isEmpty(saleid.getText()) || toolUtil.isEmpty(salename.getText()) || toolUtil.isEmpty(saleclient.getText()) ||
                    toolUtil.isEmpty(saletime.getText()) || toolUtil.isEmpty(salestaff.getText())) {
                JOptionPane.showMessageDialog(null, "不能为空");
                return;
            }
            try {
                int saleId = Integer.parseInt(saleid.getText());
                sales.setSaleId(saleId);
                sales.setSaleName(salename.getText());
                sales.setSaleClient(saleclient.getText());
                sales.setSaleTime(saletime.getText());
                sales.setSaleStaff(salestaff.getText());
                SalesDao.update(sales);
                //提示保存成功
                MessageDialog d = new MessageDialog(main, "修改成功");
                d.setVisible(true);
                //关闭对话框
                this.dispose();
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "编号必须为整数", "错误", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
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

