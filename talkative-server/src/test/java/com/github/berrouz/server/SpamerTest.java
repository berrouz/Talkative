package com.github.berrouz.server;

import com.github.berrouz.common.Contact;
import com.github.berrouz.common.Message;
import com.github.berrouz.common.depot.MessageDepot;
import com.github.berrouz.common.depot.MessageQueue;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class SpamerTest {

    private Spammer spammer;

    @Before
    public void init(){
        spammer = new Spammer();
    }

    @Test
    public void testGetCopy(){
        List<Contact> contactList1 = new LinkedList();
        contactList1.add(new Contact("A", "A", "127.0.0.1", 9000));
        contactList1.add(new Contact("B", "B", "127.0.0.1", 9001));
        contactList1.add(new Contact("C", "C", "127.0.0.1", 9002));
        assertEquals(3, spammer.getCopy(contactList1).size());
        assertNotSame(contactList1, spammer.getCopy(contactList1));
    }

    @Test
    public void testSentToContact(){
        spammer.setMessageDepot(new MessageDepot());
        assertEquals(0, spammer.getMessageQueue().size());
        spammer.sendToContact(new Message());
        assertEquals(1, spammer.getMessageQueue().size());
    }

    @Test
    public void testCreateMessage(){
        Message message = spammer.createMessage(new LinkedList<Contact>(), ContactStubber.KIEV, ContactStubber.LVIV);
        Message expected = new Message(new Gson().toJson(new LinkedList<Contact>()), Message.MESSAGE_TYPES.CONTACT_LIST, ContactStubber.KIEV, ContactStubber.LVIV);
        assertEquals(expected, message);
    }

    class MessageDepotMock extends MessageDepot{
        private MessageQueue<Message> messages;

        public MessageDepotMock(){
            messages = new MessageQueueMock<Message>();
        }
        @Override
        public MessageQueue<Message> getOutputMessages(){
            return messages;
        }
    }

    class MessageQueueMock<T> extends MessageQueue<T>{
        private Queue<T> list;
        public MessageQueueMock(){
            list = new LinkedList();
        }
        @Override
        public boolean add(T t){
            return list.add(t);
        }
    }
}
