package com.github.berrouz.sending;

import com.github.berrouz.Message;
import com.github.berrouz.depot.MessageDepot;

/**
 * Sender Thread is responsible for sending messages from OutputQueue of MessageDepot
 */
public class SenderThread implements Runnable{
    private Sender sender;
    private MessageDepot messageQueue;
    public SenderThread(MessageDepot messageQueue, Sender sender){
        this.messageQueue = messageQueue;
        this.sender = sender;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message;
            if((message = messageQueue.getOutputMessages().poll())!=null){
                sender.sendMessage(message);
            }
        }
    }
}
