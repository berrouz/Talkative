package Client;

import Shared.Message;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
public class Receiver{
    private Queue<Message> inputMessageQueue =  new LinkedList<Message>();
    private Set<Contact> currentContactList = new HashSet<Contact>();
    private Queue<Message> messages = new LinkedList<Message>();
    public static final Receiver instance = new Receiver();

    private Receiver(){
        startReceivingMessages();
    }
    public void startReceivingMessages(){
        new MessageReceiverThread().start();
    }

    public Queue<Message> getMessages() {
        return messages;
    }

    private class MessageReceiverThread extends Thread{
        public void run(){
            while(true){
                BufferedReader inputBuffer;
                try {
                    System.out.println("MessageReceiverThread is started from Receiver");
                    ServerSocket clientSocket = new ServerSocket(ClientBase.myContact.getPort());
                    Socket inputSocket = clientSocket.accept();
                    inputBuffer = new BufferedReader(new InputStreamReader(inputSocket.getInputStream()));
                    new AcceptedSocketReader(inputBuffer).start();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class AcceptedSocketReader extends Thread{
        private BufferedReader reader;
        AcceptedSocketReader(BufferedReader reader){
            this.reader = reader;
        }
        public void run(){
            String temp, result ="";
            try {
                while((temp = reader.readLine()) != null){
                    result = result.concat(temp);
                }
                inputMessageQueue.add(new Gson().fromJson(result, Message.class));
                new MessageAnalyzer().start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private class MessageAnalyzer extends Thread{
        public void run(){
            Message message;
            if((message = inputMessageQueue.poll()) != null){
                switch (message.getType()){
                    case CONTACT_LIST:
                        Type setType = new TypeToken<Set<Contact>>(){}.getType();
                        Receiver.instance.currentContactList = new Gson().fromJson(message.getData(), setType);
                        break;
                    case SMS:
                        getMessages().add(message);
                        break;
                }
            }

        }
    }
    public Set<Contact> getCurrentContactList(){
        return currentContactList;
    }

    public Contact getContact(Contact contact){
        Map<String, Contact> contactHashMap= new HashMap<String, Contact>();
        for(Contact c: currentContactList){
            contactHashMap.put(c.toString(), c);
        }
        return contactHashMap.get(contact.toString());
    }
}
