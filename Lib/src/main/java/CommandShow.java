public class CommandShow extends AbstactCommand {
    private static final long serialVersionUID = 1L;
    CommandShow() {
        setName("show");
        setDocument("вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    @Override
    public void execute() {
        if (getExecuter().login(getUser()).equals("Авторизация успешна"))setrInfo(getExecuter().getElements());
    }
}
