public class CommandRemove extends AbstactCommand {
    private static final long serialVersionUID = 1L;

    CommandRemove() {
        setName("remove_key");
        setDocument("удалить элемент из коллекции по его ключу");
    }

    @Override
    public void execute() {
        try {
            if (getExecuter().login(getUser()).equals("Авторизация успешна"))
                setrInfo(getExecuter().remove(getArg(), getUser()));
        } catch (NumberFormatException e) {
            setrInfo("Ошибка аргумента");
            return;
        }
    }
}
