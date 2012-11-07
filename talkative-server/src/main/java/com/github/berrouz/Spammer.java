package com.github.berrouz;

import com.github.berrouz.depot.MessageQueue;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 29.10.12
 * Time: 15:34
 * To change this template use File | Settings | File Templates.
 */
public class Spammer {
    public Contact serverContact;
    private MessageQueue messageQueue;
    private Logger logger = Logger.getLogger(Spammer.class);
    public Spammer(MessageQueue messageQueue, Contact serverContact){
        this.messageQueue = messageQueue;
        this.serverContact = serverContact;
    }

    /**
     * update all clients in currentClientList
     */

    public void sendToAll(List<Contact> currentContactList){
        List<Contact> contactList;
        for(Contact contact: currentContactList){
            contactList = getCopy(currentContactList);
            contactList.remove(contact);
            messageQueue.getOutputMessages().add(new Message(new Gson().toJson(contactList), Message.MESSAGE_TYPES.CONTACT_LIST, contact, serverContact));
            logger.debug("Send updated contact list with "+ contactList.size() + " to "+ contact.toString());
        }
    }

    public List<Contact> getCopy(List<Contact> contactList){
        List<Contact> list= new LinkedList<Contact>();
        for(Contact contact: contactList){
            list.add(contact);
        }
        return list;
    }
}
