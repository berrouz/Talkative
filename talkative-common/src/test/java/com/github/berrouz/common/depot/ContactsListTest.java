package com.github.berrouz.common.depot;

import com.github.berrouz.common.Contact;
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
        contactsList.getList();
        assertEquals(contactsList.isChangedContactList(), false);
    }
}
