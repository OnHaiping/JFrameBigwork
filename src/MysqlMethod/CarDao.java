package MysqlMethod;

import Bean.Car;
import Mysql.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDao {
    //表头信息
    public static String[] titles = {"cid", "cname", "ccolour", "cpro", "ctime", "cprice", "cbrand", "ctype"};
    //汽车数据
    public static List<Car> data = new ArrayList<>();

    //将汽车list转换成字符串二维数组
    public static String[][] list2Array(List<Car> list) {
        String[][] data = new String[list.size()][8];
        for (int i = 0; i < list.size(); i++) {
            Car c = list.get(i);
            data[i][0] = String.valueOf(c.getcId());
            data[i][1] = c.getcName();
            data[i][2] = c.getcColour();
            data[i][3] = c.getcPro();
            data[i][4] = c.getcTime();
            data[i][5] = c.getcPrice();
            data[i][6] = c.getcBrand();
            data[i][7] = c.getcType();
        }
        return data;
    }

    //加载所有汽车数据
    public static List<Car> loadCar() {
        List<Car> data = new ArrayList<>();
        String sql = "select * from car ";
        try {
            Connection cn = DB.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int cid = rs.getInt("cid");
                String cname = rs.getString("cname");
                String ccolour = rs.getString("ccolour");
                String cpro = rs.getString("cpro");
                String ctime = rs.getString("ctime");
                String cprice = rs.getString("cprice");
                String cbrand = rs.getString("cbrand");
                String ctype = rs.getString("ctype");

                Car c = new Car();
                c.setcId(cid);
                c.setcName(cname);
                c.setcColour(ccolour);
                c.setcPro(cpro);
                c.setcTime(ctime);
                c.setcPrice(cprice);
                c.setcBrand(cbrand);
                c.setcType(ctype);
                data.add(c);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    //根据条件查询
    public static List<Car> selectCar(String cId) {
        List<Car> data = new ArrayList<>();
        String sql = "select * from car where cid='" + cId + "'";
        try {
            Connection cn = DB.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int cid = rs.getInt("cid");
                String cname = rs.getString("cname");
                String ccolour = rs.getString("ccolour");
                String cpro = rs.getString("cpro");
                String ctime = rs.getString("ctime");
                String cprice = rs.getString("cprice");
                String cbrand = rs.getString("cbrand");
                String ctype = rs.getString("ctype");

                Car c = new Car();
                c.setcId(cid);
                c.setcName(cname);
                c.setcColour(ccolour);
                c.setcPro(cpro);
                c.setcTime(ctime);
                c.setcPrice(cprice);
                c.setcBrand(cbrand);
                c.setcType(ctype);
                data.add(c);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    //插入汽车信息
    public static void insert(Car car) throws SQLException {
        String sql = "insert into car(cid,cname,ccolour,cpro,ctime,cprice,cbrand,ctype)"
                + "values('" + car.getcId() + "',"
                + "'" + car.getcName() + "',"
                + "'" + car.getcColour() + "',"
                + "'" + car.getcPro() + "',"
                + "'" + car.getcTime() + "',"
                + "'" + car.getcPrice() + "',"
                + "'" + car.getcBrand() + "',"
                + "'" + car.getcType() + "')";

        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    //根据Id删除汽车信息
    public static void delete(String cId) throws SQLException {
        String sql = "delete from car where cid='" + cId + "'";
        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    //修改数据
    public static void update(Car c) throws SQLException {
        String sql = "update car set "
                + "cname=" + "'" + c.getcName() + "',"
                + "ccolour=" + "'" + c.getcColour() + "',"
                + "cpro=" + "'" + c.getcPro() + "',"
                + "ctime=" + "'" + c.getcTime() + "',"
                + "cprice=" + "'" + c.getcPrice() + "',"
                + "cbrand=" + "'" + c.getcBrand() + "',"
                + "ctype=" + "'" + c.getcType() + "'"
                + "where cid='" + c.getcId() + "';";
        Connection connection = DB.getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);
    }


}
