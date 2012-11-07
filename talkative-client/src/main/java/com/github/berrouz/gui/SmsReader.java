package com.github.berrouz.gui;

import com.github.berrouz.Message;
import com.github.berrouz.depot.MessageDepot;
import java.awt.*;

/**
 * SmsReader Thread read SMS messages from MessageDepot
 * and shows them in GUI
 */
public class SmsReader implements Runnable{
    private MessageDepot messageQueue;
    private View view;
    public SmsReader(MessageDepot messageQueue, View view){
        this.messageQueue = messageQueue;
        this.view = view;
    }

    /**
     * If Depot holds at least one new message, this threads gets notified
     * and shows a new message in GUI
     */
    @Override
    public void run() {
        Message message;
        while(true){
            message = messageQueue.getInputSMS().poll() ;
            view.receivedMessages.append(message.getFromWhom().toString() +" :");
            view.receivedMessages.append(message.getData() + "\n");
            view.setPreferredSize(new Dimension(view.getSize().height+10, view.getSize().width));
            view.jScrollPaneReceivedMessages.getVerticalScrollBar().setValue(view.jScrollPaneReceivedMessages.getVerticalScrollBar().getMaximum());
            view.revalidate();
        }
    }
}
