package Server.Interfaces;

import Client.Contact;
import Shared.Message;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 27.10.12
 * Time: 15:37
 * To change this template use File | Settings | File Templates.
 */
public interface Sender {
    public void sendMessage(Message message);
}
