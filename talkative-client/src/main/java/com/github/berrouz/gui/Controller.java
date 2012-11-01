package com.github.berrouz.gui;

import com.github.berrouz.Contact;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Controller {
    private Model model;
    private View view;
    public Controller(Model m, View v){
        this.model = m;
        this.view  = v;
        view.sendButton.addActionListener(new ClickOnSendButtonListener());
        view.addWindowListener(new MainWindowListener());
        view.addWindowListener(new HelloSender());
    }
    private class ClickOnSendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String textToBeSent = view.textAreaToSend.getText();
            Contact adressat = (Contact) view.names.getSelectedItem();
            model.sendSMS(textToBeSent, adressat);
            view.textAreaToSend.setText("");
        }
    }
    private class MainWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            model.sendGoodByeMessage();
        }
    }

    private class HelloSender extends WindowAdapter{
        @Override
        public void windowOpened(WindowEvent e){
            model.sendHelloMessage();
        }
    }
}
