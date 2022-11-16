
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Reciver {
    private Executer executer;
    private ServerSocket socket;
    private Socket clientSoket;
    private int port = 212;

    public Reciver(Collection collection) {
        executer = new SExecuter(collection);
    }

    public void run(int port) {
        try {
            socket = new ServerSocket(port);
            Scanner scanner = new Scanner(System.in);
            Thread t = new Thread(() -> {
                while (true) {
                    try {
                        String s = scanner.nextLine();
                        if (s.equals("save")) {
                            executer.save();
                            System.out.println("Колекция сохранена");
                        }
                        if (s.equals("exit")) {
                            scanner.close();
                            clientSoket.close();
                            socket.close();
                            System.exit(0);
                        }
                    } catch (Exception e) {
                        System.exit(0);
                    }
                }
            });
            try {
                t.start();
                System.out.println("Сервер запущен");
                while (true) {
                    try {
                        clientSoket = socket.accept();
                    } catch (IOException e) {
                    }
                    System.out.println("Клиент подключился");
                    ExecutorService service = Executors.newWorkStealingPool(5);
                    service.execute(new ClientProcess(clientSoket, executer));
                }
            } finally {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
