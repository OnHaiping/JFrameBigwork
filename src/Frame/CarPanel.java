package Frame;

import Bean.Car;
import Tools.GUITools;
import MysqlMethod.CarDao;
import Tools.toolUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CarPanel extends JPanel implements ActionListener {
    JTextField testSid;
    JButton buttonQuery, buttonAdd, buttonDelete, buttonUpdate;
    JFrame mainFrame;
    JTable table;//表格体现
    AddCarDialog addCarDialog;

    public CarPanel() {
        mainFrame = (JFrame) this.getParent();
        this.setLayout(null);//设置空布局
        this.setPreferredSize(new Dimension(860, 480));

        GUITools.setCenter(this);

        JLabel cid = new JLabel("编号：");
        cid.setBounds(50, 20, 80, 30);
        cid.setFont(new Font("宋体", Font.BOLD, 16));

        testSid = new JTextField(14);
        testSid.setBounds(100, 20, 100, 25);

        buttonQuery = new JButton("查询");
        buttonAdd = new JButton("添加");
        buttonDelete = new JButton("删除");
        buttonUpdate = new JButton("修改");

        buttonQuery.setBounds(220, 20, 80, 25);
        buttonAdd.setBounds(500, 20, 80, 25);
        buttonDelete.setBounds(600, 20, 80, 25);
        buttonUpdate.setBounds(700, 20, 80, 25);

        buttonQuery.addActionListener(this);
        buttonAdd.addActionListener(this);
        buttonDelete.addActionListener(this);
        buttonUpdate.addActionListener(this);

        this.add(cid);
        this.add(testSid);
        this.add(buttonQuery);
        this.add(buttonAdd);
        this.add(buttonDelete);
        this.add(buttonUpdate);

        //数据表格
        table = new JTable();
        table.getTableHeader().setPreferredSize(new Dimension(1, 30));
        table.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
        table.getTableHeader().setBackground(new Color(223, 241, 255));
        table.setFont(new Font("宋体", Font.PLAIN, 16));//设置表中文字大小
        table.setRowHeight(30);//设置表中行高
//        table.setEnabled(false);  这个用了之后有bug
        //表格模型
        TableModel model = new DefaultTableModel(CarDao.list2Array(CarDao.loadCar()), CarDao.titles);
        table.setModel(model);

        //滚动面板
        JScrollPane pane = new JScrollPane(table);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//设置水平滚动条
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//设置垂直滚动条
        pane.setBounds(20, 50, 800, 380);
        this.add(pane);//将滚动面板添加到当前面板上

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //查询按钮事件
        if (e.getSource() == buttonQuery) {
            String sid = testSid.getText().trim();
            if(toolUtil.isEmpty(sid)){
//                JOptionPane.showMessageDialog(null,"不能为空！");
                DefaultTableModel tableModel = new DefaultTableModel(CarDao.list2Array(CarDao.loadCar()), CarDao.titles);
                table.setModel(tableModel);
            } else {
                DefaultTableModel tableModel = new DefaultTableModel(CarDao.list2Array(CarDao.selectCar(sid)), CarDao.titles);
                table.setModel(tableModel);
            }
        }
        //添加按钮事件
        if (e.getSource() == buttonAdd) {
            //出现添加数据对话框
            addCarDialog = new AddCarDialog(mainFrame);
            addCarDialog.setVisible(true);
            //加载表格数据
            DefaultTableModel tableModel = new DefaultTableModel(CarDao.list2Array(CarDao.loadCar()), CarDao.titles);
            table.setModel(tableModel);
        }
        //修改按钮事件
        if (e.getSource() == buttonUpdate) {
            // 判断是否选中
            int row = table.getSelectedRow();
            if (row == -1) {
                MessageDialog dialog = new MessageDialog(mainFrame,
                        "请选择要修改的数据！");
                dialog.setVisible(true);
            } else {//修改
                String cid = String.valueOf(table.getValueAt(row, 0));
                String cname = String.valueOf(table.getValueAt(row, 1));
                String ccolour = String.valueOf(table.getValueAt(row, 2));
                String cpro = String.valueOf(table.getValueAt(row, 3));
                String ctime = String.valueOf(table.getValueAt(row, 4));
                String cprice = String.valueOf(table.getValueAt(row, 5));
                String cbrand = String.valueOf(table.getValueAt(row, 6));
                String ctype = String.valueOf(table.getValueAt(row, 7));

                Car car = new Car();
                car.setcId(Integer.parseInt(cid));
                car.setcName(cname);
                car.setcColour(ccolour);
                car.setcPro(cpro);
                car.setcTime(ctime);
                car.setcPrice(cprice);
                car.setcBrand(cbrand);
                car.setcType(ctype);

                ModifyCarDialog d = new ModifyCarDialog(mainFrame, car);
                d.setVisible(true);
                //加载数据
                TableModel model = new DefaultTableModel(CarDao.list2Array(CarDao.loadCar()), CarDao.titles);
                table.setModel(model);
            }
        }

        //删除按钮事件
        if (e.getSource() == buttonDelete) {
            //获取表格中选中的数据行
            int row = table.getSelectedRow();
            if (row == -1) {
                MessageDialog dialog = new MessageDialog(mainFrame,
                        "请选择要删除的数据！");
                dialog.setVisible(true);
            } else {
                //删除
                String cid = String.valueOf(table.getValueAt(row, 0));
                //根据编号删除数据
                int dialogResult = JOptionPane.showConfirmDialog(this, "确认删除选定数据？", "确认", JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    try {
                        CarDao.delete(cid);
                        //加载数据
                        TableModel model = new DefaultTableModel(CarDao.list2Array(CarDao.loadCar()), CarDao.titles);
                        table.setModel(model);
                    } catch (SQLException e1) {
                        MessageDialog dialog = new MessageDialog(mainFrame, "删除异常");
                    }
                }
            }
        }
    }
}