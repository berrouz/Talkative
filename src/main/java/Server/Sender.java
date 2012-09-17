package Server;
import Client.Contact;
import Shared.Message;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;

public enum Sender {
    INSTANCE;
    public void updateClients(Set<Contact> contacts){
        new SenderOfContactsThread().start();
    }

    public class SenderOfContactsThread extends Thread{
        public void run(){
            for(Contact c: MainServer.currentlyActiveContacts.getContactList()){
                try {
                    Socket socket = new Socket(c.getIpAddress(), c.getPort());
                    OutputStreamWriter outputBuffer = new OutputStreamWriter(socket.getOutputStream());
                    PrintWriter printWriter = new PrintWriter(outputBuffer);
                    Message message = new Message(new Gson().toJson(MainServer.currentlyActiveContacts.getContactList()), Message.MESSAGE_TYPES.CONTACT_LIST, c, MainServer.serverContact);
                    printWriter.print(message.toJson());
                    printWriter.flush();
                    printWriter.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
