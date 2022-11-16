import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Start {
    private static SocketChannel client;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Reciver reciver = new Reciver();
        int port;
        if (args.length >= 1) port = Integer.parseInt(args[0]);
        else port = 7283;
        try {
            client = SocketChannel.open();
            client.connect(new InetSocketAddress("localhost", port));
            while (scanner.hasNextLine()) {
                reciver.run(scanner.nextLine(), client);
            }
        } catch (IOException e) {
        }
    }
}
