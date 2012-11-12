package com.github.berrouz;

import com.github.berrouz.depot.MessageDepot;
import com.github.berrouz.receiver.ServerMessageAnalyzer;
import com.github.berrouz.receiving.Analyzer;
import com.github.berrouz.receiving.ReceiverThread;
import com.github.berrouz.receiving.SocketReader;
import com.github.berrouz.sending.Sender;
import com.github.berrouz.sending.SenderThread;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;


/**
 * Encapsulates all classes for Server side of Chat application
 */
public class Server {
    private Transceiver transceiver;

    private Contact serverContact;

    private static final Logger logger = Logger.getLogger(Server.class);

    public void start(){
        readConfig();
        // messageQueue assignment
        MessageDepot queue = new MessageDepot();

        // Sender
        SenderThread senderThread = new SenderThread(queue, new Sender());

        // Receiver
        Analyzer messageAnalyzer = new ServerMessageAnalyzer(queue, new Spammer(queue, serverContact));
        SocketReader socketReader = new SocketReader(queue);
        ReceiverThread receiverThread = new ReceiverThread(serverContact, messageAnalyzer, socketReader);
        receiverThread.start();
        // transceiver-receiver
        this.transceiver = new Transceiver();
        this.transceiver.setReceiverThread(receiverThread);
        this.transceiver.setSenderThread(senderThread);
        this.transceiver.start();

    }

    /**
     * Reads config from server.properties
     */
    private void readConfig(){
        Properties properties = new Properties();
        try {
            properties.load(Class.class.getResourceAsStream("/server.properties"));
        } catch (IOException e) {
            logger.error("Cannot open resource while reading server properties", e);
        }
        this.serverContact = new Contact(properties.getProperty("server"),
                properties.getProperty("server"),
                properties.getProperty("ip.address"),
                Integer.parseInt(properties.getProperty("port")));
    }
}
