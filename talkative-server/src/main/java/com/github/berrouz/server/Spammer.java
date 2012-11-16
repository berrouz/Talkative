package com.github.berrouz.server;

import com.github.berrouz.common.SendableAsJson;
import com.github.berrouz.common.depot.MessageDepot;
import com.github.berrouz.common.Contact;
import com.github.berrouz.common.Message;
import com.github.berrouz.common.depot.MessageQueue;
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
    private MessageDepot messageDepot;

    private static final Logger logger = Logger.getLogger(Spammer.class);

    /**
     * send a message to output queue
     * @param message
     */
    public void sendToContact(Message message){
        getMessageQueue().add(message);
    }

    /**
     * Send ContactList to all recipients
     * @param recipients
     */
    public void sendToAll(List<Contact> recipients){
        for(Contact recipient: recipients){
            List<Contact> contactList = getCopy(recipients);
            contactList.remove(recipient);
            sendToContact(createMessage(contactList, recipient, serverContact));
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

    protected Message createMessage(List<Contact> contactToBeSent, Contact recipient, Contact sender){
        return new Message(new Gson().toJson(contactToBeSent), Message.MESSAGE_TYPES.CONTACT_LIST, recipient, sender);
    }

    // Getters and setter

    public void setServerContact(Contact serverContact) {
        this.serverContact = serverContact;
    }

    public MessageQueue<Message> getMessageQueue() {
        return messageDepot.getOutputMessages();
    }

    public void setMessageQueue(MessageQueue<Message> messageQueue) {
        this.messageDepot.setOutputMessages(messageQueue);
    }

    public void setMessageDepot(MessageDepot messageDepot) {
        this.messageDepot = messageDepot;
    }
}
