package com.github.berrouz;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 29.10.12
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 */
public class Server {
    private static Handler handler = new Handler();
    private Receiver receiver = new Receiver(Global.SERVER_CONTACT.myContact.getIpAddress(), Global.SERVER_CONTACT.myContact.getPort());

    public void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
            while(true){
                Message message;
                if((message = receiver.receive())!= null){
                    handler.handleMessage(message);
                }
            }
            }
        }).start();
    }

    public static void main(String[] args) {
        new Server().start();
    }
}
