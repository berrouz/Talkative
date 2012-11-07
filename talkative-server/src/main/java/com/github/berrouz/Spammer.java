package com.github.berrouz;

import com.github.berrouz.depot.MessageDepot;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import java.util.LinkedList;
import java.util.List;

/**
 * Spammer sends updates to all clients in the current contact list
 */
public class Spammer {
    public Contact serverContact;
    private MessageDepot messageQueue;
    private Logger logger = Logger.getLogger(Spammer.class);
    public Spammer(MessageDepot messageQueue, Contact serverContact){
        this.messageQueue = messageQueue;
        this.serverContact = serverContact;
    }

    /**
     * update all clients in currentClientList
     */

    public void sendToAll(List<Contact> currentContactList){
        List<Contact> contactList;
        // update each client with current contact list
        for(Contact contact: currentContactList){
            contactList = getCopy(currentContactList);
            contactList.remove(contact);                        // removes contact of recipient from sent list
            messageQueue.getOutputMessages().add(new Message(new Gson().toJson(contactList), Message.MESSAGE_TYPES.CONTACT_LIST, contact, serverContact));
            logger.debug("Send updated contact list with "+ contactList.size() + " to "+ contact.toString());
        }
    }

    /**
     * Returns a deep copy of current contact list
     * @param contactList
     * @return
     */
    public List<Contact> getCopy(List<Contact> contactList){
        List<Contact> list= new LinkedList<Contact>();
        for(Contact contact: contactList){
            list.add(contact);
        }
        return list;
    }
}
