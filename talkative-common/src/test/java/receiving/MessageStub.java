package receiving;

import com.github.berrouz.Message;
import receiving.ContactStub;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 05.11.12
 * Time: 12:36
 * To change this template use File | Settings | File Templates.
 */
public class MessageStub {
    public final static Message HELLO_CLIENT1_SERVER = hello_message();
    private static Message hello_message(){
        return new Message("HELLO", Message.MESSAGE_TYPES.ADD_CONTACT, ContactStub.SERVER, ContactStub.CLIENT1);
    }
}
