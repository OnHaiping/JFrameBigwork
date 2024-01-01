package MysqlMethod;

import Bean.Staff;
import Mysql.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {
    //表头信息
    public static String[] titles = {"sid", "sname", "ssex", "spwd", "sedu", "splace"};
    //汽车数据
    public static List<Staff> data = new ArrayList<>();

    //将员工list转换成字符串二维数组
    public static String[][] list2Array(List<Staff> list) {
        String[][] data = new String[list.size()][6];
        for (int i = 0; i < list.size(); i++) {
            Staff s = list.get(i);
            data[i][0] = String.valueOf(s.getsId());
            data[i][1] = s.getsName();
            data[i][2] = s.getsSex();
            data[i][3] = s.getsPwd();
            data[i][4] = s.getsEdu();
            data[i][5] = s.getsPlace();

        }
        return data;
    }

    //加载所有员工数据
    public static List<Staff> loadstaff() {
        List<Staff> data = new ArrayList<>();
        String sql = "select * from staff ";
        try {
            Connection cn = DB.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int sid = rs.getInt("sid");
                String sname = rs.getString("sname");
                String ssex = rs.getString("ssex");
                String spwd = rs.getString("spwd");
                String sedu = rs.getString("sedu");
                String splace = rs.getString("splace");
                Staff s = new Staff();

                s.setsId(sid);
                s.setsName(sname);
                s.setsSex(ssex);
                s.setsPwd(spwd);
                s.setsEdu(sedu);
                s.setsPlace(splace);
                data.add(s);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    //根据条件查询
    public static List<Staff> selectstaff(String staffId) {
        List<Staff> data = new ArrayList<>();
        String sql = "select * from staff where sid='" + staffId + "'";
        try {
            Connection cn = DB.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int sid = rs.getInt("sid");
                String sname = rs.getString("sname");
                String ssex = rs.getString("ssex");
                String spwd = rs.getString("spwd");
                String sedu = rs.getString("sedu");
                String splace = rs.getString("splace");

                Staff s = new Staff();

                s.setsId(sid);
                s.setsName(sname);
                s.setsSex(ssex);
                s.setsPwd(spwd);
                s.setsEdu(sedu);
                s.setsPlace(splace);
                data.add(s);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    //插入员工信息
    public static void insert(Staff staff) throws SQLException {
        String sql = "insert into staff(sid,sname,ssex,spwd,sedu,splace)"
                + "values('" + staff.getsId() + "',"
                + "'" + staff.getsName() + "',"
                + "'" + staff.getsSex() + "',"
                + "'" + staff.getsPwd() + "',"
                + "'" + staff.getsEdu() + "',"
                + "'" + staff.getsPlace() + "')";

        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    //根据Id查询汽车数据
    public static Staff select(String staffId) throws SQLException {
        String sql = "select * from staff where sid ='" + staffId + "'";
        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        Staff staff = null;
        while (rs.next()) {
            int sid = rs.getInt("sid");
            String sname = rs.getString("sname");
            String ssex = rs.getString("ssex");
            String spwd = rs.getString("spwd");
            String sedu = rs.getString("sedu");
            String splace = rs.getString("splace");

            staff = new Staff(sid, sname, ssex, spwd, sedu, splace);
        }
        return staff;
    }

    //根据Id删除员工信息
    public static void delete(String staffId) throws SQLException {
        String sql = "delete from staff where sid='" + staffId + "'";
        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    //修改数据
    public static void update(Staff s) throws SQLException {
        String sql = "update staff set "
                + "sname=" + "'" + s.getsName() + "', "
                + "ssex=" + "'" + s.getsSex() + "', "
                + "spwd=" + "'" + s.getsPwd() + "', "
                + "sedu=" + "'" + s.getsEdu() + "', "
                + "splace=" + "'" + s.getsPlace() + "' "
                + "where sid='" + s.getsId() + "'";
        Connection connection = DB.getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        connection.close();
    }
    //登录
    public static boolean login(String sid, String spwd) {
        boolean login = false;
        //登录
        String sql = "select * from staff where sid='" + sid + "' and spwd='" + spwd + "'";
        try {
            Connection cn = DB.getConnection();
            Statement st= cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            login = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return login;
    }
}
