package com.github.berrouz;

import com.github.berrouz.Contact;
import com.github.berrouz.Message;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 01.11.12
 * Time: 09:49
 * To change this template use File | Settings | File Templates.
 */
public class MessageQueue {
    private Queue<Message> input;
    private Queue<Contact> contactList;
    private Queue<Message> inputMessages;
    private Queue<Message> outputMessages;
    public MessageQueue(){
        this.input          = new LinkedList<Message>();
        this.contactList    = new LinkedList<Contact>();
        this.inputMessages  = new LinkedList<Message>();
        this.outputMessages = new LinkedList<Message>();
    }

    public Queue<Message> getInput() {
        return input;
    }

    public void setInput(Queue<Message> input) {
        this.input = input;
    }

    public Queue<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(Queue<Contact> contactList) {
        this.contactList = contactList;
    }

    public Queue<Message> getInputMessages() {
        return inputMessages;
    }

    public void setInputMessages(Queue<Message> inputMessages) {
        this.inputMessages = inputMessages;
    }

    public Queue<Message> getOutputMessages() {
        return outputMessages;
    }

    public void setOutputMessages(Queue<Message> outputMessages) {
        this.outputMessages = outputMessages;
    }
}
