package Frame;

import Bean.Staff;
import MysqlMethod.StaffDao;
import Tools.GUITools;
import Tools.toolUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class StaffPanel extends JPanel implements ActionListener {
    JTextField testSid;
    JButton buttonQuery, buttonAdd, buttonDelete, buttonUpdate;
    JFrame mainFrame;
    JTable table;//表格体现
    AddStaffDialog addstaffDialog;
    public StaffPanel() {
        mainFrame = (JFrame) this.getParent();
        this.setLayout(null);//设置空布局
        this.setPreferredSize(new Dimension(860, 480));

        GUITools.setCenter(this);

        JLabel sid = new JLabel("编号：");
        sid.setBounds(50, 20, 80, 30);
        sid.setFont(new Font("宋体", Font.BOLD, 16));

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

        this.add(sid);
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

        //表格模型
        TableModel model = new DefaultTableModel(StaffDao.list2Array(StaffDao.loadstaff()), StaffDao.titles);
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
                DefaultTableModel tableModel = new DefaultTableModel(StaffDao.list2Array(StaffDao.loadstaff()), StaffDao.titles);
                table.setModel(tableModel);
            } else {
                DefaultTableModel tableModel = new DefaultTableModel(StaffDao.list2Array(StaffDao.selectstaff(sid)), StaffDao.titles);
                table.setModel(tableModel);
            }

        }
        //添加按钮事件
        if (e.getSource() == buttonAdd) {
            //出现添加数据对话框
            addstaffDialog = new AddStaffDialog(mainFrame);
            addstaffDialog.setVisible(true);
            //表格加载数据
            DefaultTableModel tableModel = new DefaultTableModel(StaffDao.list2Array(StaffDao.loadstaff()), StaffDao.titles);
            table.setModel(tableModel);

        }
        //修改按钮事件
        if (e.getSource() == buttonUpdate) {
            int row = table.getSelectedRow();
            if (row == -1) {
                MessageDialog dialog = new MessageDialog(mainFrame,
                        "请选择要修改的数据！");
                dialog.setVisible(true);
            } else {//修改
                String sid = String.valueOf(table.getValueAt(row, 0));
                String sname = String.valueOf(table.getValueAt(row, 1));
                String ssex = String.valueOf(table.getValueAt(row, 2));
                String spwd = String.valueOf(table.getValueAt(row, 3));
                String sedu = String.valueOf(table.getValueAt(row, 4));
                String splace = String.valueOf(table.getValueAt(row, 5));

                Staff staff = new Staff();
                staff.setsId(Integer.parseInt(sid));
                staff.setsName(sname);
                staff.setsSex(ssex);
                staff.setsPwd(spwd);
                staff.setsEdu(sedu);
                staff.setsPlace(splace);

                ModifyStaffDialog d = new ModifyStaffDialog(mainFrame, staff);
                d.setVisible(true);
                //加载数据
                TableModel model = new DefaultTableModel(StaffDao.list2Array(StaffDao.loadstaff()), StaffDao.titles);
                table.setModel(model);
            }
        }
        //删除按钮事件
        if (e.getSource() == buttonDelete) {
            // 获取表格中选中的数据行
            int row = table.getSelectedRow();
            if (row == -1) {
                MessageDialog dialog = new MessageDialog(mainFrame, "请选择要删除的数据！");
                dialog.setVisible(true);
            } else {
                // 弹窗确认是否删除
                int dialogResult = JOptionPane.showConfirmDialog(null, "确定要删除这条数据吗？", "提示", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    // 删除
                    String sid = String.valueOf(table.getValueAt(row, 0));
                    // 根据编号删除数据
                    try {
                        StaffDao.delete(sid);
                        MessageDialog dialog = new MessageDialog(mainFrame, "删除成功");
                        dialog.setVisible(true);
                    } catch (SQLException e1) {
                        MessageDialog dialog = new MessageDialog(mainFrame, "删除异常");
                        dialog.setVisible(true);
                        e1.printStackTrace();
                    }
                    // 加载数据
                    TableModel model = new DefaultTableModel(StaffDao.list2Array(StaffDao.loadstaff()), StaffDao.titles);
                    table.setModel(model);
                }
            }
        }
    }
}