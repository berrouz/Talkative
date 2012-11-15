package com.github.berrouz.common.receiving;

import com.github.berrouz.common.Message;
import com.github.berrouz.common.depot.MessageDepot;
import org.apache.log4j.Logger;

/**
 *  Abstract Analyzer defines analyze method which takes a message from input queue and puts it
 *  into MessageQueue(SMS queue) or ContactsQueue
 */
public abstract class Analyzer implements Runnable{

    protected MessageDepot messageQueue;

    private static final Logger logger = Logger.getLogger(Analyzer.class);

    /**
     * if inputMessages queue is not empty
     * sends a message to analyze method of concrete Analyzer
     */
    @Override
    public void run() {
        while (true){
            analyze(messageQueue.getInputMessages().poll());
            logger.debug("Message is to be analyzed in "+ this.getClass());
        }
    }

    public abstract void analyze(Message message);

    public void setMessageQueue(MessageDepot messageQueue) {
        this.messageQueue = messageQueue;
    }

    public MessageDepot getMessageQueue() {
        return messageQueue;
    }
}
