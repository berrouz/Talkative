package com.github.berrouz.sender;

import com.github.berrouz.Message;
import com.github.berrouz.Sender;
import com.github.berrouz.MessageQueue;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 01.11.12
 * Time: 11:04
 * To change this template use File | Settings | File Templates.
 */
public class SenderThread implements Runnable{
    private Sender sender;
    private MessageQueue messageQueue;
    public SenderThread(MessageQueue messageQueue){
        this.messageQueue = messageQueue;
        this.sender = new Sender();
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
