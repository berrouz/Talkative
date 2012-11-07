package com.github.berrouz.gui;

import com.github.berrouz.Message;
import com.github.berrouz.depot.MessageDepot;
import java.awt.*;


public class SmsReader implements Runnable{
    private MessageDepot messageQueue;
    private View view;
    public SmsReader(MessageDepot messageQueue, View view){
        this.messageQueue = messageQueue;
        this.view = view;
    }

    @Override
    public void run() {
        Message message;
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if((message = messageQueue.getInputMessages().poll())!= null){
                view.receivedMessages.append(message.getFromWhom().toString() +" :");
                view.receivedMessages.append(message.getData() + "\n");
                view.setPreferredSize(new Dimension(view.getSize().height+10, view.getSize().width));
                view.jScrollPaneReceivedMessages.getVerticalScrollBar().setValue(view.jScrollPaneReceivedMessages.getVerticalScrollBar().getMaximum());
                view.revalidate();
            }
        }
    }
}
