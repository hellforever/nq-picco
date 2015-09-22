package com.nq.picco.DAO;

import com.nq.picco.model.Contact;

public class MailDAO {
    public void SaveMail(Contact contact) {
        MailManager mgr = new MailManager();

        mgr.createAndStoreEvent(contact);


    }


}
