package com.github.berrouz.common.sending;

import com.github.berrouz.common.Contact;
import com.github.berrouz.common.ContactStubber;
import com.github.berrouz.common.Message;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SenderTest {
    private Message message;
    private Sender sender;
    @Before
    public void init() throws IOException {
        sender = new Sender();
        message = new Message("test4", Message.MESSAGE_TYPES.SMS, ContactStubber.KIEV, ContactStubber.LVIV);
    }

    @Test
    public void testPrintToPrinter() throws IOException {
        OutputStream os = mock(OutputStream.class);
        Printer pw = new Printer(os);
        sender.printToWriter(pw,message.toJson());
        assertEquals(message.toJson(), pw.getText());

    }

    @Test
    public void testGetPrintWriter() throws IOException {
        startServer(message.getRecipient().getPort());
        assertEquals(sender.getPrintWriter(message.getRecipient()).getClass(), PrintWriter.class);
    }

    class Printer extends PrintWriter{
        private StringBuilder text = new StringBuilder();

        public Printer(OutputStream out) {
            super(out);
        }

        public void close(){}
        public void flush(){}
        public void print(String message){
            text.append(message);
        }
        public String getText() {
            return text.toString();
        }
    }


    private void startServer(final int port){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new ServerSocket(port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
