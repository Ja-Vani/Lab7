import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientProcess2 implements Runnable {

    private Socket clientSoket;
    private AbstactCommand command;
    private Executer executer;

    ClientProcess2(Socket clientSoket, AbstactCommand command, Executer executer) {
        this.clientSoket = clientSoket;
        this.command = command;
        this.executer = executer;
    }

    @Override
    public void run() {
        synchronized (executer) {
            command.setExecuter(executer);
            command.execute();
            command.setExecuter(null);
            Thread thread = new Thread(new ClientOut(clientSoket, command));
            thread.start();
            return;
        }
    }
}
