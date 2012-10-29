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
    private Spammer spammer;

    private List<Contact> currentContactList = new ArrayList<Contact>();

    public Handler(){
        this.spammer = new Spammer();
    }
    public void handleMessage(Message message){
        switch (message.getType()){
            case ADD_CONTACT    :
                currentContactList.add(message.getFromWhom())     ;
                spammer.sendToAll(currentContactList);
                break;
            case REMOVE_CONTACT :
                currentContactList.remove(message.getFromWhom())  ;
                spammer.sendToAll(currentContactList);
                break;
        }
    }
}

