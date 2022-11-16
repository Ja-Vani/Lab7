import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class CommandExecute extends AbstactCommand {
    CommandExecute() {
        setName("execute_script");
        setDocument("считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
    }

    @Override
    public void execute() {
        try {
            File file = new File(getArg());
            if (file.isDirectory()) {
                System.out.println("Файл является директорией");
                return;
            } else if (file.canRead() && file.canWrite()) {
                System.out.println("Ошибка разрешений");
                return;
            } else if (!file.isFile()) {
                System.out.println("Файл не является файлом");
                return;
            }
            if (Reciver.getExecutePath().contains(file.getAbsolutePath())) {
                System.out.println("Рекурсия");
                return;
            }
            Reciver.pushExecutePath(file.getAbsolutePath());
            Scanner scanner = new Scanner(new FileInputStream(file));
            while (scanner.hasNextLine()) {
                String[] cms = scanner.nextLine().split(" ");
                if (cms[0].equals("exit") || cms[0].equals("help") ||
                        cms[0].equals("history")) {
                    Reciver.getCommands().get(cms[0]).execute();
                    Reciver.getCommands().get(cms[0]).rExecute();
                    return;
                }
                if (cms[0].equals("execute")) {
                    if (cms.length > 1) {
                        Reciver.getCommands().get(cms[0]).setArg(cms[1]);
                        Reciver.getCommands().get(cms[0]).execute();
                    } else {
                        System.out.println("Укажите путь");
                        return;
                    }
                }
                if (Reciver.getClient().isConnected()) {
                    ObjectInputStream in = new ObjectInputStream(Reciver.getClient().socket().getInputStream());
                    ObjectOutputStream out = new ObjectOutputStream(Reciver.getClient().socket().getOutputStream());
                    if (cms.length == 2 && Reciver.getCommands().containsKey(cms[0])) {
                        Reciver.getCommands().get(cms[0]).setArg(cms[1]);
                        out.writeObject(Reciver.getCommands().get(cms[0]));
                    } else if (Reciver.getCommands().containsKey(cms[0])) {
                        out.writeObject(Reciver.getCommands().get(cms[0]));
                    } else {
                        out.writeObject(new CommandError());
                    }
                    ((AbstactCommand) in.readObject()).rExecute();
                }
                Reciver.getClient().close();
            }
            Reciver.popExecutePath();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не существует, указана директория или он не может быть открыт");
            System.exit(1);
        } catch (SecurityException e) {
            System.out.println("Ошибка доступа");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
