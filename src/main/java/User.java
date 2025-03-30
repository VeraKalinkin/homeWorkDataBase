public class User{
    Long id;
    String name;
    String email;

    User(Long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", name = " + name +
                ", email = " + email;
    }
}