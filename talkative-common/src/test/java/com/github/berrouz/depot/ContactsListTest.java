package com.github.berrouz.depot;

import com.github.berrouz.Contact;
import com.github.berrouz.depot.ContactsList;
import org.junit.Test;
import java.util.LinkedList;
import static junit.framework.Assert.assertEquals;

public class ContactsListTest {
    private ContactsList contactsList;

    public ContactsListTest(){
        this.contactsList = new ContactsList();
    }

    @Test
    public void TestSetContactList(){
        contactsList.setContactList(new LinkedList<Contact>());
        assertEquals(contactsList.isChangedContactList(), true);
        contactsList.getContactList();
        assertEquals(contactsList.isChangedContactList(), false);
    }
}
