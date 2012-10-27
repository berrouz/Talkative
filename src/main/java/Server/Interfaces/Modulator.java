package Server.Interfaces;

import Client.Contact;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 27.10.12
 * Time: 19:48
 * To change this template use File | Settings | File Templates.
 */
public interface Modulator extends Sender, Receiver{
    public Message receive();
    public void send(Message message, Contact from, Contact to);
}
