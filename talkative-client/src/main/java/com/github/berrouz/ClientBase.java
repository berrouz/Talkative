package com.github.berrouz;

import com.github.berrouz.gui.Runner;
import org.apache.log4j.Logger;

public class ClientBase {
    private Logger logger = Logger.getLogger(ClientBase.class.getName());
    private static final int multiServerPort = 7000;
    private static final String multiServerIpAddress = "127.0.0.1";
    private static final String serverIpAddress = "127.0.0.1";
    public Contact myContact;
    public static final Contact multiServerContact  = new Contact("server", "server!", "", multiServerIpAddress, multiServerPort);
    public final Receiver receiver = new Receiver();
    public final Sender sender = new Sender();
    public ClientBase(String firstName, String lastName, int port){
        this.myContact = new Contact(firstName,lastName,"", serverIpAddress, port);
        this.receiver.start(myContact);
        new Runner(this);
        Message message = new Message("hello", Message.MESSAGE_TYPES.ADD_CONTACT, multiServerContact, myContact);
        sender.sendMessage(message);
        logger.info(myContact.getFirstName() + " "+ myContact.getLastName() + " is connecting");
    }
}
