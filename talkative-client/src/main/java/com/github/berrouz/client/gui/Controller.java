package com.github.berrouz.client.gui;

import com.github.berrouz.common.Contact;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Controller part of MVC
 */
public class Controller {

    // reference to Model
    private Model model;

    // reference to View
    private View view;

    public Controller(Model m, View v){
        this.model = m;
        this.view  = v;
        view.sendButton.addActionListener(new ClickOnSendButtonListener());
        view.addWindowListener(new MainWindowListener());
        view.addWindowListener(new HelloSender());
    }

    // Listener Class waits for clicking Send Button in View
    private class ClickOnSendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String textToBeSent = view.textAreaToSend.getText();
            Contact recipient = (Contact) view.names.getSelectedItem();
            model.sendSMS(textToBeSent, recipient);
            view.textAreaToSend.setText("");
        }
    }

    // When main Window is closed, sends Goodbye message to Server in order to update current list of contacts
    private class MainWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            model.sendGoodByeMessage();
        }
    }

    // When main window is started client sends HELLO message to the Server in order to update current list of contacts
    private class HelloSender extends WindowAdapter{
        @Override
        public void windowOpened(WindowEvent e){
            model.sendHelloMessage();
        }
    }
}
