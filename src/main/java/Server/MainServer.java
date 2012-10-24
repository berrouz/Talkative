package Server;
import Client.Contact;

public class MainServer {
    protected static final String serverIpAddress = "127.0.0.1";
    protected static final int serverPort = 7000;
    protected static final Sender sender = Sender.INSTANCE;
    public static final Receiver receiver = new Receiver();
    public static final MainServer single = new MainServer();
    protected static final Contact serverContact = new Contact("Main", "Server", "", serverIpAddress, serverPort);
    public static final CurrentlyActiveAccounts currentlyActiveContacts = new CurrentlyActiveAccounts();
    public MainServer(){
        ;
    }
    public void start(){
        System.out.println("Server is started in " + serverIpAddress + ":" + serverPort);
        receiver.startReceiver();
    }
}
