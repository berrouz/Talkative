import Client.ClientBase;

public class Runner {
    public static void main(String[] args) {

        new Server.Server().start();
        try {
            new Server.Server();
            Thread.sleep(100);
            new ClientBase("Mihail", "Petrov", 8001);
            Thread.sleep(100);
            new ClientBase("Sergey", "Dunkan", 8000);
            Thread.sleep(100);
            new ClientBase("Gennadiy", "Ribachok", 8003);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }
}
