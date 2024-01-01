package MysqlMethod;

import Bean.Client;
import Mysql.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    //表头信息
    public static String[] titles = {"clid", "clname", "clsex", "clage", "clplace", "clnum", "clrecord"};
    //客户数据
    public static List<Client> data = new ArrayList<>();

    //将客户list转换成字符串二维数组
    public static String[][] list2Array(List<Client> list) {
        String[][] data = new String[list.size()][7];
        for (int i = 0; i < list.size(); i++) {
            Client c = list.get(i);
            data[i][0] = String.valueOf(c.getClId());
            data[i][1] = c.getClName();
            data[i][2] = c.getClSex();
            data[i][3] = String.valueOf(c.getClAge());
            data[i][4] = c.getClPlace();
            data[i][5] = c.getClNum();
            data[i][6] = c.getClRecord();
        }
        return data;
    }

    //加载所有客户数据
    public static List<Client> loadClient() {
        List<Client> data = new ArrayList<>();
        String sql = "select * from client ";
        try {
            Connection cn = DB.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int clid = rs.getInt("clid");
                String clname = rs.getString("clname");
                String clsex = rs.getString("clsex");
                int clage = rs.getInt("clage");
                String clplace = rs.getString("clplace");
                String clnum = rs.getString("clnum");
                String clrecord = rs.getString("clrecord");

                Client c = new Client();
                c.setClId(clid);
                c.setClName(clname);
                c.setClSex(clsex);
                c.setClAge(clage);
                c.setClPlace(clplace);
                c.setClNum(clnum);
                c.setClRecord(clrecord);
                data.add(c);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    //根据条件查询
    public static List<Client> selectClient(String clId) {
        List<Client> data = new ArrayList<>();
        String sql = "select * from client where clid='" + clId + "'";
        try {
            Connection cn = DB.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int clid = rs.getInt("clid");
                String clname = rs.getString("clname");
                String clsex = rs.getString("clsex");
                int clage = rs.getInt("clage");
                String clplace = rs.getString("clplace");
                String clnum = rs.getString("clnum");
                String clrecord = rs.getString("clrecord");

                Client c = new Client();
                c.setClId(clid);
                c.setClName(clname);
                c.setClSex(clsex);
                c.setClAge(clage);
                c.setClPlace(clplace);
                c.setClNum(clnum);
                c.setClRecord(clrecord);
                data.add(c);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    //插入客户信息
    public static void insert(Client client) throws SQLException {
        String sql = "insert into client(clid,clname,clsex,clage,clplace,clnum,clrecord)"
                + "values('" + client.getClId() + "',"
                + "'" + client.getClName() + "',"
                + "'" + client.getClSex() + "',"
                + "'" + client.getClAge() + "',"
                + "'" + client.getClPlace() + "',"
                + "'" + client.getClNum() + "',"
                + "'" + client.getClRecord() + "')";

        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }


    //根据Id删除客户信息
    public static void delete(String clId) throws SQLException {
        String sql = "delete from client where clid='" + clId + "'";
        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    //修改数据
    public static void update(Client c) throws SQLException {
        String sql = "update client set "
                + "clname='" + c.getClName() + "',"
                + "clsex='" + c.getClSex() + "',"
                + "clage='" + c.getClAge() + "',"
                + "clplace='" + c.getClPlace() + "',"
                + "clnum='" + c.getClNum() + "',"
                + "clrecord='" + c.getClRecord() + "' "
                + "where clid='" + c.getClId() + "';";
        Connection connection = DB.getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);
    }


}

