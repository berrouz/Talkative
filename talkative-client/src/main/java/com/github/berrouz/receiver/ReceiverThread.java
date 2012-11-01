package com.github.berrouz.receiver;

import com.github.berrouz.Contact;
import com.github.berrouz.MessageQueue;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 01.11.12
 * Time: 09:50
 * To change this template use File | Settings | File Templates.
 */
public class ReceiverThread implements Runnable{
    private Contact myContact;
    private MessageQueue messageQueue;
    public ReceiverThread(Contact contact, MessageQueue messageQueue){
        this.myContact = contact;
        this.messageQueue = messageQueue;
        new Thread(new MessageAnalyzer(messageQueue)).start();
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(myContact.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            try {
                new Thread(new MessageThread(serverSocket.accept(), messageQueue));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

