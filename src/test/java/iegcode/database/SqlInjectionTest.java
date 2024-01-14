package iegcode.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInjectionTest {

    @Test
    void testSqlInjection() throws SQLException {
        Connection connection = ConnectioUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String username = "admin' #'"; // tidak aman
        String passwrod = "salah";

        String sql = "SELECT * FROM admin WHERE username = '" + username +
                "' AND PASSWORD = '" + passwrod + "'";

        System.out.println(sql);
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()){
            // sukses login
            System.out.println("Sukses Login : " + resultSet.getString("username"));
        }else{
            // Gagal Login
            System.out.println("Gagal Login");
        }

        while (resultSet.next()){
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");

            System.out.println(
                    String.join(" , ", id, name, email)
            );
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
