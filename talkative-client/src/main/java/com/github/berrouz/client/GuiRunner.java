package com.github.berrouz.client;

import com.github.berrouz.common.Contact;
import com.github.berrouz.common.depot.MessageDepot;
import com.github.berrouz.client.gui.Controller;
import com.github.berrouz.client.gui.Model;
import com.github.berrouz.client.gui.View;
import javax.swing.*;

public class GuiRunner {
    /**
     * Runs Swing GUI in separate Thread
     * @param messageQueue
     * @param myContact
     */
    public GuiRunner(final MessageDepot messageQueue, final Contact myContact){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View view = new View();
                Model model = new Model(messageQueue, view, myContact);
                new Controller(model, view);
            }
        });
    }
}
