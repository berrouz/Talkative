package com.github.berrouz;

import com.github.berrouz.receiver.ReceiverThread;
import com.github.berrouz.sender.SenderThread;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 01.11.12
 * Time: 09:40
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    private Contact myContact;

    public Client(String firstName, String lastName, int port){
        this.myContact = new Contact(firstName,lastName,"", "127.0.0.1", port);
        MessageQueue queue = new MessageQueue();
        new GuiRunner(queue, myContact);
        // starts sender thread
        // if in outputMessageQueue there is a message, Sender sends it
        new Thread(new SenderThread(queue)).start();
        new Thread(new ReceiverThread(myContact, queue)).start();
    }

    public static void main(String[] args) {
        new Server().start();
        new Client("Sergey", "Shevchik", 9090);
    }
}
