package com.github.berrouz.receiving;

import com.github.berrouz.Message;
import com.github.berrouz.depot.MessageDepot;
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

    private Logger logger = Logger.getLogger(SocketReader.class);

    public SocketReader(MessageDepot messageQueue){
        this.messageQueue = messageQueue;
    }

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
}
