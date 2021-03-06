package com.github.berrouz.common.depot;

import com.github.berrouz.common.Contact;
import com.github.berrouz.common.Message;
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
    private static final Logger logger = Logger.getLogger(MessageDepot.class);

    public MessageDepot(){
        this.inputMessages = new MessageQueue<Message>();
        this.outputMessages = new MessageQueue<Message>();
        this.contactList    = new ContactsList<Contact>();
        this.inputSMS       = new MessageQueue<Message>();
    }


    // Getters and Setters

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

    public void setInputMessages(MessageQueue<Message> inputMessages) {
        this.inputMessages = inputMessages;
    }

    public void setOutputMessages(MessageQueue<Message> outputMessages) {
        this.outputMessages = outputMessages;
    }

    public void setContactList(ContactsList<Contact> contactList) {
        this.contactList = contactList;
    }

    public void setInputSMS(MessageQueue<Message> inputSMS) {
        this.inputSMS = inputSMS;
    }
}
