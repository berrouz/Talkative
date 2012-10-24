package Server;
import Client.Contact;
import Shared.Message;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum Sender {
    INSTANCE;
    private Logger logger = Logger.getLogger(Sender.class);
    public void updateClients(Set<Contact> contacts){
        new SenderOfContactsThread().start();
    }
    // Thread that upon request resend to all the current clients full list of contacts
    public class SenderOfContactsThread extends Thread{
        public void run(){
            Set<Contact> contactsListWhomToSend = MainServer.currentlyActiveContacts.getCurrentlyActiveContacts();
            synchronized (contactsListWhomToSend){
            for(Contact c: contactsListWhomToSend){
                try {
                    Set<Contact> contactListToBeSent = MainServer.currentlyActiveContacts.getCurrentlyActiveContactsCopy();
                    contactListToBeSent.remove(c);
                    // if at least one contact in the contact list then update the client
                    if(contactListToBeSent.size()>0){
                        Socket socket = new Socket(c.getIpAddress(), c.getPort());
                        OutputStreamWriter outputBuffer = new OutputStreamWriter(socket.getOutputStream());
                        PrintWriter printWriter = new PrintWriter(outputBuffer);
                        Message message = new Message(new Gson().toJson(contactListToBeSent), Message.MESSAGE_TYPES.CONTACT_LIST, c, MainServer.serverContact);
                        printWriter.print(message.toJson());
                        printWriter.flush();
                        printWriter.close();
                        logger.info("Contact list is sent to " + c.getFirstName() + " "+ c.getLastName()+ " with "+ contactListToBeSent.size() + " contacts");
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            }
        }
    };
}
