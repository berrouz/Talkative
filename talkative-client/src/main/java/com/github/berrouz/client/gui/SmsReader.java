package com.github.berrouz.client.gui;

import com.github.berrouz.common.Message;
import com.github.berrouz.common.ThreadExecutor;
import com.github.berrouz.common.depot.MessageDepot;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.awt.*;

/**
 * SmsReader Thread read SMS messages from MessageDepot
 * and shows them in GUI
 */
@org.springframework.stereotype.Component
@Scope("singleton")
public class SmsReader implements Runnable{

    @Inject
    private ThreadExecutor threadExecutor;

    @Inject
    private MessageDepot messageQueue;

    @Inject
    private View view;

    private static final Logger logger = Logger.getLogger(SmsReader.class);

    @PostConstruct
    public void start(){
        threadExecutor.execute(this);
        logger.debug("SMS Reader Thread has been started");
    }

    /**
     * If Depot holds at least one new message, this threads gets notified
     * and shows a new message in GUI
     */
    @Override
    public void run() {
        Message message;
        while(true){
            logger.debug("SMS Reader Thread is waiting for incoming messages");
            message = messageQueue.getInputSMS().poll() ;
            logger.debug("SMS message has been received from "+ message.getFromWhom());
            view.receivedMessages.append(message.getFromWhom().toString() +" :");
            view.receivedMessages.append(message.getData() + "\n");
            view.setPreferredSize(new Dimension(view.getSize().height+10, view.getSize().width));
            view.jScrollPaneReceivedMessages.getVerticalScrollBar().setValue(view.jScrollPaneReceivedMessages.getVerticalScrollBar().getMaximum());
            view.revalidate();
        }
    }
}
