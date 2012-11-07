package com.github.berrouz.gui;

import com.github.berrouz.Contact;
import com.github.berrouz.Global;
import com.github.berrouz.Message;
import com.github.berrouz.depot.MessageDepot;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 01.11.12
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
public class Model {
    private View view;
    private Contact myContact;
    private MessageDepot messageQueue;
    private Logger logger = Logger.getLogger(Model.class);
    public Model(MessageDepot messageQueue, View view, Contact myContact){
        this.view = view;
        this.messageQueue = messageQueue;
        this.myContact = myContact;
        new Thread(new SmsReader(messageQueue, view)).start();
        new Thread(new ContactsReader(messageQueue, view)).start();
        view.setTitle(myContact.getFullName());
    }

    public void sendMessage(Message message){
        messageQueue.getOutputMessages().add(message);
    }

    public void sendGoodByeMessage(){
        // when client disconnects send "Goodbye" message
        // to Server
        Message goodByeMessage = new Message("", Message.MESSAGE_TYPES.REMOVE_CONTACT, Global.SERVER_CONTACT.myContact, myContact);
        sendMessage(goodByeMessage);
    }

    public void sendSMS(String textToBeSent, Contact toWhom){
        Message message = new Message(textToBeSent, Message.MESSAGE_TYPES.SMS, toWhom, myContact);
        sendMessage(message);
        logger.info("Client sends sms message to the server");
    }

    public void sendHelloMessage(){
        Message message = new Message("Hello", Message.MESSAGE_TYPES.ADD_CONTACT, Global.SERVER_CONTACT.myContact, myContact);
        sendMessage(message);
        logger.info("Client sends hello message to the server");
    }
}

