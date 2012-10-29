package Server;

import org.apache.log4j.Logger;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 29.10.12
 * Time: 12:49
 * To change this template use File | Settings | File Templates.
 */
public class ReceivedMessages {
    private LinkedList<Message> messages = new LinkedList<Message>();
    private Logger logger = Logger.getLogger(ReceivedMessages.class);

    public void add(Message message){
        synchronized (messages){
            if(message != null){
                this.messages.add(message);
                try {
                    messages.wait();
                } catch (InterruptedException e) {
                    logger.error(e);
                }
            }
            messages.notify();
        }
    }

    public Message poll(){
        Message message;
        synchronized (messages){
            if((message = messages.poll())==null){
                try {
                    messages.wait();
                } catch (InterruptedException e) {
                    logger.error(e);
                }
            }
            messages.notify();
        }
        return message;
    }
}
