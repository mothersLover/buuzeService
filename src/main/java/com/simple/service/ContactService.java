package com.simple.service;

import com.simple.entities.Contact;
import org.joda.time.DateTime;

import java.util.List;

public interface ContactService {
    Contact findById(Long id);
    List<Contact> findAll();
    void save(Contact contact);
    List<Contact> findByBuyDate(DateTime dateTime);
    Contact findByDateAndName(DateTime dateTime, String name);
    List<Contact> getAllForName(String name);
    void saveAll(List<Contact> contacts);
}
