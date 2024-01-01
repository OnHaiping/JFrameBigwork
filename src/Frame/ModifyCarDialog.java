package Frame;

import Bean.Car;
import MysqlMethod.CarDao;
import Tools.GUITools;
import Tools.toolUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyCarDialog extends JDialog implements ActionListener {
    private JTextField cid;
    private JTextField cname;
    private JTextField ccolour;
    private JTextField cpro;
    private JTextField ctime;
    private JTextField cprice;
    private JTextField cbrand;
    private JComboBox ctype;
    private JFrame main;//对话框依附的窗体

    public ModifyCarDialog(JFrame jFrame, Car car) {
        super(jFrame);
        main = jFrame;
        this.setLayout(null);
        this.setSize(400, 500);
        GUITools.setCenter(this);
        this.setModal(true);
        this.setTitle("修改用户");

        //编号
        cid = new JTextField(10);
        //名字
        JLabel Jcname = new JLabel("名字：");
        Jcname.setBounds(70, 40, 70, 30);
        this.add(Jcname);
        cname = new JTextField(10);
        cname.setBounds(140, 40, 150, 30);
        this.add(cname);

        //颜色
        JLabel Jccolour = new JLabel("颜色：");
        Jccolour.setBounds(70, 70, 70, 30);
        this.add(Jccolour);
        ccolour = new JTextField(10);
        ccolour.setBounds(140, 70, 150, 30);
        this.add(ccolour);

        //厂家
        JLabel Jcpro = new JLabel("厂家：");
        Jcpro.setBounds(70, 100, 70, 30);
        this.add(Jcpro);
        cpro = new JTextField(10);
        cpro.setBounds(140, 100, 150, 30);
        this.add(cpro);

        //生产时间
        JLabel Jctime = new JLabel("生产时间：");
        Jctime.setBounds(70, 130, 70, 30);
        this.add(Jctime);
        ctime = new JTextField(10);
        ctime.setBounds(140, 130, 150, 30);
        this.add(ctime);

        //价格
        JLabel Jcprice = new JLabel("价格：");
        Jcprice.setBounds(70, 160, 70, 30);
        this.add(Jcprice);
        cprice = new JTextField(10);
        cprice.setBounds(140, 160, 150, 30);
        this.add(cprice);

        //品牌
        JLabel Jcbrand = new JLabel("品牌：");
        Jcbrand.setBounds(70, 190, 70, 30);
        this.add(Jcbrand);
        cbrand = new JTextField(10);
        cbrand.setBounds(140, 190, 150, 30);
        this.add(cbrand);

        //类型
        JLabel Jctype = new JLabel("类型：");
        Jctype.setBounds(70, 220, 70, 30);
        this.add(Jctype);
        ctype = new JComboBox(new String[]{"大型车","中型车","小型车"});
        ctype.setBounds(140, 220, 150, 30);
        this.add(ctype);

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
        cid.setText(String.valueOf(car.getcId()));
        cname.setText(car.getcName());
        ccolour.setText(car.getcColour());
        cpro.setText(car.getcPro());
        ctime.setText(car.getcTime());
        cprice.setText(car.getcPrice());
        cbrand.setText(car.getcBrand());
        ctype.setSelectedItem(car.getcType());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("提交")) {
            Car car = new Car();

            if (toolUtil.isEmpty(cid.getText()) || toolUtil.isEmpty(cname.getText()) ||
                    toolUtil.isEmpty(ccolour.getText()) || toolUtil.isEmpty(ctime.getText()) ||
                    toolUtil.isEmpty(cpro.getText()) || toolUtil.isEmpty(cprice.getText()) ||
                    toolUtil.isEmpty(cbrand.getText())) {
                JOptionPane.showMessageDialog(null, "所有字段都不能为空");
                return;
            }
            try {
                // 验证 Cid 为integer类型
                int carId = Integer.parseInt(cid.getText());
                car.setcId(carId);
                car.setcName(cname.getText());
                car.setcColour(ccolour.getText());
                car.setcPro(cpro.getText());
                car.setcTime(ctime.getText());
                car.setcPrice(cprice.getText());
                car.setcBrand(cbrand.getText());
                car.setcType(String.valueOf(ctype.getSelectedItem()));

                CarDao.update(car);
                //提示保存成功
                MessageDialog d = new MessageDialog(main, "修改成功");
                d.setVisible(true);
                //关闭对话框
                this.dispose();
            } catch (Exception ex) {
                //提示保存失败
                MessageDialog d = new MessageDialog(main, "修改失败");
                d.setVisible(true);
                ex.printStackTrace();
            }
        } else {
            this.dispose();
        }
    }
}
