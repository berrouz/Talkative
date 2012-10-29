package Server;

import Shared.Contact;
import Shared.Message;
import Shared.Sender;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 29.10.12
 * Time: 15:34
 * To change this template use File | Settings | File Templates.
 */
public class Spammer {
    public Contact myContact = new Contact("Server", "server", "photo", "127.0.0.1", 7000);
    private Sender sender = new Sender();
    private Logger logger;
    /**
     * update all clients in currentClientList
     */
    public void sendToAll(List<Contact> currentContactList){
        for(Contact contact: currentContactList){
            sender.sendMessage(new Message(new Gson().toJson(currentContactList), Message.MESSAGE_TYPES.CONTACT_LIST, contact, myContact));
        }
    }
}
