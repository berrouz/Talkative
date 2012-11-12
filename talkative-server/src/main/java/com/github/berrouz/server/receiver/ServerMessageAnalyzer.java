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
        System.out.println("Entered");
        switch (message.getType()){
            case ADD_CONTACT    :
                System.out.println("Received message");
                messageQueue.getContactList().add(message.getFromWhom());
                System.out.println("Just added");
                spammer.sendToAll(messageQueue.getContactList().getContactList());
                logger.debug("Contact has been added to contact list on the server and send to clients");
                break;
            case REMOVE_CONTACT :
                messageQueue.getContactList().remove(message.getFromWhom());
                spammer.sendToAll(messageQueue.getContactList().getContactList());
                break;

        }
    }

    public void setSpammer(Spammer spammer) {
        this.spammer = spammer;
    }


}
