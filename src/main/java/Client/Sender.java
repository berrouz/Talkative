package Client;
import Shared.Message;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
public class Sender {
    private Queue<Message> messageOutputQueue = new LinkedList<Message>();
    public static final Sender instance = new Sender();

    public void addMessage(Message message){
        messageOutputQueue.add(message);
        sendMessage(messageOutputQueue.poll());
    }
    public void sendMessage(Message message){
        OutputStreamWriter outputBuffer = null;
        try {
            Socket clientSocket = new Socket(message.getToWhom().getIpAddress(), message.getToWhom().getPort());
            outputBuffer = new OutputStreamWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(outputBuffer);
        printWriter.print(message.toJson()+"\n");
        printWriter.flush();
        printWriter.close();
    }

}
