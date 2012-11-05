package com.github.berrouz;

import com.github.berrouz.sending.Sender;
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
    public Contact myContact = new Contact("Server", "server", "photo", "127.0.0.1", 7000);
    private Sender sender = new Sender();
    private Logger logger;

    /**
     * update all clients in currentClientList
     */

    public void sendToAll(List<Contact> currentContactList){
        List<Contact> contactList;
        for(Contact contact: currentContactList){
            contactList = getCopy(currentContactList);
            contactList.remove(contact);
            sender.sendMessage(new Message(new Gson().toJson(contactList), Message.MESSAGE_TYPES.CONTACT_LIST, contact, myContact));
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
