package com.github.berrouz;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Message Queue is queue where all messages and contacts are saved
 */
public class MessageQueue {

    // general input queue for every Message received
    private Queue<Message> input;

    // queue where saved contacts of all active users of chat program
    private Queue<Contact> contactList;

    // queue where saved only messages to be displayed in chat program, actual text messages from client to client
    private Queue<Message> inputMessages;

    // queue where saved messages which are to be sent to clients
    private Queue<Message> outputMessages;

    // boolean value, defines if contactList has been updated with new contact list
    private boolean contactListUpdated;


    public MessageQueue(){
        this.input          = new LinkedList<Message>();
        this.contactList    = new LinkedList<Contact>();
        this.inputMessages  = new LinkedList<Message>();
        this.outputMessages = new LinkedList<Message>();
        this.contactListUpdated = false;
    }

    public Queue<Message> getInput() {
        return input;
    }

    public Queue<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(Queue<Contact> contactList) {
        this.contactList = contactList;
        this.contactListUpdated = true;
    }

    public Queue<Message> getInputMessages() {
        return inputMessages;
    }

    public Queue<Message> getOutputMessages() {
        return outputMessages;
    }

    public boolean isContactListUpdated(){
        return contactListUpdated;
    }

    public void resetContactListUpdated(){
        this.contactListUpdated = false;
    }
}
