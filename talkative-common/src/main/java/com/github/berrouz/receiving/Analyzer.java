package com.github.berrouz.receiving;

import com.github.berrouz.Contact;
import com.github.berrouz.Message;
import com.github.berrouz.MessageQueue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 05.11.12
 * Time: 07:20
 * To change this template use File | Settings | File Templates.
 */
public abstract class Analyzer implements Runnable{

    protected MessageQueue messageQueue;

    protected Analyzer(MessageQueue messageQueue){
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        Message message = null;
        while(true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            analyze(message);
        }
    }

    public abstract void analyze(Message message);
}
