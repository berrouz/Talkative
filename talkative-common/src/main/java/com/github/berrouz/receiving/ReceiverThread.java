package com.github.berrouz.receiving;

import com.github.berrouz.Contact;
import com.github.berrouz.errors.ArgumentError;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Thread that accept new connections and starts Socket Reader Thread
 */
public class ReceiverThread implements Runnable{

    // contact defines on which port receiving socket is opened and on which interface
    private Contact myContact;

    // Socket Reader is a thread which reads data from accepted socket
    private SocketReader socketReader;

    // Class which analyze messages in input queue and sorts them accordingly, server and client both provide their
    // own realization of abstract class Analyzer
    private Analyzer messageAnalyzer;

    private Logger logger = Logger.getLogger(ReceiverThread.class);

    public ReceiverThread(Contact contact, Analyzer messageAnalyzer, SocketReader socketReader){
        this.myContact = contact;
        this.socketReader = socketReader;
        this.messageAnalyzer = messageAnalyzer;
        start();
    }

    // checks if all required params are set and starts listening on port to incoming connections
    public void start(){
        if(myContact != null && socketReader != null && messageAnalyzer != null){
            new Thread(messageAnalyzer).start();
        }
        else{
            throw new ArgumentError("Not all arguments set in class "+ ReceiverThread.class);
        }
    }

    /**
     * Accepts connections and starts new SocketReader threads and passes accepted sockets
     */
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
                logger.debug("Server " + ReceiverThread.class + " has accepted new client and started new socketReader Thread");
            } catch (IOException e) {
                logger.error("IOException in "+ ReceiverThread.class + " instance " + this, e);
            }
        }
    }

    public SocketReader getSocketReader() {
        return socketReader;
    }
}

