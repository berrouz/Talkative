package com.github.berrouz;

import com.github.berrouz.depot.MessageDepot;
import com.github.berrouz.gui.Controller;
import com.github.berrouz.gui.Model;
import com.github.berrouz.gui.View;
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
