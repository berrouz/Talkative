package Client;
import Client.GUI.Runner;
import Shared.Message.MESSAGE_TYPES;
import Shared.Message;
public class ClientBase {
    private static final int multiServerPort = 7000;
    private static final String multiServerIpAddress = "127.0.0.1";
    private static final int serverPort = 8000;
    private static final String serverIpAddress = "127.0.0.1";
    // server ip address should get String value from xml configuration file
    // serverPort should get int value from xml configuration file
    public static final Contact myContact           = new Contact("Name", "Surname", "", serverIpAddress, serverPort);
    public static final Contact multiServerContact  = new Contact("server", "server!", "", multiServerIpAddress, multiServerPort);
    private static final Receiver receiver = Receiver.instance;
    private static final Sender sender = Sender.instance;
    public ClientBase(){
        Message message = new Message("hello", MESSAGE_TYPES.ADD_CONTACT, multiServerContact, myContact);
        sender.sendMessage(message);
    }

    public static void main(String[] args) {
        new Runner();
        new ClientBase();
    }
}
