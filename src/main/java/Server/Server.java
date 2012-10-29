package Server;

import Client.Contact;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 29.10.12
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 */
public class Server {
    private Receiver receiver = new Receiver(handler.myContact.getIpAddress(), handler.myContact.getPort());
    private static Handler handler;

    public void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler = new Handler();
                while(true){
                    Message message;
                    if((message = receiver.receive())!= null){
                        handler.handleMessage(message);
                    }
                }
            }
        }).start();
    }
}
