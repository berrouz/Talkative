package com.github.berrouz;

import com.github.berrouz.depot.MessageDepot;
import com.github.berrouz.receiver.ServerMessageAnalyzer;
import com.github.berrouz.receiving.Analyzer;
import com.github.berrouz.receiving.ReceiverThread;
import com.github.berrouz.receiving.SocketReader;
import com.github.berrouz.sending.Sender;
import com.github.berrouz.sending.SenderThread;


/**
 * Encapsulates all classes for Server side of Chat application
 */
public class Server {
    private Transceiver transceiver;

    private Contact serverContact;

    public Server(Contact contact){
        this.serverContact = contact;
    }
    public void start(){
        // messageQueue assignment
        MessageDepot queue = new MessageDepot();

        // Sender
        SenderThread senderThread = new SenderThread(queue, new Sender());

        // Receiver
        Analyzer messageAnalyzer = new ServerMessageAnalyzer(queue, new Spammer(queue, serverContact));
        SocketReader socketReader = new SocketReader(queue);
        ReceiverThread receiverThread = new ReceiverThread(serverContact, messageAnalyzer, socketReader);

        // transceiver-receiver
        this.transceiver = new Transceiver();
        this.transceiver.setReceiverThread(receiverThread);
        this.transceiver.setSenderThread(senderThread);
        this.transceiver.start();

    }

    public static void main(String[] args) {
        Contact serverContact = new Contact("server", "server", "127.0.0.1", 7000);
        new Server(serverContact).start();
    }
}
