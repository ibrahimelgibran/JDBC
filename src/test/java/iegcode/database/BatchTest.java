package iegcode.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchTest {

    @Test
    void testStatement() throws SQLException {
        Connection connection = ConnectioUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = "INSERT INTO comments(email, comment) VALUES ('ibrahim@test.com', 'hi')";

        for (int i = 0; i < 20; i++) {
            statement.addBatch(sql);
        }

        statement.executeBatch();

        statement.close();
        connection.close();
    }

    @Test
    void testPreparedStatment() throws SQLException {
        Connection connection = ConnectioUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES (? , ?)";
        PreparedStatement preparedStatement =  connection.prepareStatement(sql);

        for (int i = 0; i < 20; i++) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, "gibran@test.com");
            preparedStatement.setString(2, "hi");
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();

        preparedStatement.close();
        connection.close();
    }

}
