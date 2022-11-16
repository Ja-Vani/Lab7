public class CommandClear extends AbstactCommand {
    private static final long serialVersionUID = 1L;

    CommandClear() {
        setName("clear");
        setDocument("очистить коллекцию");
    }

    @Override
    public void execute() {
        if (getExecuter().login(getUser()).equals("Авторизация успешна")) setrInfo(getExecuter().clear(getUser()));
    }
}
