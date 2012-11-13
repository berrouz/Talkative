package com.github.berrouz.common.sending;

import com.github.berrouz.common.Message;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Sender class is to send a message to client
 */
public class Sender {

    private static final Logger logger = Logger.getLogger(Sender.class);

    /**
     * send Message to the recipient, mentioned in Message object
     * @param message
     */
    public void sendMessage(Message message){
        logger.debug("Sender is going to send message "+ message+ " and connect with " +
                ""+ message.getRecipient().getIpAddress()+ " and port " + message.getRecipient().getPort());
        try {
            Socket socket = new Socket(message.getRecipient().getIpAddress(), message.getRecipient().getPort());
            OutputStreamWriter outputBuffer = new OutputStreamWriter(socket.getOutputStream());
            PrintWriter printWriter = new PrintWriter(outputBuffer);
            printWriter.print(message.toJson());
            printWriter.flush();
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            logger.error("Cannot send Message in "+Sender.class, e);
        }
    }
}
