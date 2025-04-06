import java.util.ArrayList;

public class Service {

    private final DataBase dataBase;

    public Service(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public boolean createTable() {
        dataBase.createTable();
        return true;
    }

    public boolean addUser(Long id, String name, String email) {
        dataBase.addUser(id, name, email);
        return true;
    }

    public User findUserById(Long id) {
        return dataBase.findById(id);
    }

    public ArrayList<User> findAll() {
        return dataBase.findAll();
    }

    public void deleteById(Long id) {
        dataBase.deleteUserById(id);
    }
}