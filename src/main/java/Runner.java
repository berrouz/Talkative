import Client.ClientBase;
import Server.MainServer;

public class Runner {
    public static void main(String[] args) {
        new MainServer().start();

        try {
            new ClientBase("Mihail", "Petrov", 8001);
            Thread.sleep(500);
            new ClientBase("Sergey", "Dunkan", 8000);
            Thread.sleep(500);
            new ClientBase("Gennadiy", "Ribachok", 8003);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }
}
