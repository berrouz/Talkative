package com.github.berrouz.receiver;

import com.github.berrouz.Message;
import com.github.berrouz.Spammer;
import com.github.berrouz.depot.MessageDepot;
import com.github.berrouz.receiving.Analyzer;
import org.apache.log4j.Logger;

public class ServerMessageAnalyzer extends Analyzer {
    private Spammer spammer;

    private static final Logger logger = Logger.getLogger(ServerMessageAnalyzer.class);

    public ServerMessageAnalyzer(MessageDepot messageQueue, Spammer spammer){
        super(messageQueue);
        this.spammer = spammer;
    }
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
}
