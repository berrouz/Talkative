package com.github.berrouz.gui;

import com.github.berrouz.Contact;
import com.github.berrouz.depot.MessageDepot;
import org.apache.log4j.Logger;
import java.util.List;

/**
 * Reads contacts from MessageDepot and shows them in GUI
 */
public class ContactsReader implements Runnable{

    private View view;

    // Depot of all messages and contact for Chat application
    private MessageDepot messageQueue;

    private Logger logger = Logger.getLogger(ContactsReader.class);

    public ContactsReader(MessageDepot messageQueue, View view){
        this.messageQueue = messageQueue;
        this.view = view;
    }

    /**
     * If Contact list was updated, then it removes all contacts from GUI and
     * updates JComboBox with a new received from Server contact list
     */
    @Override
    public void run() {
        while(true){
            List<Contact> contactList = messageQueue.getContactList().getContactList();
            view.names.removeAllItems();
            for(Contact c: contactList){
                view.names.addItem(c);
            }
            logger.debug("Contact List is updated by "+ this.getClass());
        }
    }
}
