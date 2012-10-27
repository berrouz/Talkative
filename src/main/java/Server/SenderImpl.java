package Server;

import Client.Contact;
import Server.Interfaces.Sender;
import Shared.Message;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SenderImpl implements Sender {

    private Logger logger = Logger.getLogger(Sender.class);

    @Override
    public void sendMessage(Message message) {
        sendToSocket(message);
    }

    public void sendToSocket(Message message){
        try {
            Socket socket = new Socket(message.getToWhom().getIpAddress(), message.getToWhom().getPort());
            OutputStreamWriter outputBuffer = new OutputStreamWriter(socket.getOutputStream());
            PrintWriter printWriter = new PrintWriter(outputBuffer);
            printWriter.print(message.toJson());
            printWriter.flush();
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
