package com.simple.controller;

import com.simple.entities.Contact;
import com.simple.entities.Contacts;
import com.simple.service.ContactService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "restful/contacts")
public class RestContactController {
    //todo Перевести всё на POST и AJAX!
    private final Logger logger = LoggerFactory.getLogger(RestContactController.class);

    private final ContactService contactService;

    @Autowired
    public RestContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Contacts listData() {
        logger.info("Searching contacts");
        return new Contacts(contactService.findAll());
    }

    @RequestMapping(value = "/contact/{name}", method = RequestMethod.GET)
    public @ResponseBody List<Contact> findContactById(@PathVariable(value = "name") String name) {
        logger.info("Searching contact by name - " + name);
        return contactService.getAllForName(name);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Contact create(@RequestBody Contact contact) {
        logger.info("Creating contact: " + contact);
        contactService.save(contact);
        logger.info("Contact created successfully with info: " + contact);
        return contact;
    }

    @RequestMapping(value = "/{date}", method = RequestMethod.GET)
    public @ResponseBody List<Contact> findContactsByDate(@PathVariable(value = "date")
                                                              @DateTimeFormat(pattern = "yyyy-MM-dd") DateTime date) {
        logger.info("Searching contacts on date: " + date);
        List<Contact> contacts = contactService.findByBuyDate(date);
        logger.info("Contacts searched successfully!");
        return contacts;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody Contact findContactById3(@RequestParam("name") String name, @RequestParam(value = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd") DateTime date) {
        logger.info("Searching contact by name - " + name + " for date - " + date);
        return contactService.findByDateAndName(date, name);
    }
}
