import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String login;
    private String password;

    User() {
        login = "a";
        password = "a";
    }

    User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
