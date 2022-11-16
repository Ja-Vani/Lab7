public class CommandUpdate extends AbstactCommand {
    private static final long serialVersionUID = 1L;

    private HumanBeing humanBeing;

    CommandUpdate() {
        setName("update");
        setDocument("обновить значение элемента коллекции, id которого равен заданному");
    }

    @Override
    public void setArg(String arg) {
        HumanBeing humanBeing = new HumanBeing();
        humanBeing.create();
        this.humanBeing = humanBeing;
        super.setArg(arg);
    }

    @Override
    public void execute() {
        try {
            if (getExecuter().login(getUser()).equals("Авторизация успешна"))
                setrInfo(getExecuter().pushId(getArg(), humanBeing, getUser()));
        } catch (NumberFormatException e) {
            setrInfo("Ошибка аргумента");
            return;
        }
    }
}
