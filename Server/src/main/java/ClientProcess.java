import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientProcess implements Runnable {
    private Socket clientSoket;
    private Executer executer;

    ClientProcess(Socket clientSoket, Executer executer) {
        this.clientSoket = clientSoket;
        this.executer = executer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                try {
                    ObjectInputStream in = new ObjectInputStream(clientSoket.getInputStream());
                    ExecutorService service = Executors.newCachedThreadPool();
                    service.execute(new ClientProcess2(
                            clientSoket, (AbstactCommand) in.readObject(), executer));
                } catch (EOFException e) {

                }
            }
        } catch (
                SocketException e) {
            System.out.println("Клиент аварийно отключился");
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        } finally {
            try {
                clientSoket.close();
            } catch (IOException e) {
            }
        }
        return;
    }
}
