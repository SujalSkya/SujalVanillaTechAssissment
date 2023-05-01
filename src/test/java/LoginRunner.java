import org.example.base.BaseClass;
import org.testng.annotations.Test;

public class LoginRunner extends Login {

    public LoginRunner() {
    }

    @Test
    public void loginRunner() {
        Login ln = new Login();
        ln.signIn();
        ln.cred();
        ln.cred2();

    }
}
