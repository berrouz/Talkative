package com.github.berrouz.server;

import com.github.berrouz.Contact;
import com.github.berrouz.Transceiver;
import com.github.berrouz.depot.MessageQueue;
import com.github.berrouz.receiving.Analyzer;
import com.github.berrouz.receiving.ReceiverThread;
import com.github.berrouz.receiving.SocketReader;
import com.github.berrouz.sending.Sender;
import com.github.berrouz.sending.SenderThread;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;


/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 29.10.12
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 */
public class Server {

    private static Logger logger = Logger.getLogger(Server.class);
    private static Transceiver transceiver;
    private static Contact contact;

    /**
     * Start server!!!!
     */
    public static void start() {
        setContact();
        MessageQueue queue = new MessageQueue();
        SenderThread senderThread = new SenderThread(queue, new Sender());
        Analyzer messageAnalyzer = new ServerMessageAnalyzer(queue, new Spammer(queue, contact));
        SocketReader socketReader = new SocketReader(queue);
        ReceiverThread receiverThread = new ReceiverThread(contact, messageAnalyzer, socketReader);
        transceiver = new Transceiver();
        transceiver.setReceiverThread(receiverThread);
        transceiver.setSenderThread(senderThread);
        transceiver.start();
    }

    private static void setContact() {
        Properties properties = new Properties();
        try {
            properties.load(Server.class.getResourceAsStream("server.properties"));
            contact = new Contact(properties.getProperty("name"), properties.getProperty("name"),
                    properties.getProperty("ip.address"), new Integer(properties.getProperty("port")));
        } catch (IOException e) {
            logger.error("Can not loading the properties file", e);
        }
    }
}
