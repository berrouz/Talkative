import Client.ClientBase;
import Server.MainServer;

public class Runner {
    public static void main(String[] args) {
        new MainServer().start();
        new ClientBase("Mihail", "Petrov", 8001);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new ClientBase("Sergey", "Dunkan", 8000);
    }
}
