package com.github.berrouz;

import com.github.berrouz.depot.MessageDepot;
import com.github.berrouz.receiver.MessageAnalyzer;
import com.github.berrouz.receiving.Analyzer;
import com.github.berrouz.receiving.ReceiverThread;
import com.github.berrouz.receiving.SocketReader;
import com.github.berrouz.sending.Sender;
import com.github.berrouz.sending.SenderThread;

/**
 * Client encompasses all the classes required for Client side of Chat application
 */
public class Client {

    private Contact myContact;

    private Transceiver transceiver;

    public Client(String firstName, String lastName, int port){
        this.myContact = new Contact(firstName,lastName, "127.0.0.1", port);

        // messageQueue assignment
        MessageDepot queue = new MessageDepot();

        // Sender
        SenderThread senderThread = new SenderThread(queue, new Sender());

        // Receiver
        Analyzer messageAnalyzer = new MessageAnalyzer(queue);
        SocketReader socketReader = new SocketReader(queue);
        ReceiverThread receiverThread = new ReceiverThread(myContact, messageAnalyzer, socketReader);

        // transceiver-receiver
        this.transceiver = new Transceiver();
        this.transceiver.setReceiverThread(receiverThread);
        this.transceiver.setSenderThread(senderThread);
        this.transceiver.start();

        // starts GUI
        new GuiRunner(queue, myContact);
    }
}
