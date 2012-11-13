package com.github.berrouz.server;

import com.github.berrouz.common.depot.MessageDepot;
import com.github.berrouz.common.Contact;
import com.github.berrouz.common.Message;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

/**
 * Spammer sends updates to all clients in the current contact list
 */
@Component
public class Spammer {

    @Inject
    public Contact serverContact;

    @Inject
    private MessageDepot messageQueue;

    private static final Logger logger = Logger.getLogger(Spammer.class);

    /**
     * update all clients in currentClientList
     */

    public void sendToAll(List<Contact> currentContactList){
        List<Contact> contactList;
        // update each client with current contact list
        for (Contact contact: currentContactList){
            contactList = getCopy(currentContactList);
            contactList.remove(contact);                        // removes contact of recipient from sent list
            Message message = new Message(new Gson().toJson(contactList), Message.MESSAGE_TYPES.CONTACT_LIST, contact, serverContact);
            logger.debug("Sent message is " + message.toString() );
            messageQueue.getOutputMessages().add(message);
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
        for (Contact contact: contactList){
            list.add(contact);
        }
        return list;
    }

    public void setServerContact(Contact serverContact) {
        this.serverContact = serverContact;
    }

    public void setMessageQueue(MessageDepot messageQueue) {
        this.messageQueue = messageQueue;
    }
}
