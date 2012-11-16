package com.github.berrouz.client.gui;

import com.github.berrouz.common.Contact;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
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
@Scope("singleton")
public class Controller {

    // reference to Model
    @Inject
    private Model model;

    // reference to View
    @Inject
    private View view;

    private static final Logger logger = Logger.getLogger(Controller.class);

    @PostConstruct
    public void setListeners(){
        view.sendButton.addActionListener(new ClickOnSendButtonListener());
        view.addWindowListener(new MainWindowListener());
        view.addWindowListener(new HelloSender());
    }

    @PostConstruct
    public void sendHello(){
        model.sendHelloMessage();
    }
    // Listener Class waits for clicking Send Button in View
    private class ClickOnSendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String textToBeSent = view.textAreaToSend.getText();

            if (textToBeSent.length()!= 0 && view.names.getSelectedItem() != null){
                Contact recipient = (Contact) view.names.getSelectedItem();
                model.sendSMS(textToBeSent, recipient);
                view.textAreaToSend.setText("");
            }
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
        public void windowLostFocus(WindowEvent e){
            model.sendHelloMessage();
            view.setTitle(model.getClientContact().getFullName());
        }
    }


    /**
     * update Title of View
     */
    public void updateTitle(){
        view.setTitle(model.getClientContact().getFullName());
    }
}
