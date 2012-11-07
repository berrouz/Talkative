package com.github.berrouz.gui;

import com.github.berrouz.Contact;
import com.github.berrouz.depot.MessageDepot;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 01.11.12
 * Time: 11:46
 * To change this template use File | Settings | File Templates.
 */
public class ContactsReader implements Runnable{
    private View view;
    private MessageDepot messageQueue;
    public ContactsReader(MessageDepot messageQueue, View view){
        this.messageQueue = messageQueue;
        this.view = view;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(5600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(messageQueue.isContactListUpdated()){
                messageQueue.resetContactListUpdated();
                view.names.removeAllItems();
                for(Contact c: messageQueue.getContactList()){
                    view.names.addItem(c);
                }
            }
        }
    }
}
