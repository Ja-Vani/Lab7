public class CommandInsert extends AbstactCommand {
    private static final long serialVersionUID = 1L;

    private HumanBeing humanBeing;

    public CommandInsert() {
        setName("insert");
        setDocument("добавить новый элемент с заданным ключом");
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
            if (getExecuter().login(getUser()).equals("Авторизация успешна")) setrInfo(getExecuter().
                    insert(getArg(), humanBeing, getUser()));
        } catch (NumberFormatException e) {
            setrInfo("Ошибка аргумента");
        }
    }
}
