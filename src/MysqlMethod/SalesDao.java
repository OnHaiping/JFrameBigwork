package MysqlMethod;


import Bean.Sales;
import Mysql.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SalesDao {
    //表头信息
    public static String[] titles = {"saleid", "salename", "saleclient", "saletime", "salestaff"};
    //销售数据
    public static List<Sales> data = new ArrayList<>();

    //将销售list转换成字符串二维数组
    public static String[][] list2Array(List<Sales> list) {
        String[][] data = new String[list.size()][5];
        for (int i = 0; i < list.size(); i++) {
            Sales s = list.get(i);
            data[i][0] = String.valueOf(s.getSaleId());
            data[i][1] = s.getSaleName();
            data[i][2] = s.getSaleClient();
            data[i][3] = s.getSaleTime();
            data[i][4] = s.getSaleStaff();

        }
        return data;
    }

    //加载所有销售数据
    public static List<Sales> loadsale() {
        List<Sales> data = new ArrayList<>();
        String sql = "select * from sale ";
        try {
            Connection cn = DB.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int saleid = rs.getInt("saleid");
                String salename = rs.getString("salename");
                String saleclient = rs.getString("saleclient");
                String saletime = rs.getString("saletime");
                String salestaff = rs.getString("salestaff");

                Sales s = new Sales();

                s.setSaleId(saleid);
                s.setSaleName(salename);
                s.setSaleClient(saleclient);
                s.setSaleTime(saletime);
                s.setSaleStaff(salestaff);
                data.add(s);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    //根据条件查询
    public static List<Sales> selectsale(String saleId) {
        List<Sales> data = new ArrayList<>();
        String sql = "select * from sale where saleid='" + saleId + "'";
        try {
            Connection cn = DB.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int saleid = rs.getInt("saleid");
                String salename = rs.getString("salename");
                String saleclient = rs.getString("saleclient");
                String saletime = rs.getString("saletime");
                String salestaff = rs.getString("salestaff");

                Sales s = new Sales();

                s.setSaleId(saleid);
                s.setSaleName(salename);
                s.setSaleClient(saleclient);
                s.setSaleTime(saletime);
                s.setSaleStaff(salestaff);
                data.add(s);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    //插入销售信息
    public static void insert(Sales sale) throws SQLException {
        String sql = "insert into sale(saleid,salename,saleclient,saletime,salestaff)"
                + "values('" + sale.getSaleId() + "',"
                + "'" + sale.getSaleName() + "',"
                + "'" + sale.getSaleClient() + "',"
                + "'" + sale.getSaleTime() + "',"
                + "'" + sale.getSaleStaff() + "')";

        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    //根据Id删除销售信息
    public static void delete(String saleId) throws SQLException {
        String sql = "delete from sale where saleid='" + saleId + "'";
        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    //修改数据
    public static void update(Sales s) throws SQLException {
        String sql = "update sale set "
                + "salename=" + "'" + s.getSaleName() + "',"
                + "saleclient=" + "'" + s.getSaleClient() + "',"
                + "saletime=" + "'" + s.getSaleTime() + "',"
                + "salestaff=" + "'" + s.getSaleStaff()
                + "where saleid='" + s.getSaleId() + "'";
        Connection connection = DB.getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);
    }
}

