package com.github.berrouz;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 29.10.12
 * Time: 12:00
 * To change this template use File | Settings | File Templates.
 */
public class Sender {
    private Logger logger = Logger.getLogger(Sender.class);

    /**
     * send Message to the recipient, mentioned in Message object
     * @param message
     */
    public void sendMessage(Message message){
        try {
            Socket socket = new Socket(message.getToWhom().getIpAddress(), message.getToWhom().getPort());
            OutputStreamWriter outputBuffer = new OutputStreamWriter(socket.getOutputStream());
            PrintWriter printWriter = new PrintWriter(outputBuffer);
            printWriter.print(message.toJson());
            printWriter.flush();
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
