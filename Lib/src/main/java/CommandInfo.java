public class CommandInfo extends AbstactCommand {
    private static final long serialVersionUID = 1L;
    public CommandInfo() {
        setName("info");
        setDocument("вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
    }

    @Override
    public void execute() {
        if (getExecuter().login(getUser()).equals("Авторизация успешна"))setrInfo(getExecuter().getInfo());
    }
}
