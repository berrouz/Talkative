import Client.ClientBase;
import Server.MainServer;

public class Runner {
    public static void main(String[] args) {
        new MainServer().start();
        new ClientBase("Sergey", "Shevchik", 8005);
        new ClientBase("Misha", "Reiner", 8006);
    }
}
