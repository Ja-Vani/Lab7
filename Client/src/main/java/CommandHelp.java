public class CommandHelp extends AbstactCommand {

    public CommandHelp() {
        setName("help");
        setDocument("вывести справку по доступным командам");
    }

    @Override
    public void execute() {
        setrInfo(Reciver.getCommands().entrySet().stream().
                map(o-> o.getValue().getName() + "\n" + o.getValue().getDocumentation()+"\n").
                reduce((s1, s2)-> s1+s2).toString());
    }
}
