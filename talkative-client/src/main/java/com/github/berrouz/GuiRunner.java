package com.github.berrouz;

import com.github.berrouz.gui.Controller;
import com.github.berrouz.gui.Model;
import com.github.berrouz.gui.View;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 01.11.12
 * Time: 11:01
 * To change this template use File | Settings | File Templates.
 */
public class GuiRunner {
    /**
     * Runs Swing GUI in separate Thread
     * @param messageQueue
     * @param myContact
     */
    public GuiRunner(final MessageQueue messageQueue, final Contact myContact){
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
