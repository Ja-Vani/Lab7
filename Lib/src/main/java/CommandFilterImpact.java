public class CommandFilterImpact extends AbstactCommand {
    private static final long serialVersionUID = 1L;
    CommandFilterImpact() {
        setName("filter_greater_than_impact_speed");
        setDocument("вывести элементы, значение поля impactSpeed которых больше заданного");
    }

    @Override
    public void execute() {
        if (getExecuter().login(getUser()).equals("Авторизация успешна"))setrInfo(getExecuter().filterImpact(getArg()));
    }
}
