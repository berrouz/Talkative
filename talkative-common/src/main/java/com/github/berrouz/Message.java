package com.github.berrouz;

import com.google.gson.Gson;

public final class Message implements SendableAsJson{
    
    // The value is used to define the type of the message
    private MESSAGE_TYPES type;
    
    // The value where is actual data is saved, encoded as JSON
    // Every object with Gson can be transformed into JSON text format 
    private String data;
    
    // Recipient of Message
    private Contact recipient;

    // Sender of Message
    private Contact sender;

    public Message(){}

    public Message(String data, MESSAGE_TYPES type, Contact recipient, Contact sender){
        this.data = data;
        this.type = type;
        this.recipient = recipient;
        this.sender = sender;
    }

    public String getData() {
        return data;
    }

    public Contact getFromWhom() {
        return sender;
    }

    public Contact getRecipient() {
        return recipient;
    }

    public MESSAGE_TYPES getType() {
        return type;
    }

    public static enum MESSAGE_TYPES{
        REMOVE_CONTACT, ADD_CONTACT, CONTACT_LIST, SMS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (!data.equals(message.data)) return false;
        if (!recipient.equals(message.recipient)) return false;
        if (!sender.equals(message.sender)) return false;
        if (type != message.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + data.hashCode();
        result = 31 * result + recipient.hashCode();
        result = 31 * result + sender.hashCode();
        return result;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }
}
