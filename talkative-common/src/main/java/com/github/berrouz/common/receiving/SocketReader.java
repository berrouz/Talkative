package com.github.berrouz.common.receiving;

import com.github.berrouz.common.Message;
import com.github.berrouz.common.depot.MessageDepot;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * SocketReader reads from accepted socket and saves to MessageDepot inputMessages queue
 */

public class SocketReader implements Runnable {

    private Socket socket;

    private MessageDepot messageQueue;

    private static final Logger logger = Logger.getLogger(SocketReader.class);

    /**
     * reads from BufferedReader, concatenates text,
     * transforms from JSON to Message object and add to inputMessage queue
     */
    @Override
    public void run() {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String temp, result = "";
            while ((temp = bufferedReader.readLine()) != null) {
                result = result.concat(temp);
            }
            Message message = new Gson().fromJson(result, Message.class);
            messageQueue.getInputMessages().add(message);
            logger.debug("new message received from "+ message.getFromWhom() +" and has been added to input queue of class"+ this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setMessageQueue(MessageDepot messageQueue) {
        this.messageQueue = messageQueue;
    }
}
