package Client.GUI;
import Client.Contact;
import Client.Receiver;
import Shared.Message;

import java.awt.*;

public class Model {
    private View view;
    public Model(View view){
        this.view = view;
        new MessageThread().start();
        new MessageShowerThread().start();
    }
    // todo make MessageThread started only if Receiver has a new message
    private class MessageThread extends Thread{
        public void run() {
            while(true){
                new ContactsThread().start();
                System.out.println("polling messages");
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
            if(view.names.getItemCount()!= Receiver.instance.getCurrentContactList().size()){
                view.names.removeAllItems();
                // delete my contact from the display list
                //Receiver.instance.getCurrentContactList().remove(ClientBase.myContact);
                for(Contact contact: Receiver.instance.getCurrentContactList()){
                    view.names.addItem(contact);
                }
            }
        }
    }

    private class MessageShowerThread extends Thread{
        public void run(){
            while(true){
                if(Receiver.instance.getMessages().size() !=0){
                    Message messageText = Receiver.instance.getMessages().poll();
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
