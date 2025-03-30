import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ServiceTest {

    @Mock
    Service mockService;

    @Test
    void mockService(){
        Service service = Mockito.mock(Service.class);
        User user = new User(1L, "Mike", "example.com");
        service.addUser(user);
        System.out.println(service.getClass());
    }
}
