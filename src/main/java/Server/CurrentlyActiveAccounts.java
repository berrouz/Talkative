package Server;
import Client.Contact;
import Shared.Message;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

//  server holds current list of all connected clients
public class CurrentlyActiveAccounts {
    private Logger logger = Logger.getLogger(CurrentlyActiveAccounts.class.getName());
    public final Set<Contact> currentlyActiveContacts;

    public CurrentlyActiveAccounts(){
        this.currentlyActiveContacts = new HashSet<Contact>();
    }
    public void addContact(Contact contact){
        logger.info("Size of Contact list before " + this);
        currentlyActiveContacts.add(contact);
        MainServer.sender.updateClients(getContactList());
        logger.info("Size of Contact list after "  + this);
    }
    public Set<Contact> getContactList(){
        return currentlyActiveContacts;
    }
    public void removeContact(Contact contact){
        currentlyActiveContacts.remove(contact);
        MainServer.sender.updateClients(getContactList());
    }
    public void updateContacts(Contact updateContact, Message.MESSAGE_TYPES type){
        logger.info("BEFORE UPDATE"  + currentlyActiveContacts.size());
        switch (type){
            case REMOVE_CONTACT: removeContact(updateContact);break;
            case ADD_CONTACT   : addContact(updateContact)   ;break;
        }
        logger.info("AFTER UPDATE "  + currentlyActiveContacts.size());
    }
}
