import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ServiceTest {

    @Mock
    DataBase dataBase = Mockito.mock(DataBase.class);

    @InjectMocks
    Service service = new Service(dataBase);

    @Test
    void mockServiceShouldGetUser() {
        User user = new User(1L, "Mike", "example.com");
        Mockito.when(dataBase.findById(1L)).thenReturn(user);
        service.findUserById(1L);
        Mockito.verify(dataBase).findById(1L);
    }

    @Test
    void mockServiceShouldAddUser() {
        Mockito.doNothing().when(dataBase).addUser(1L, "Mike", "example.com");
        service.addUser(1L, "Mike", "example.com");
        Mockito.verify(dataBase).addUser(1L, "Mike", "example.com");
    }

}
