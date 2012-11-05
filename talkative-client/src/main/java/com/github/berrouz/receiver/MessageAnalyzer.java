package com.github.berrouz.receiver;

import com.github.berrouz.Contact;
import com.github.berrouz.Message;
import com.github.berrouz.MessageQueue;
import com.github.berrouz.receiving.Analyzer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Queue;

public class MessageAnalyzer extends Analyzer {

    public MessageAnalyzer(MessageQueue messageQueue){
        super(messageQueue);
    }

    public void analyze(Message message){
        if ((message = super.messageQueue.getInput().poll()) != null) {
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
