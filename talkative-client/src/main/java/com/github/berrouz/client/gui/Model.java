package com.github.berrouz.client.gui;

import com.github.berrouz.common.Contact;
import com.github.berrouz.client.Global;
import com.github.berrouz.common.Message;
import com.github.berrouz.common.depot.MessageDepot;
import org.apache.log4j.Logger;


/**
 * Model side of  MVC
 */
public class Model {

    private View view;

    private Contact myContact;

    private MessageDepot messageQueue;

    private static final Logger logger = Logger.getLogger(Model.class);

    public Model(MessageDepot messageQueue, View view, Contact myContact){
        this.view = view;
        this.messageQueue = messageQueue;
        this.myContact = myContact;
        new Thread(new SmsReader(messageQueue, view)).start();
        new Thread(new ContactsReader(messageQueue, view)).start();
        view.setTitle(myContact.getFullName());
    }

    /**
     * Adds a new Message to outputQueue of MessageDepot
     * @param message
     */
    public void sendMessage(Message message){
        messageQueue.getOutputMessages().add(message);
    }

    /**
     * Sends GoodBye message to Server
     */
    public void sendGoodByeMessage(){
        Message goodByeMessage = new Message("", Message.MESSAGE_TYPES.REMOVE_CONTACT, Global.SERVER_CONTACT.myContact, myContact);
        sendMessage(goodByeMessage);
    }

    /**
     * Sends SMS to recipient
     * @param textToBeSent
     * @param toWhom
     */
    public void sendSMS(String textToBeSent, Contact toWhom){
        Message message = new Message(textToBeSent, Message.MESSAGE_TYPES.SMS, toWhom, myContact);
        sendMessage(message);
        logger.info("Client sends sms message to the server");
    }

    /**
     * Sends HELLO Message to Server
     */
    public void sendHelloMessage(){
        Message message = new Message("Hello", Message.MESSAGE_TYPES.ADD_CONTACT, Global.SERVER_CONTACT.myContact, myContact);
        sendMessage(message);
        logger.info("Client sends hello message to the server");
    }
}

