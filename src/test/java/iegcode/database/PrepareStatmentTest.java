package iegcode.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrepareStatmentTest {
    @Test
    void testPrepareStatment() throws SQLException {
        Connection connection = ConnectioUtil.getDataSource().getConnection();
        String username = "admin' : #";
        String password = "admin";

        String sql = "SELECT * FROM  admin WHERE username = ? AND password = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            // sukses login
            System.out.println("Sukses login : " + resultSet.getString("username"));
        } else {
            // gagal login
            System.out.println("Gagal Login");

        }

        preparedStatement.close();
        connection.close();
    }
}
