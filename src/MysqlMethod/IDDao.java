package MysqlMethod;

import Mysql.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IDDao {
    public static boolean isStaffIdExists(int staffId) {
        String sql = "SELECT COUNT(*) FROM staff WHERE sid = ?";
        try (Connection connection = DB.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, staffId);
            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception or rethrow as needed
            return false;
        }
    }
}
