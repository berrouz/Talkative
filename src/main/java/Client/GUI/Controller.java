package Client.GUI;

import Client.ClientBase;
import Client.Contact;
import Client.Receiver;
import Client.Sender;
import Shared.Message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Controller {
    private Model model;
    private View view;
    public Controller(Model m, View v){
        this.model = m;
        this.view  = v;
        view.sendButton.addActionListener(new ClickOnSendButtonListener());
        view.addWindowListener(new MainWindowListener());
    }
    private class ClickOnSendButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String textToBeSent = view.textAreaToSend.getText();
            Sender sender = Sender.instance;
            System.out.println(view.names.getSelectedItem()+"  selected");
            Contact senderContact = Receiver.instance.getContact((Contact) view.names.getSelectedItem());
            System.out.println("Message should be sent to "+ senderContact.toString());
            sender.addMessage(new Message(textToBeSent, Message.MESSAGE_TYPES.SMS, senderContact, ClientBase.myContact));
            view.textAreaToSend.setText("");
        }
    }
    private class MainWindowListener implements WindowListener{
        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("closed");
            Sender sender = Sender.instance;
            sender.addMessage(new Message("", Message.MESSAGE_TYPES.REMOVE_CONTACT, ClientBase.multiServerContact, ClientBase.myContact));
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
