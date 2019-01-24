package com.simple.service;

import com.google.common.collect.Lists;
import com.simple.entities.Contact;
import com.simple.service.jpa.ContactRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jpaContactService")
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        if (contactRepository == null) {
            throw new IllegalArgumentException("Repository can't be null!");
        }
        this.contactRepository = contactRepository;
    }

    public Contact findById(Long id) {
        return contactRepository.findOne(id);
    }

    public List<Contact> findAll() {
        return Lists.newArrayList(contactRepository.findAll());
    }

    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    public List<Contact> findByBuyDate(DateTime dateTime) {
        return contactRepository.findByBuyDate(dateTime);
    }

    public Contact findByDateAndName(DateTime dateTime, String name) {
        return contactRepository.findByBuyDateAndName(dateTime, name);
    }

    public List<Contact> getAllForName(String name) {
        return contactRepository.findByNameOrderByBuyDateDesc(name);
    }

    public void saveAll(List<Contact> contacts) {
        contactRepository.save(contacts);
    }
}
