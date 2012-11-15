package com.github.berrouz.common;

import com.github.berrouz.common.errors.ArgumentError;
import com.github.berrouz.common.receiving.ReceiverThread;
import com.github.berrouz.common.sending.Sender;

// class holds sender and receiver threads


public class Transceiver {

    private ThreadExecutor threadExecutor;

    private ReceiverThread receiverThread;

    protected Sender sender;

    public void start(){
        if (receiverThread != null && sender != null){
            threadExecutor.execute(sender);
            threadExecutor.execute(receiverThread);
        }
        else{
            throw new ArgumentError("Not all arguments have been set in class "+ Transceiver.class);
        }
    }

    public void setReceiverThread(ReceiverThread receiverThread) {
        this.receiverThread = receiverThread;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public void setThreadExecutor(ThreadExecutor threadExecutor) {
        this.threadExecutor = threadExecutor;
    }
}
