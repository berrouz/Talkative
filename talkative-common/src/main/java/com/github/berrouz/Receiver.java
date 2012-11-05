package com.github.berrouz;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 29.10.12
 * Time: 12:00
 * To change this template use File | Settings | File Templates.
 */
public class Receiver {
    private Logger logger = Logger.getLogger(Receiver.class);
    private String ipAddress;
    private int port;

    public Receiver(String ipAddress, int port){
        this.ipAddress = ipAddress;
        this.port = port;
    }

    /**
     * Receives the message from the socket and transforms it to Message
     * @return Message
     */
    public Message receive(){
        ServerSocket serverSocket = null;
        Message receivedMessage =  null;
        try {
            serverSocket = new ServerSocket(port);
            logger.info("Server listens on port "+ port);
            Socket acceptedClientSocket = serverSocket.accept();
            logger.info("Client accepted from "+ acceptedClientSocket.getInetAddress() + " "+ acceptedClientSocket.getPort());
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(acceptedClientSocket.getInputStream()));
            StringBuilder content= new StringBuilder();
            String temp;
            while((temp = inputStream.readLine())!= null){
                content.append(temp);
            }
            receivedMessage = new Gson().fromJson(content.toString(), Message.class);

        } catch (IOException e) {
            logger.error("IO Exception in message", e);
        }
        finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                logger.error("Cannot close socket "+ this);
            }
        }
        return receivedMessage;
    }
}
