package com.github.berrouz.receiving;

import com.github.berrouz.Contact;
import com.github.berrouz.MessageQueue;
import com.github.berrouz.errors.ArgumentError;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 01.11.12
 * Time: 09:50
 * To change this template use File | Settings | File Templates.
 */
public class ReceiverThread implements Runnable{
    private Contact myContact;
    private SocketReader socketReader;
    private Analyzer messageAnalyzer;
    private Logger logger = Logger.getLogger(ReceiverThread.class);

    public ReceiverThread(Contact contact, Analyzer messageAnalyzer, SocketReader socketReader){
        this.myContact = contact;
        this.socketReader = socketReader;
        this.messageAnalyzer = messageAnalyzer;
        start();
    }

    public void start(){
        if(myContact != null && socketReader != null && messageAnalyzer != null){
            new Thread(messageAnalyzer).start();
        }
        else{
            throw new ArgumentError("Not all arguments set in class "+ ReceiverThread.class);
        }
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
                Socket socket = serverSocket.accept();
                socketReader.setSocket(socket);
                new Thread(socketReader).start();
                logger.debug("Server "+ ReceiverThread.class + " has accepted new client and started new socketReader Thread");
            } catch (IOException e) {
                logger.error("IOException in "+ ReceiverThread.class + " instance " + this, e);
            }
        }
    }
}

