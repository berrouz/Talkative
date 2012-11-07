package com.github.berrouz.depot;

import com.github.berrouz.Contact;
import com.github.berrouz.Message;

import java.util.LinkedList;

/**
 * Message Queue is queue where all messages and contacts are saved
 */
public class MessageDepot {

    // general inputMessages queue for every Message received
    private InputQueue<Message> inputMessages;

    // queue where saved contacts of all active users of chat program
    private LinkedList<Contact> contactList;

    // queue where saved only SMS messages to be displayed in chat program, actual text messages from client to client
    private InputQueue<Message> inputSMS;

    // queue where saved messages which are to be sent to clients
    private InputQueue<Message> outputMessages;

    // boolean value, defines if contactList has been updated with new contact list
    private boolean contactListUpdated;


    public MessageDepot(){
        this.inputMessages = new InputQueue<Message>();
        this.outputMessages = new InputQueue<Message>();
        this.contactList    = new LinkedList<Contact>();
        this.inputSMS       = new InputQueue<Message>();
        this.contactListUpdated = false;
    }

    public InputQueue<Message> getInputMessages() {
        return inputMessages;
    }

    public LinkedList<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(LinkedList<Contact> contactList) {
        this.contactList = contactList;
        this.contactListUpdated = true;
    }

    public InputQueue<Message> getInputSMS() {
        return inputSMS;
    }

    public InputQueue<Message> getOutputMessages() {
        return outputMessages;
    }

    public boolean isContactListUpdated(){
        return contactListUpdated;
    }

    public void resetContactListUpdated(){
        this.contactListUpdated = false;
    }
}
