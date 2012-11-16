package com.github.berrouz.common.depot;

import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Collection for holding Contact list
 * @param <E>
 */
public class ContactsList<E> implements Iterable<E>{

    // actual collection
    private List<E> contactList;

    // Variable that save the state of contact list, if its stable --> FALSE, when contact list is updated, FALSE --> TRUE
    private boolean changedContactList;

    private static final Logger logger = Logger.getLogger(ContactsList.class);

    public ContactsList(){
        this.contactList = new LinkedList<E>();
        this.changedContactList = false;
    }

    /**
     * returns current Contact list only if contact list has been changed
     * and has not yet been read by GUI
     * @return
     */
    public List<E> getList() {
        synchronized (contactList){
            if (!changedContactList){
                try{
                    contactList.wait();
                } catch (InterruptedException e) {
                    logger.error("Thread is waiting and was interrupted", e);
                }
            }
            logger.debug("There is a change in contact list"+ contactList.toString());
            this.changedContactList = false;
        }
        return contactList;
    }

    /**
     * Update the current contact list with a new list, received from Server
     * if updated, changedContactList changes state to TRUE and notify Thread, that waits
     * to get this collection in order to show in GUI
     * @param contacts
     */
    public void setContactList(List<E> contacts) {
        synchronized (contactList){
            contactList.clear();
            for(E c: contacts){
                contactList.add(c);
            }
            this.changedContactList = true;
            contactList.notify();
        }
    }

    public void add(E e){
        contactList.add(e);
        this.changedContactList = true;
    }

    public void remove(E e){
        contactList.remove(e);
        this.changedContactList = true;
    }

    public int size(){
        return contactList.size();
    }

    @Override
    public Iterator<E> iterator() {
        return this.contactList.iterator();
    }

    public boolean isChangedContactList() {
        return changedContactList;
    }
}
