public class CommandFilterName extends AbstactCommand {
    private static final long serialVersionUID = 1L;
    CommandFilterName() {
        setName("filter_starts_with_name");
        setDocument("вывести элементы, значение поля name которых начинается с заданной подстроки");
    }

    @Override
    public void execute() {
        if (getExecuter().login(getUser()).equals("Авторизация успешна"))setrInfo(getExecuter().filterName(getArg()));
    }
}
