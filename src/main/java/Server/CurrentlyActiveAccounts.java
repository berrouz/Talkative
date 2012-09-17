package Server;
import Client.Contact;
import Shared.Message;
import java.util.HashSet;
import java.util.Set;

//  server holds current list of all connected clients
public class CurrentlyActiveAccounts {
    public Set<Contact> currentlyActiveContacts = new HashSet<Contact>();

    public void addContact(Contact contact){
        currentlyActiveContacts.add(contact);
        MainServer.sender.updateClients(getContactList());
    }
    public Set<Contact> getContactList(){
        return currentlyActiveContacts;
    }
    public void removeContact(Contact contact){
        currentlyActiveContacts.remove(contact);
        MainServer.sender.updateClients(getContactList());
    }
    public void updateContacts(Contact updateContact, Message.MESSAGE_TYPES type){
        switch (type){
            case REMOVE_CONTACT: removeContact(updateContact);break;
            case ADD_CONTACT   : addContact(updateContact)   ;break;
        }

    }
}
