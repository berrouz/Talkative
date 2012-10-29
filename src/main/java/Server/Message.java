package Server;

import Client.Contact;
import Shared.SendableAsJson;
import com.google.gson.Gson;

public final class Message implements SendableAsJson{
    private String name="";
    private MESSAGE_TYPES type;
    private String data;
    private String username;
    private String password;
    private Contact toWhom;
    private Contact fromWhom;
    public Message(){}
    public Message(String data, MESSAGE_TYPES type, Contact to, Contact from){
        this.data = data;
        this.type = type;
        this.toWhom = to;
        this.fromWhom = from;
    }
    public String getData() {
        return data;
    }

    public Contact getFromWhom() {
        return fromWhom;
    }


    public Contact getToWhom() {
        return toWhom;
    }

    public MESSAGE_TYPES getType() {
        return type;
    }

    public static enum MESSAGE_TYPES{
        SERVICE(), DATA(), STATUS, AUTHENTICATION, REMOVE_CONTACT, ADD_CONTACT, CONTACT_LIST, SMS;
    }
    private volatile int hashCode;


    @Override
    public boolean equals(Object o){
        Message ci = (Message) o;
        if(this.name == ci.name){
            return true;
        }
        else{
            return false;
        }

    }
    @Override
    public int hashCode(){
        int result = hashCode;
        if(result == 0){
            result = 17;
            result = 31 * result + name.hashCode();
            hashCode = result;
        }
        return result;
    }

    public String getName(){
        return this.name;
    }
    public String toJson(){
        return new Gson().toJson(this);
    }
}
