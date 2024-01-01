package Frame;

import Bean.Car;
import MysqlMethod.CarDao;
import MysqlMethod.IDDao;
import Tools.GUITools;
import Tools.toolUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddCarDialog extends JDialog implements ActionListener {
    private JTextField cid;
    private JTextField cname;
    private JTextField ccolour;
    private JTextField cpro;
    private JTextField ctime;
    private JTextField cprice;
    private JTextField cbrand;
    private JComboBox ctype;
    private JFrame main;//对话框依附的窗体

    public AddCarDialog(JFrame jFrame) {
        super(jFrame);
        main = jFrame;
        this.setLayout(null);
        this.setSize(400, 500);
        GUITools.setCenter(this);
        this.setModal(true);
        this.setTitle("添加车辆信息");

        //编号
        JLabel Jcid = new JLabel("编号：");
        Jcid.setBounds(70, 10, 70, 30);
        this.add(Jcid);
        cid = new JTextField(10);
        cid.setBounds(140, 10, 150, 30);
        this.add(cid);

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
        JLabel Jcpro = new JLabel("排量：");
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("提交")) {
            if (toolUtil.isEmpty(cid.getText())||toolUtil.isEmpty(cname.getText()) || toolUtil.isEmpty(ccolour.getText()) ||
                    toolUtil.isEmpty(ctime.getText()) || toolUtil.isEmpty(cpro.getText()) ||
                    toolUtil.isEmpty(cprice.getText()) || toolUtil.isEmpty(cbrand.getText())) {
                JOptionPane.showMessageDialog(null, "不能为空");
                return;
            }
            try {
                int carId = Integer.parseInt(cid.getText());
                // 验证CarID是否是integer类型
                if (IDDao.isStaffIdExists(carId)) {
                    JOptionPane.showMessageDialog(null, "车辆ID已存在!请输入其他ID", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (carId <= 0) {
                    throw new NumberFormatException();
                }
                Car car = new Car();
                car.setcId(carId);
                car.setcName(cname.getText());
                car.setcColour(ccolour.getText());
                car.setcPro(cpro.getText());
                car.setcTime(ctime.getText());
                car.setcPrice(cprice.getText());
                car.setcBrand(cbrand.getText());
                car.setcType(String.valueOf(ctype.getSelectedItem()));

                CarDao.insert(car);
                //提示保存成功
                MessageDialog d = new MessageDialog(main, "保存成功");
                d.setVisible(true);
                //关闭对话框
                this.dispose();
            } catch (NumberFormatException ex) {
                //提示保存失败
                MessageDialog d = new MessageDialog(main, "无效的编号！！！");
                d.setVisible(true);
            } catch (Exception ex) {
                //提示保存失败
                MessageDialog d = new MessageDialog(main, "保存失败");
                d.setVisible(true);
                ex.printStackTrace();
            }
        } else {
            this.dispose();
        }
    }
}

