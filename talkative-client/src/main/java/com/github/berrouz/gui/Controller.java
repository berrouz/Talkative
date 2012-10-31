package com.github.berrouz.gui;

import com.github.berrouz.ClientBase;
import com.github.berrouz.Contact;
import com.github.berrouz.Message;
import com.github.berrouz.Sender;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Controller {
    private ClientBase clientBase;
    private Model model;
    private View view;
    public Controller(Model m, View v, ClientBase clientBase){
        this.model = m;
        this.view  = v;
        this.clientBase = clientBase;
        view.sendButton.addActionListener(new ClickOnSendButtonListener());
        view.addWindowListener(new MainWindowListener());
    }
    private class ClickOnSendButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String textToBeSent = view.textAreaToSend.getText();
            Sender sender = clientBase.sender;
            Contact senderContact = clientBase.receiver.getContact((Contact) view.names.getSelectedItem());
            sender.sendMessage(new Message(textToBeSent, Message.MESSAGE_TYPES.SMS, senderContact, clientBase.myContact));
            view.textAreaToSend.setText("");
        }
    }
    private class MainWindowListener implements WindowListener{
        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            clientBase.sender.sendMessage(new Message("", Message.MESSAGE_TYPES.REMOVE_CONTACT, ClientBase.multiServerContact, clientBase.myContact));
        }

        @Override
        public void windowClosed(WindowEvent e) {}

        @Override
        public void windowIconified(WindowEvent e) {}

        @Override
        public void windowDeiconified(WindowEvent e) {}

        @Override
        public void windowActivated(WindowEvent e) {}

        @Override
        public void windowDeactivated(WindowEvent e) {}
    }
}
