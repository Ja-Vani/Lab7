
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Reciver {
    private static HashMap<String, AbstactCommand> commands = new HashMap<String, AbstactCommand>();
    private static ArrayList<String> list = new ArrayList<String>();
    private static Stack<String> executePath = new Stack<String>();
    private static SocketChannel client;
    private static User user = new User();

    public Reciver() {
        createCommand();
    }

    public void createCommand() {
        CommandInsert insert = new CommandInsert();
        commands.put(insert.getName(), insert);
        CommandInfo info = new CommandInfo();
        commands.put(info.getName(), info);
        CommandShow show = new CommandShow();
        commands.put(show.getName(), show);
        CommandUpdate update = new CommandUpdate();
        commands.put(update.getName(), update);
        CommandRemove remove = new CommandRemove();
        commands.put(remove.getName(), remove);
        CommandClear clear = new CommandClear();
        commands.put(clear.getName(), clear);
        CommandExecute execute = new CommandExecute();
        commands.put(execute.getName(), execute);
        CommandHistory history = new CommandHistory();
        commands.put(history.getName(), history);
        CommandReplace replace = new CommandReplace();
        commands.put(replace.getName(), replace);
        CommandAverage average = new CommandAverage();
        commands.put(average.getName(), average);
        CommandFilterImpact impact = new CommandFilterImpact();
        commands.put(impact.getName(), impact);
        CommandFilterName name = new CommandFilterName();
        commands.put(name.getName(), name);
        CommandExit exit = new CommandExit();
        commands.put(exit.getName(), exit);
        CommandHelp help = new CommandHelp();
        commands.put(help.getName(), help);
        CommandLogin login = new CommandLogin();
        commands.put(login.getName(), login);
        CommandRegister register = new CommandRegister();
        commands.put(register.getName(), register);
    }

    public void run(String name, SocketChannel client) {
        String[] cms = name.split(" ");
        try {
            if (cms[0].equals("exit") || cms[0].equals("help") ||
                    cms[0].equals("history")) {
                commands.get(cms[0]).execute();
                commands.get(cms[0]).rExecute();
                list.add(name);
                return;
            }
            if (cms[0].equals("execute")) {
                if (cms.length > 1) {
                    commands.get(cms[0]).setUser(user);
                    commands.get(cms[0]).setArg(cms[1]);
                    commands.get(cms[0]).execute();
                } else {
                    System.out.println("Укажите путь");
                    return;
                }
            }
            try {
                if (client.isConnected()) {
                    ObjectOutputStream out = new ObjectOutputStream(client.socket().getOutputStream());
                    if (cms.length == 2 && commands.containsKey(cms[0])) {
                        commands.get(cms[0]).setUser(user);
                        commands.get(cms[0]).setArg(cms[1]);
                        out.writeObject(commands.get(cms[0]));
                    } else if (commands.containsKey(cms[0])) {
                        commands.get(cms[0]).setUser(user);
                        out.writeObject(commands.get(cms[0]));
                    } else {
                        CommandError error = new CommandError();
                        error.setUser(user);
                        out.writeObject(error);
                    }
                    list.add(name);
                    ObjectInputStream in = new ObjectInputStream(client.socket().getInputStream());
                    AbstactCommand a = ((AbstactCommand) in.readObject());
                    a.rExecute();
                    user = a.getUser();
                }
            } catch (NoSuchElementException e) {
                System.out.println("Прервано выполнение");
            } catch (RuntimeException e) {
                System.out.println("Ошибка подключения");
                System.exit(1);
            }
        } catch (SocketException e) {
            System.out.println("Сервер аварийно отключился");
            System.exit(1);
        } catch (IOException | ClassNotFoundException e) {
            System.exit(1);
        }
    }


    public static HashMap<String, AbstactCommand> getCommands() {
        return commands;
    }

    public static ArrayList<String> getList() {
        return list;
    }

    public static Stack<String> getExecutePath() {
        return executePath;
    }

    public static void pushExecutePath(String path) {
        executePath.add(path);
    }

    public static void popExecutePath() {
        executePath.pop();
    }

    public static SocketChannel getClient() {
        return client;
    }

    public static void setClient(SocketChannel client) {
        Reciver.client = client;
    }
}
