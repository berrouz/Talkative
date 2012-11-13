package com.github.berrouz.server.receiver;

import com.github.berrouz.common.Message;
import com.github.berrouz.common.receiving.Analyzer;
import com.github.berrouz.server.Spammer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ServerMessageAnalyzer extends Analyzer {

    private Spammer spammer;

    private static final Logger logger = Logger.getLogger(ServerMessageAnalyzer.class);

    @Override
    public void analyze(Message message) {
        logger.debug(message+" is going to be analyzed by "+ ServerMessageAnalyzer.class);
        switch (message.getType()){
            case ADD_CONTACT    :
                logger.debug("ADD_CONTACT Message has been received from "+ message.getFromWhom());
                messageQueue.getContactList().add(message.getFromWhom());
                logger.debug("NEW CONTACT "+ message.getFromWhom() + " has been added to the current contact list");
                spammer.sendToAll(messageQueue.getContactList().getContactList());
                logger.debug("Contact has been added to contact list on the server and send to clients");
                break;
            case REMOVE_CONTACT :
                logger.debug("Contact list before REMOVAL has "+ messageQueue.getContactList().size() +" contacts");
                messageQueue.getContactList().remove(message.getFromWhom());
                logger.debug("Contact list after REMOVAL has "+ messageQueue.getContactList().size() +" contacts");
                spammer.sendToAll(messageQueue.getContactList().getContactList());
                break;

        }
    }

    public void setSpammer(Spammer spammer) {
        this.spammer = spammer;
    }
}
