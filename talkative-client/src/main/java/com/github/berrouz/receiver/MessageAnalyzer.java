package com.github.berrouz.receiver;

import com.github.berrouz.Contact;
import com.github.berrouz.Message;
import com.github.berrouz.MessageQueue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Queue;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 01.11.12
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
public class MessageAnalyzer implements Runnable{

    private MessageQueue messageQueue;

    public MessageAnalyzer(MessageQueue messageQueue){
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        Message message;
        while(true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if ((message = messageQueue.getInput().poll()) != null) {
                switch (message.getType()) {
                    case CONTACT_LIST:
                        Type setType = new TypeToken<Queue<Contact>>() {
                        }.getType();
                        messageQueue.setContactList((Queue<Contact>) new Gson().fromJson(message.getData(), setType));
                        break;
                    case SMS:
                        messageQueue.getInputMessages().add(message);
                        break;
                }
            }
        }
    }
}
