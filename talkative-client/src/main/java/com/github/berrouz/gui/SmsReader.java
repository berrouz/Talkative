package com.github.berrouz.gui;

import com.github.berrouz.Message;
import com.github.berrouz.MessageQueue;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 01.11.12
 * Time: 11:41
 * To change this template use File | Settings | File Templates.
 */
public class SmsReader implements Runnable{
    private MessageQueue messageQueue;
    private View view;
    public SmsReader(MessageQueue messageQueue, View view){
        this.messageQueue = messageQueue;
        this.view = view;
    }

    @Override
    public void run() {
        Message message;
        while(true){
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
