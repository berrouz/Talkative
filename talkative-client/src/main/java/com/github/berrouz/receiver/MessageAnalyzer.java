package com.github.berrouz.receiver;

import com.github.berrouz.Contact;
import com.github.berrouz.Message;
import com.github.berrouz.depot.MessageDepot;
import com.github.berrouz.receiving.Analyzer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.Queue;

public class MessageAnalyzer extends Analyzer {

    private Logger logger = Logger.getLogger(MessageAnalyzer.class);

    public MessageAnalyzer(MessageDepot messageQueue){
        super(messageQueue);
    }

    public void analyze(Message message){
        logger.debug(message);
        switch (message.getType()) {
            case CONTACT_LIST:
                Type setType = new TypeToken<Queue<Contact>>() {
                }.getType();
                messageQueue.setContactList((LinkedList<Contact>) new Gson().fromJson(message.getData(), setType));
                logger.debug("Contact list in "+ this +" has been update. There are "+ messageQueue.getContactList().size() + " contacts");
                break;
            case SMS:
                messageQueue.getInputSMS().add(message);
                break;
        }
    }
}
