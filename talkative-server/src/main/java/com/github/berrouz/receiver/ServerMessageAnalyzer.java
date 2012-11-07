package com.github.berrouz.receiver;

import com.github.berrouz.Contact;
import com.github.berrouz.Message;
import com.github.berrouz.depot.MessageDepot;
import com.github.berrouz.Spammer;
import com.github.berrouz.receiving.Analyzer;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 05.11.12
 * Time: 07:58
 * To change this template use File | Settings | File Templates.
 */
public class ServerMessageAnalyzer extends Analyzer {
    private Spammer spammer;
    public ServerMessageAnalyzer(MessageDepot messageQueue, Spammer spammer){
        super(messageQueue);
        this.spammer = spammer;
    }
    @Override
    public void analyze(Message message) {
        switch (message.getType()){
            case ADD_CONTACT    :
                messageQueue.getContactList().add(message.getFromWhom());
                spammer.sendToAll(new ArrayList<Contact>(messageQueue.getContactList()));
                break;
            case REMOVE_CONTACT :
                messageQueue.getContactList().remove(message.getFromWhom());
                spammer.sendToAll(new ArrayList<Contact>(messageQueue.getContactList()));
                break;
        }
    }
}
