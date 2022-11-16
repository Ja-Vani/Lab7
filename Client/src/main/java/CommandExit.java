public class CommandExit extends AbstactCommand {
    public CommandExit() {
        setName("exit");
        setDocument("завершить программу (без сохранения в файл)");
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
