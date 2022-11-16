import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        Reciver reciver;
        String url = "jdbc:postgresql://pg:5432/studs";
        String user = "s311732";
        String password = "scg682";
        Collection collection = new Collection(url, user, password);
        Reciver c = new Reciver(collection);
        reciver = c;
        int port;
        if (args.length >= 1) port = Integer.parseInt(args[0]);
        else port = 7283;
        reciver.run(port);
    }
}
