import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientOut implements Runnable {

    private Socket clientSoket;
    private AbstactCommand command;

    ClientOut(Socket clientSoket, AbstactCommand command) {
        this.clientSoket = clientSoket;
        this.command = command;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(clientSoket.getOutputStream());
            out.writeObject(command);
        } catch (IOException e) {
        }
        return;
    }
}
