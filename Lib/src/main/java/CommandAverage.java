public class CommandAverage extends AbstactCommand {
    private static final long serialVersionUID = 1L;

    CommandAverage() {
        setName("average_of_impact_speed");
        setDocument("вывести среднее значение поля impactSpeed для всех элементов коллекции");
    }

    @Override
    public void execute() {
        if (getExecuter().login(getUser()).equals("Авторизация успешна")) setrInfo((getExecuter().average()));
    }
}
