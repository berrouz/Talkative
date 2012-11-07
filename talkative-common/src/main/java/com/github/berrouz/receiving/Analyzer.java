package com.github.berrouz.receiving;

import com.github.berrouz.Message;
import com.github.berrouz.depot.MessageQueue;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 05.11.12
 * Time: 07:20
 * To change this template use File | Settings | File Templates.
 */
public abstract class Analyzer implements Runnable{

    protected MessageQueue messageQueue;

    private Logger logger = Logger.getLogger(this.getClass());

    protected Analyzer(MessageQueue messageQueue){
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        Message message;
        while(true){
            if((message = messageQueue.getInput().poll())!= null){
                logger.debug("Input Queue is not empty");
                analyze(message);
                logger.debug("Message is to be analyzed in "+ this.getClass());
            }
        }
    }

    public abstract void analyze(Message message);
}
