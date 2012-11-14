package com.github.berrouz.client.gui;

import com.github.berrouz.client.Global;
import com.github.berrouz.common.Contact;
import com.github.berrouz.common.Message;
import com.github.berrouz.common.depot.MessageDepot;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;


/**
 * Model side of  MVC
 */
@Component
@Scope("singleton")
public class Model {

    @Inject
    private View view;

    @Inject
    private Contact clientContact;

    @Inject
    private MessageDepot messageQueue;

    private static final Logger logger = Logger.getLogger(Model.class);

    @PostConstruct
    public void init(){
        view.setTitle(clientContact.getFullName());
    }
    /**
     * Adds a new Message to outputQueue of MessageDepot
     * @param message
     */
    public void sendMessage(Message message){
        messageQueue.getOutputMessages().add(message);
        logger.debug("Client sends hello message to the server");

    }

    /**
     * Sends GoodBye message to Server
     */
    public void sendGoodByeMessage(){
        Message goodByeMessage = new Message("", Message.MESSAGE_TYPES.REMOVE_CONTACT, Global.SERVER_CONTACT.myContact, clientContact);
        sendMessage(goodByeMessage);
        logger.debug("GOODBYE message has been sent to server from "+ clientContact.getFullName());

    }

    /**
     * Sends SMS to recipient
     * @param textToBeSent
     * @param toWhom
     */
    public void sendSMS(String textToBeSent, Contact toWhom){
        Message message = new Message(textToBeSent, Message.MESSAGE_TYPES.SMS, toWhom, clientContact);
        sendMessage(message);
        logger.debug("Client sends sms message to the server");
    }

    /**
     * Sends HELLO Message to Server
     */
    public void sendHelloMessage(){
        Message message = new Message("Hello", Message.MESSAGE_TYPES.ADD_CONTACT, Global.SERVER_CONTACT.myContact, clientContact);
        sendMessage(message);
    }

    public Contact getClientContact() {
        return clientContact;
    }

    public void setClientContact(Contact clientContact) {
        this.clientContact = clientContact;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setMessageQueue(MessageDepot messageQueue) {
        this.messageQueue = messageQueue;
    }
}

