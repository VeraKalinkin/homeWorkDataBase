import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {

    private Connection connection;

    String TableCreateQuery = "CREATE TABLE IF NOT EXISTS users " +
            "(id LONG PRIMARY KEY, name VARCHAR(30), email VARCHAR(30))";


    Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager
                    .getConnection("jdbc:h2:mem:");
            connection.createStatement().execute(TableCreateQuery);
        }
        return connection;
    }
}
