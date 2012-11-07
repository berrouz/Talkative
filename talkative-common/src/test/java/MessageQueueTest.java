import com.github.berrouz.Contact;
import com.github.berrouz.depot.InputQueue;
import com.github.berrouz.depot.MessageQueue;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 05.11.12
 * Time: 12:19
 * To change this template use File | Settings | File Templates.
 */
public class MessageQueueTest {

    private MessageQueue messageQueue;

    @Before
    public void setMessageQueue(){
        messageQueue = new MessageQueue();
    }
    @Test
    public void testIsContactListUpdated(){
        assertFalse(messageQueue.isContactListUpdated());
        messageQueue.setContactList(new LinkedList<Contact>());
        assertTrue(messageQueue.isContactListUpdated());
        messageQueue.resetContactListUpdated();
        assertFalse(messageQueue.isContactListUpdated());
    }
}
