package com.github.berrouz.common.receiving;

import com.github.berrouz.common.Contact;
import com.github.berrouz.common.ThreadExecutor;
import com.github.berrouz.common.errors.ArgumentError;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
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

    private ThreadExecutor threadExecutor;

    // Class which analyze messages in input queue and sorts them accordingly, server and client both provide their
    // own realization of abstract class Analyzer
    private Analyzer messageAnalyzer;

    private static final Logger logger = Logger.getLogger(ReceiverThread.class);

    // checks if all required params are set and starts listening on port to incoming connections
    @PostConstruct
    public void start(){
        if (myContact != null && socketReader != null && messageAnalyzer != null){
            threadExecutor.execute(messageAnalyzer);
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
            logger.debug("Socket on port "+ myContact.getPort() + " has been opened");
        } catch (IOException e) {
            logger.error("Cannot open server socket on port " + myContact.getPort(), e);
        }
        while (true){
            try {
                Socket socket = serverSocket.accept();
                socketReader.setSocket(socket);
                threadExecutor.execute(socketReader);
                logger.debug("Server " + ReceiverThread.class + " has accepted new client and started new socketReader Thread");
            } catch (IOException e) {
                logger.error("IOException in "+ ReceiverThread.class + " instance " + this, e);
            }
        }
    }

    public void setMessageAnalyzer(Analyzer messageAnalyzer) {
        this.messageAnalyzer = messageAnalyzer;
    }

    public void setSocketReader(SocketReader socketReader) {
        this.socketReader = socketReader;
    }

    public void setMyContact(Contact myContact) {
        this.myContact = myContact;
    }

    public void setThreadExecutor(ThreadExecutor threadExecutor) {
        this.threadExecutor = threadExecutor;
    }
}

