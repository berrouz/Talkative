package com.github.berrouz.gui;

import javax.swing.*;
import java.awt.*;

/**
 * View Side of MVC
 */
public class View extends JFrame {

    // X location of the main window
    private static int defaultLocationX = 400;

    // Y location of the main window
    private static int defaultLocationY = 300;

    // Name of window
    private static String windowName = "Simple Communicator";

    // Width and length of the main Chat Window
    private static Dimension defaultDimension = new Dimension(300, 600);

    // TextArea where a user inputs messages
    protected JTextArea textAreaToSend = new JTextArea("");

    // TextArea where received messages get showed
    protected final JTextArea receivedMessages = new JTextArea("");

    // Panel for displaying contacts
    private final JPanel contactsPanel = new JPanel();

    // ComboBox for drop-down list of current active clients in Chat application
    protected final JComboBox names = new JComboBox();

    // Scrollable Pane which scrolls if there is no sufficient space for displaying all the messages
    protected JScrollPane jScrollPaneReceivedMessages;

    // Button for displaying Send Button
    protected final JButton sendButton = new JButton("Send");

    // Constructor which invokes all the needed actions in order to start GUI
    public View(){
        setDefault();
        setVisible(true);
        setBottom();
        setContacts();
        setTop();
        pack();
    }

    // Setting main Window config
    void setDefault(){
        setLocation(defaultLocationX, defaultLocationY);
        setPreferredSize(defaultDimension);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
    }

    // Setting TOP part of View
    void setTop(){
        JPanel topPanel = new JPanel();
        JLabel topLabel = new JLabel(windowName);
        topPanel.add(topLabel);
        add(topPanel, BorderLayout.NORTH);
    }

    // Setting Contact
    void setContacts(){
        contactsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contactsPanel.setLayout(new BoxLayout(contactsPanel, BoxLayout.Y_AXIS));
        textAreaToSend.setPreferredSize(new Dimension(240, 50));
        textAreaToSend.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        receivedMessages.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        receivedMessages.setEditable(false);
        jScrollPaneReceivedMessages = new JScrollPane(receivedMessages, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneReceivedMessages.setPreferredSize(new Dimension(240, 150));
        contactsPanel.add(names);
        contactsPanel.add(Box.createRigidArea(new Dimension(100,15)));
        contactsPanel.add(jScrollPaneReceivedMessages);
        contactsPanel.add(Box.createRigidArea(new Dimension(100,15)));
        contactsPanel.add(textAreaToSend);
        contactsPanel.add(Box.createRigidArea(new Dimension(100,15)));
        contactsPanel.add(sendButton);
        add(contactsPanel, BorderLayout.CENTER);
    }

    // Setting Bottom Side of view
    void setBottom(){
        JPanel status = new JPanel();
        JLabel statusLabel = new JLabel("default status");
        status.add(statusLabel);
        add(status, BorderLayout.SOUTH);
    }

    /**
     * Set Title for a window
     * @param title
     */
    public void setTitle(String title){
        super.setTitle(title);
    }
}