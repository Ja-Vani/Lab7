import java.io.IOException;
import java.util.Scanner;

public class CommandLogin extends AbstactCommand {
    private static final long serialVersionUID = 1L;

    public CommandLogin() {
        setName("login");
        setDocument("Команда для подключения к пользователю");
    }

    @Override
    public void execute() {
        try {
            setrInfo(getExecuter().login(getUser()));
        } catch (NumberFormatException e) {
            setrInfo("Ошибка аргумента");
            return;
        }
    }

    @Override
    public void rExecute() {
        super.rExecute();

    }

    @Override
    public void setUser(User user) {
        Scanner scanner = new Scanner(System.in);
        String login = "", password = "";
        System.out.println("Введите Логин");
        try {
            while (scanner.hasNextLine()) {
                String buff = scanner.nextLine();
                if (!buff.equals("")) {
                    login = buff;
                    break;
                }
                System.out.println("Введите Логин");
            }
            System.out.println("Введите пароль");
            while (scanner.hasNextLine()) {
                String buff = scanner.nextLine();
                if (!buff.equals("")) {
                    password = buff;
                    break;
                }
                System.out.println("Введите пароль");
            }
        } catch (Exception e) {
        }
        user = new User(login, password);
        super.setUser(user);
    }
}
