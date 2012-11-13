package com.github.berrouz.client.receiver;

import com.github.berrouz.common.Contact;
import com.github.berrouz.common.Message;
import com.github.berrouz.common.receiving.Analyzer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.Queue;

public class MessageAnalyzer extends Analyzer {

    private static final Logger logger = Logger.getLogger(MessageAnalyzer.class);

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
