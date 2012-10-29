package Client.GUI;
import Client.ClientBase;
import Shared.Contact;
import Shared.Message;

import java.awt.*;

public class Model {
    private View view;
    private ClientBase clientBase;
    public Model(View view, ClientBase clientBase){
        this.view = view;
        this.clientBase = clientBase;
        new MessageThread().start();
        new MessageShowerThread().start();
    }
    // todo make MessageThread started only if Receiver has a new message
    private class MessageThread extends Thread{
        public void run() {
            while(true){
                new ContactsThread().start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class ContactsThread extends Thread{
        public void run(){
            if(view.names.getItemCount()!= clientBase.receiver.getCurrentContactList().size()){
                view.names.removeAllItems();
                for(Contact contact: clientBase.receiver.getCurrentContactList()){
                    view.names.addItem(contact);
                }
            }
        }
    }

    private class MessageShowerThread extends Thread{
        public void run(){
            while(true){
                if(clientBase.receiver.getMessages().size() !=0){
                    Message messageText = clientBase.receiver.getMessages().poll();
                    view.receivedMessages.append(messageText.getFromWhom().toString() +" :");
                    view.receivedMessages.append(messageText.getData() + "\n");
                    view.setPreferredSize(new Dimension(view.getSize().height+10, view.getSize().width));
                    view.jScrollPaneReceivedMessages.getVerticalScrollBar().setValue(view.jScrollPaneReceivedMessages.getVerticalScrollBar().getMaximum());
                    view.revalidate();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
