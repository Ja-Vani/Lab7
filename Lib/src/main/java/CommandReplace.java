public class CommandReplace extends AbstactCommand {
    private static final long serialVersionUID = 1L;

    private HumanBeing humanBeing;

    CommandReplace() {
        setName("replace_if_lowe");
        setDocument("заменить значение по ключу, если новое значение меньше старого");
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
                setrInfo(getExecuter().ril(getArg(), humanBeing, getUser()));
        } catch (NumberFormatException e) {
            setrInfo("Ошибка аргумента");
        }
    }
}
