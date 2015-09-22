package com.nq.picco.DAO;

import com.nq.picco.model.Mail;
import org.hibernate.Session;

import com.nq.picco.model.Contact;


public class MailManager {



    public void createAndStoreEvent(Contact contact) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Mail mail = new Mail();
        mail.setName(contact.getName());
        mail.setSubject(contact.getSubject());
        mail.setMessage(contact.getMessage());
        mail.setEmail(contact.getEmail());

        session.save(mail);

        session.getTransaction().commit();

    }

}