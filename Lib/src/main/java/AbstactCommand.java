import java.io.Serializable;

public abstract class AbstactCommand implements Serializable {
    private static final long serialVersionUID = 1L;

    private String arg = null;
    private String name = "default";
    private String document = "default";
    private String rInfo = "None Info";
    private Executer executer;
    private User user;

    public void execute() {
        setrInfo("Команды не существует или ошибка аргументов");
    }

    public void rExecute() {
        System.out.println(rInfo);
    }

    public void setrInfo(String rInfo) {
        this.rInfo = rInfo;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public String getArg() {
        return arg;
    }

    public String getDocumentation() {
        return document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setExecuter(Executer executer) {
        this.executer = executer;
    }

    public Executer getExecuter() {
        return executer;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
