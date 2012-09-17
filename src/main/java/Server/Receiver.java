package Server;

import Shared.Message;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public enum Receiver {
    INSTANCE;

    // object for synchronization
    private final Object lock = new Object();
    private Socket receivedSocket;
    private boolean hasSocket = false;

    // starting 2 threads and synchronize both on lock object
    public void startReceiver(){
        new ListeningForContactsThread().start();
        new ReadingObjectFromClientSocketThread().start();
    }


    public void putSocket(Socket acceptedSocket){
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

    public void getSocket(){
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

    private class ReadingObjectFromClientSocketThread extends Thread{
        public void run(){
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
                    Message receivedObject = new Gson().fromJson(content.toString(), Message.class);
                    MainServer.currentlyActiveContacts.updateContacts(receivedObject.getFromWhom(), receivedObject.getType());

                } catch (IOException e) {
                    System.out.println("Error while reading from buffered reader");
                    e.printStackTrace();
                }

            }
        }

    }
}
