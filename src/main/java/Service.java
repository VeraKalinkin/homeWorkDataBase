import java.util.ArrayList;

public class Service {

    private final DataBase dataBase;

    public Service() {
        this.dataBase = new DataBase();
        dataBase.createTable();
    }

    public void addUser(User user){
        dataBase.addUser(user.id, user.name, user.email);
    }

    public User findUserById(Long id){
        return dataBase.findById(id);
    }

    public ArrayList<User> findAll(){
        return dataBase.findAll();
    }

    public void deleteById(Long id){
        dataBase.deleteUserById(id);
    }
}
