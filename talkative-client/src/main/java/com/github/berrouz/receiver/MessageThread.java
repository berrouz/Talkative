package com.github.berrouz.receiver;

import com.github.berrouz.Message;
import com.github.berrouz.MessageQueue;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 01.11.12
 * Time: 09:55
 * To change this template use File | Settings | File Templates.
 */
public class MessageThread implements Runnable {
    private Socket socket;
    private MessageQueue messageQueue;
    public MessageThread(Socket socket, MessageQueue messageQueue){
        this.socket = socket;
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String temp, result = "";
            while ((temp = bufferedReader.readLine()) != null) {
                result = result.concat(temp);
            }
            System.out.println(result);
            messageQueue.getInput().add(new Gson().fromJson(result, Message.class));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
