package com.github.berrouz.depot;

import com.github.berrouz.Contact;
import com.github.berrouz.Message;
import org.apache.log4j.Logger;
import java.util.List;

/**
 * Message Depot is class where all messages and contacts are saved
 */
public class MessageDepot {

    // general inputMessages queue for every Message received
    private MessageQueue<Message> inputMessages;

    // queue where saved messages which are to be sent to clients
    private MessageQueue<Message> outputMessages;

    // queue where saved contacts of all active users of chat program
    private ContactsList<Contact> contactList;

    // queue where saved only SMS messages to be displayed in chat program, actual text messages from client to client
    private MessageQueue<Message> inputSMS;

    // logger
    private Logger logger = Logger.getLogger(MessageDepot.class);

    public MessageDepot(){
        this.inputMessages = new MessageQueue<Message>();
        this.outputMessages = new MessageQueue<Message>();
        this.contactList    = new ContactsList<Contact>();
        this.inputSMS       = new MessageQueue<Message>();
    }

    public MessageQueue<Message> getInputMessages() {
        return inputMessages;
    }

    public ContactsList<Contact> getContactList() {
        return this.contactList;
    }

    public void setContactList(List<Contact> contacts) {
        this.contactList.setContactList(contacts);
    }

    public MessageQueue<Message> getInputSMS() {
        return inputSMS;
    }

    public MessageQueue<Message> getOutputMessages() {
        return outputMessages;
    }
}
