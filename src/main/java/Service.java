import java.util.ArrayList;

public class Service {

    private final DataBase dataBase;

    public Service(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public boolean createTable() {
        return dataBase.createTable();
    }

    public boolean addUser(Long id, String name, String email) {
        return dataBase.addUser(id, name, email);
    }

    public User findUserById(Long id) {
        return dataBase.findById(id);
    }

    public ArrayList<User> findAll() {
        return dataBase.findAll();
    }

    public boolean deleteById(Long id) {
        return dataBase.deleteUserById(id);
    }
}
