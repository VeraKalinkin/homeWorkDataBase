import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataBaseTest {

    @Test
    void shouldGetJdbcConnection() throws SQLException {
        DataBase dataBase = new DataBase();
        try(Connection connection = dataBase.getNewConnection()) {
            assert(connection.isValid(0));
        }
    }

    @Test
    void shouldCreateEmptyDataBase() throws SQLException {
        DataBase dataBase = new DataBase();
        String query = "SELECT * FROM users";
        dataBase.createTable();
        Assertions.assertFalse(dataBase.select(query).first());
    }

    @Test
    void shouldCatchInsertException(){
        DataBase dataBase = new DataBase();
        dataBase.createTable();
        dataBase.createTable();
    }

    @Test
    void shouldAddAndFindUser(){
        DataBase dataBase = new DataBase();
        dataBase.createTable();
        dataBase.addUser(1L, "John", "example.com");
        dataBase.addUser(2L, "Mike", "email.ru");
        Assertions.assertEquals("John", dataBase.findById(1L).name);
        Assertions.assertEquals("Mike", dataBase.findById(2L).name);
    }

    @Test
    void shouldAddAndFindUserService(){
        Service service = new Service();
        User user = new User(1L, "Mike", "example.com");
        service.addUser(user);
        Assertions.assertEquals("example.com", service.findUserById(1L).email);
    }

    @Test
    void shouldAddAndDeleteUser() throws SQLException {
        DataBase dataBase = new DataBase();
        dataBase.createTable();
        dataBase.addUser(1L, "Jonn", "example.com");
        dataBase.deleteUserById(1L);
        String query = "SELECT * FROM users";
        Assertions.assertFalse(dataBase.select(query).first());
    }

}
