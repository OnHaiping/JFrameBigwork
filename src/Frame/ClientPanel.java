package Frame;

import Bean.Client;
import Tools.GUITools;
import MysqlMethod.ClientDao;
import Tools.toolUtil;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ClientPanel extends JPanel implements ActionListener {
    JTextField testclId;
    JButton buttonQuery, buttonAdd, buttonDelete, buttonUpdate;
    JFrame mainFrame;
    JTable table;//表格体现
    AddClientDialog addClientDialog;


    public ClientPanel() {
        mainFrame = (JFrame) this.getParent();
        this.setLayout(null);//设置空布局
        this.setPreferredSize(new Dimension(860, 480));

        GUITools.setCenter(this);

        JLabel clid = new JLabel("编号：");
        clid.setBounds(50, 20, 80, 30);
        clid.setFont(new Font("宋体", Font.BOLD, 16));

        testclId = new JTextField(14);
        testclId.setBounds(100, 20, 100, 25);

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

        this.add(clid);
        this.add(testclId);
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
        TableModel model = new DefaultTableModel(ClientDao.list2Array(ClientDao.loadClient()), ClientDao.titles);
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
            String sid = testclId.getText().trim();
            if(toolUtil.isEmpty(sid)){
                DefaultTableModel tableModel = new DefaultTableModel(ClientDao.list2Array(ClientDao.loadClient()), ClientDao.titles);
                table.setModel(tableModel);
            } else {
                DefaultTableModel tableModel = new DefaultTableModel(ClientDao.list2Array(ClientDao.selectClient(sid)), ClientDao.titles);
                table.setModel(tableModel);
            }
        }
        //添加按钮事件
        if (e.getSource() == buttonAdd) {
            //出现添加数据对话框
            addClientDialog = new AddClientDialog(mainFrame);
            addClientDialog.setVisible(true);
            //表格加载数据
            DefaultTableModel tableModel = new DefaultTableModel(ClientDao.list2Array(ClientDao.loadClient()), ClientDao.titles);
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
                String clid = String.valueOf(table.getValueAt(row, 0));
                String clname = String.valueOf(table.getValueAt(row, 1));
                String clsex = String.valueOf(table.getValueAt(row, 2));
                String clage = String.valueOf(table.getValueAt(row, 3));
                String clplace = String.valueOf(table.getValueAt(row, 4));
                String clnum = String.valueOf(table.getValueAt(row, 5));
                String clrecord = String.valueOf(table.getValueAt(row, 6));

                Client client = new Client();
                client.setClId(Integer.parseInt(clid));
                client.setClName(clname);
                client.setClSex(clsex);
                client.setClAge(Integer.parseInt(clage));
                client.setClPlace(clplace);
                client.setClNum(clnum);
                client.setClRecord(clrecord);

                ModifyClientDialog d = new ModifyClientDialog(mainFrame, client);
                d.setVisible(true);
                //加载数据
                TableModel model = new DefaultTableModel(ClientDao.list2Array(ClientDao.loadClient()), ClientDao.titles);
                table.setModel(model);
            }
        }
        //删除按钮事件
        if (e.getSource() == buttonDelete) {
            //获取表格中选中的数据行
            int row = table.getSelectedRow();
            if (row == -1) {
                MessageDialog dialog = new MessageDialog(mainFrame, "请选择要删除的数据！");
                dialog.setVisible(true);
            } else {
                //删除
                String clid = String.valueOf(table.getValueAt(row, 0));

                int dialogResult = JOptionPane.showConfirmDialog(this,"确认删除选定数据？", "确认", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_NO_OPTION) {
                    try {
                        //根据编号删除数据
                        ClientDao.delete(clid);
                        //加载数据
                        TableModel model = new DefaultTableModel(ClientDao.list2Array(ClientDao.loadClient()), ClientDao.titles);
                        table.setModel(model);
                    } catch (SQLException e1) {
                        MessageDialog dialog = new MessageDialog(mainFrame, "删除异常");
                    }
                }
            }
        }
    }
}