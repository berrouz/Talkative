package Server;
import Client.Contact;
import Shared.Message;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//  server holds current list of all connected clients
public class CurrentlyActiveAccounts {
    private Logger logger = Logger.getLogger(CurrentlyActiveAccounts.class.getName());
    private final Set<Contact> currentlyActiveContacts;

    public CurrentlyActiveAccounts(){
        this.currentlyActiveContacts = Collections.synchronizedSet(new HashSet<Contact>());
    }

    private void addContact(Contact contact){
        logger.info("Size of Contact list before " + this.currentlyActiveContacts.size());
        currentlyActiveContacts.add(contact);
        MainServer.sender.updateClients(getContactList());
        logger.info("Size of Contact list after "  + this.currentlyActiveContacts.size());
    }
    private Set<Contact> getContactList(){
        return currentlyActiveContacts;
    }
    private void removeContact(Contact contact){
        currentlyActiveContacts.remove(contact);
        MainServer.sender.updateClients(getContactList());
    }
    protected void updateContacts(Contact updateContact, Message.MESSAGE_TYPES type){
        logger.info("BEFORE UPDATE"  + currentlyActiveContacts.size());
        switch (type){
            case REMOVE_CONTACT: removeContact(updateContact);break;
            case ADD_CONTACT   : addContact(updateContact)   ;break;
        }
        logger.info("AFTER UPDATE "  + currentlyActiveContacts.size());
    }
    /**
     *  deep copy of current contact list
     * @return Set<Contact>
     */
    public synchronized Set<Contact> getCurrentlyActiveContactsCopy(){
        HashSet<Contact> response = new HashSet<Contact>();
        for(Contact contact: currentlyActiveContacts){
            response.add(contact);
        }
        return response;
    }


    /**
     * current list of contacts
     * @return Set<Contact>
     */
    public synchronized Set<Contact> getCurrentlyActiveContacts() {
        return currentlyActiveContacts;
    }
}
