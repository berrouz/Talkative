package Server;

import Server.Interfaces.Receiver;
import Shared.Message;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ReceiverImpl implements Receiver {
    private String ipAddress;
    private int port;
    private List<Message> inputMessages;
    // object for synchronization
    private final Object lock = new Object();
    private Socket receivedSocket;
    private boolean hasSocket = false;
    private Logger logger = Logger.getLogger(Receiver.class);
    // starting 2 threads and synchronize both on lock object

    /**
     * Constructor of Receiver
     * @param ipAddress
     * @param port
     * @param inputMessages
     */
    public ReceiverImpl(String ipAddress, int port, List<Message> inputMessages){
        this.ipAddress  = ipAddress;
        this.port       = port;
        this.inputMessages = inputMessages;
        new ListeningForContactsThread().start();
        new ReadingObjectFromClientSocketThread().start();
    }

    private void putSocket(Socket acceptedSocket){
        synchronized (lock){
            if(hasSocket){
                try {
                    acceptedSocket.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.receivedSocket = acceptedSocket;
            this.hasSocket = true;
            lock.notify();
        }
    }


    // if there is no accepted connections ReadingObjectFromClientSocketThread thread starts sleeping until
    // ListeningForContactsThread wakes it through putSocket method
    private void getSocket(){
        synchronized (lock){
            if(!hasSocket){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.hasSocket = false;
            lock.notify();
        }
    }

    /**
     *  Thread listens for hello messages from clients and accept connection
     */

    private class ListeningForContactsThread extends Thread{
        public void run(){
            while(true){
                try {
                    ServerSocket serverSocket = new ServerSocket(MainServer.serverPort);
                    Socket acceptedClientSocket = serverSocket.accept();
                    putSocket(acceptedClientSocket);
                    serverSocket.close();
                } catch (IOException e) {
                    System.out.println("Socket cannot be opened on the port " + MainServer.serverPort);
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *      Thread reads from accepted socket Messages from Clients
     */
    private class ReadingObjectFromClientSocketThread extends Thread{
        public synchronized void run(){
            while(true){
                try {
                    // getting client's socket
                    getSocket();
                    BufferedReader inputStream = new BufferedReader(new InputStreamReader(receivedSocket.getInputStream()));
                    StringBuilder content= new StringBuilder();
                    String temp;
                    while((temp = inputStream.readLine())!= null){
                        content.append(temp);
                    }
                    Message message = new Gson().fromJson(content.toString(), Message.class);
                    inputMessages.add(message);

                } catch (IOException e) {
                    System.out.println("Error while reading from buffered reader");
                    e.printStackTrace();
                }

            }
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public List<Message> getInputMessages() {
        return inputMessages;
    }

    public void setInputMessages(List<Message> inputMessages) {
        this.inputMessages = inputMessages;
    }
}
