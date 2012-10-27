package Server;
import Client.Contact;
import Server.Interfaces.Receiver;

public class MainServer {
    protected static final String serverIpAddress = "127.0.0.1";
    protected static final int serverPort = 7000;
    protected static final Sender sender = Sender.INSTANCE;
    public static final Receiver receiver = new ReceiverImpl();
    public static final MainServer single = new MainServer();
    protected static final Contact serverContact = new Contact("Main", "Server", "", serverIpAddress, serverPort);
    public static final CurrentlyActiveAccounts currentlyActiveContacts = new CurrentlyActiveAccounts();

    public void start(){

        receiver.startReceiver();
    }
}
