package com.github.berrouz;

import com.github.berrouz.errors.ArgumentError;
import com.github.berrouz.receiving.ReceiverThread;
import com.github.berrouz.sending.Sender;
import com.github.berrouz.sending.SenderThread;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 05.11.12
 * Time: 06:39
 * To change this template use File | Settings | File Templates.
 */
// class holds sender and receiver threads
public class Transceiver {
    private ReceiverThread receiverThread;
    protected SenderThread senderThread;

    public void start(){
        if(receiverThread != null && senderThread != null){
            new Thread(senderThread).start();
            new Thread(receiverThread).start();
        }
        else{
            throw new ArgumentError("Not all arguments have been set in class "+ Transceiver.class);
        }
    }

    public void setReceiverThread(ReceiverThread receiverThread) {
        this.receiverThread = receiverThread;
    }

    public void setSenderThread(SenderThread senderThread) {
        this.senderThread = senderThread;
    }
}
