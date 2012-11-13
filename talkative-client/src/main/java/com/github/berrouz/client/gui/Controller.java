package com.github.berrouz.client.gui;

import com.github.berrouz.common.Contact;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Controller part of MVC
 */
@Component
public class Controller {

    // reference to Model
    @Inject
    private Model model;

    // reference to View
    @Inject
    private View view;

    private static final Logger logger = Logger.getLogger(Controller.class);


    public Controller(){
        //setListeners();
    }

    @PostConstruct
    public void setListeners(){
        view.sendButton.addActionListener(new ClickOnSendButtonListener());
        view.addWindowListener(new MainWindowListener());
        view.addWindowListener(new HelloSender());
        model.sendHelloMessage();
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
        public void windowGainedFocus(WindowEvent e){
            model.sendHelloMessage();
        }
    }
}
