package com.github.berrouz.sending;

import com.github.berrouz.Message;
import com.github.berrouz.depot.MessageDepot;
import org.apache.log4j.Logger;

/**
 * Sender Thread is responsible for sending messages from OutputQueue of MessageDepot
 */
public class SenderThread implements Runnable{

    private Sender sender;

    private MessageDepot messageQueue;

    private static final Logger logger = Logger.getLogger(SenderThread.class);

    public SenderThread(MessageDepot messageQueue, Sender sender){
        this.messageQueue = messageQueue;
        this.sender = sender;
    }

    @Override
    public void run() {
        while(true){
            Message message;
            if ((message = messageQueue.getOutputMessages().poll())!=null){
                sender.sendMessage(message);
            }
        }
    }
}
