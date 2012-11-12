package com.github.berrouz.common.sending;

import com.github.berrouz.common.Message;
import com.github.berrouz.common.depot.MessageDepot;
import org.apache.log4j.Logger;

/**
 * Sender Thread is responsible for sending messages from OutputQueue of MessageDepot
 */
public class SenderThread implements Runnable{

    private Sender sender;

    private MessageDepot messageQueue;

    private static final Logger logger = Logger.getLogger(SenderThread.class);

    @Override
    public void run() {
        while(true){
            Message message;
            if ((message = messageQueue.getOutputMessages().poll())!=null){
                sender.sendMessage(message);
            }
        }
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public void setMessageQueue(MessageDepot messageQueue) {
        this.messageQueue = messageQueue;
    }
}
