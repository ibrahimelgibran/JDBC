package iegcode.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class TransactionTest {

    @Test
    void testCommit() throws SQLException {
        Connection connection = ConnectioUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO comments(email, comment) VALUES (? , ?)";


        for (int i = 0; i < 20; i++) {
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
            preparedStatement.setString(1, "gibran@test.com");
            preparedStatement.setString(2, "hi");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        connection.commit();
        connection.close();
    }

    @Test
    void testCommitRollBack() throws SQLException {
        Connection connection = ConnectioUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO comments(email, comment) VALUES (? , ?)";


        for (int i = 0; i < 20; i++) {
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
            preparedStatement.setString(1, "gibran@test.com");
            preparedStatement.setString(2, "hi");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        // proeses dibatalkan
        connection.rollback();
        connection.close();
    }
}
