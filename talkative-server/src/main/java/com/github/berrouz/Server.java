package com.github.berrouz;

import com.github.berrouz.receiver.ServerMessageAnalyzer;
import com.github.berrouz.receiving.Analyzer;
import com.github.berrouz.receiving.ReceiverThread;
import com.github.berrouz.receiving.SocketReader;
import com.github.berrouz.sending.Sender;
import com.github.berrouz.sending.SenderThread;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 29.10.12
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 */
public class Server {
    private Transceiver transceiver;

    public void start(){
        // messageQueue assignment
        MessageQueue queue = new MessageQueue();

        // Sender
        SenderThread senderThread = new SenderThread(queue, new Sender());

        // Receiver
        Analyzer messageAnalyzer = new ServerMessageAnalyzer(queue, new Spammer(queue));
        SocketReader socketReader = new SocketReader(queue);
        ReceiverThread receiverThread = new ReceiverThread(Global.SERVER_CONTACT.myContact, messageAnalyzer, socketReader);

        // transceiver-receiver
        this.transceiver = new Transceiver(queue);
        this.transceiver.setReceiverThread(receiverThread);
        this.transceiver.setSenderThread(senderThread);
        this.transceiver.start();

    }

    public static void main(String[] args) {
        new Server().start();
    }
}
