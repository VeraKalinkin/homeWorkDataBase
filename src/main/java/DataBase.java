import java.sql.*;
import java.util.ArrayList;

public class DataBase {

    static GetConnection getConnection = new GetConnection();

    public DataBase() {
    }

    public boolean addUser(Long id, String name, String email) {
        try {
            Connection connection = getConnection.getConnection();
            String query = "INSERT INTO users(id, name, email) values(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private int insertUpdateDrop(String query) {
        try {
            Connection connection = getConnection.getConnection();
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    ResultSet select(String query) {
        try (Connection connection = getConnection.getConnection();) {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User findById(Long id) {
        try (Connection connection = getConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            return createUser(resultSet);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new User(0L, "Name", "email");
    }

    public ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM users";
            try (ResultSet resultSet = select(query)) {

                while (resultSet.next()) {
                    User user = new User(resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"));
                    users.add(user);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public boolean deleteUserById(Long id) {
        try (Connection connection = getConnection.getConnection();) {
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private User createUser(ResultSet resultSet) {
        try {
            long id = 0L;
            String name = "name";
            String email = "email";
            while (resultSet.next()) {
                id = resultSet.getLong(1);
                name = resultSet.getString(2);
                email = resultSet.getString(3);
            }
            return new User(id, name, email);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new User(0L, "Name", "email");
    }

    private void printResult(ResultSet resultSet) {
        try (resultSet) {
            while (resultSet.next()) {
                System.out.print(resultSet.getLong(1));
                System.out.print(' ' + resultSet.getString(2) + ' ');
                System.out.print(resultSet.getString(3));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
