package Server;

import Shared.Contact;
import Shared.Message;
import Shared.Sender;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 29.10.12
 * Time: 11:48
 * To change this template use File | Settings | File Templates.
 */
public class Handler {
    public Contact myContact = new Contact("Server", "server", "photo", "127.0.0.1", 7000);

    private Sender sender = new Sender();
    private List<Contact> currentContactList = new ArrayList<Contact>();

    public void handleMessage(Message message){
        switch (message.getType()){
            case ADD_CONTACT    :
                currentContactList.add(message.getFromWhom())     ;
                updateAllClients();
                break;
            case REMOVE_CONTACT : currentContactList.remove(message.getFromWhom())  ;
                updateAllClients();
                break;
        }
    }

    /**
     * update all clients in currentClientList
     */
    public void updateAllClients(){

        for(Contact contact: currentContactList){
            sender.sendMessage(new Message(new Gson().toJson(currentContactList), Message.MESSAGE_TYPES.CONTACT_LIST, contact, myContact));
        }
    }
}

