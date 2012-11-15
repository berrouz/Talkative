package com.github.berrouz.server.receiver;

import com.github.berrouz.common.Contact;
import com.github.berrouz.common.Message;
import com.github.berrouz.common.depot.MessageDepot;
import com.github.berrouz.server.Spammer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerMessageAnalyzerTest {

    private ServerMessageAnalyzer analyzer;

    @Before
    public void setAnalyzer(){
        analyzer = new ServerMessageAnalyzer();
        MessageDepot messageDepot = new MessageDepot();
        Spammer spammer = new Spammer();
        spammer.setMessageQueue(messageDepot);
        analyzer.setMessageQueue(messageDepot);
        analyzer.setSpammer(spammer);
    }

    @Test
    public void testAnalyzeAddContact(){
        Message message = mock(Message.class);
        Contact contact = mock(Contact.class);
        when(message.getFromWhom()).thenReturn(contact);
        when(message.getType()).thenReturn(Message.MESSAGE_TYPES.ADD_CONTACT);
        analyzer.analyze(message);
        assertEquals(analyzer.getMessageQueue().getContactList().size(), 1);
    }
}
