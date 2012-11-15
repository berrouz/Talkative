package com.github.berrouz.common.sending;

import com.github.berrouz.common.Contact;
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
        PrintWriter pw = getPrintWriter(message.getRecipient());
        printToWriter( pw, message.toJson());
    }

    /**
     * Opens socket and gets PrintWriter to the recipient
     * @param recipient
     * @return
     */
    public PrintWriter getPrintWriter(Contact recipient){
        OutputStreamWriter outputBuffer = null;
        try {
            Socket socket = new Socket(recipient.getIpAddress(), recipient.getPort());
            outputBuffer = new OutputStreamWriter(socket.getOutputStream());
        } catch (IOException e) {
            logger.error("Cannot open Socket", e);
        }
        return new PrintWriter(outputBuffer);
    }

    /**
     * prints Data from message to PrintWriter
     * @param printWriter
     * @param message
     */
    public void printToWriter(PrintWriter printWriter, String message){
        printWriter.print(message);
        printWriter.flush();
        printWriter.close();
    }
}
