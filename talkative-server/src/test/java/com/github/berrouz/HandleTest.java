package com.github.berrouz;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnit44Runner;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 01.11.12
 * Time: 15:48
 * To change this template use File | Settings | File Templates.
 */

public class HandleTest {
    Contact contactA;
    Contact contactB;
    Message addMessage;
    Message removeMessage;
    private Handler handler= new Handler();

    @Before
    public void createMessages(){
        contactA = new Contact("ClientA", "test", "", "127.0.0.1", 9090);
        contactB = new Contact("ClientB", "test", "", "127.0.0.1", 9091);
        addMessage = new Message("test", Message.MESSAGE_TYPES.ADD_CONTACT, contactA, contactB);
        removeMessage = new Message("test", Message.MESSAGE_TYPES.REMOVE_CONTACT, contactA, contactB);
    }

    @Test
    public void testHandleMessageAdd(){
        handler.handleMessage(addMessage);
        assertTrue(handler.getCurrentContactList().size() == 1);
    }
    @Test
    public void testHandleMessageRemove(){
        testHandleMessageAdd();
        handler.handleMessage(removeMessage);
        assertTrue(handler.getCurrentContactList().size() == 0);
    }

    @Test
    public void testSmth(){
        List mockedList = mock(List.class);
        when(mockedList.get(0)).thenReturn("first");
        assertTrue(mockedList.get(0) == "first");
    }
}
