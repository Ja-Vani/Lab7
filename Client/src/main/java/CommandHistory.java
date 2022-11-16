public class CommandHistory extends AbstactCommand {
    CommandHistory() {
        setName("history");
        setDocument("вывести последние 12 команд (без их аргументов)");
    }

    @Override
    public void execute() {
        try {
            setrInfo(Reciver.getList().stream().reduce((s1,s2)-> s1+"\n"+s2).toString());
        } catch (IndexOutOfBoundsException e) {

        }
    }
}
