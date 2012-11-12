package com.github.berrouz.receiving;

import com.github.berrouz.Message;
import com.github.berrouz.depot.MessageDepot;
import org.apache.log4j.Logger;

/**
 *  Abstract Analyzer defines analyze method which takes a message from input queue and puts it
 *  into MessageQueue(SMS queue) or ContactsQueue
 */
public abstract class Analyzer implements Runnable{

    protected MessageDepot messageQueue;

    private static final Logger logger = Logger.getLogger(Analyzer.class);

    protected Analyzer(MessageDepot messageQueue){
        this.messageQueue = messageQueue;
    }

    /**
     * run method encompasses activity of sending the message from
     * input queue to analyze method of concrete Analyzer,
     * which after processing a message adds  message to SMS queue of Contacts queue
     */
    @Override
    public void run() {
        Message message;
        while (true){

                logger.debug("Input Queue is not empty");
                analyze(messageQueue.getInputMessages().poll());
                logger.debug("Message is to be analyzed in "+ this.getClass());

        }
    }

    public abstract void analyze(Message message);
}
