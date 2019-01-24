package com.simple;

import com.simple.entities.Contact;
import com.simple.service.ContactService;
import com.simple.util.DateTimeFieldHandler;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.format.datetime.DateFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SpringJpaSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/application-context.xml");
        ctx.refresh();

        final ContactService contactService
                = ctx.getBean("jpaContactService", ContactService.class);

        addContacts(contactService);

        final List<Contact> contactList = contactService.findAll();
        listContacts(contactList);
        findByDate(contactService);
    }

    private static void addContacts(ContactService contactService) {
        Contact contact = new Contact();
        String pattern = "yyyy-MM-dd";
        String date = "2017-05-26";
        DateTime dateTime = DateTimeFieldHandler.getDateTimeFromString(date, pattern);
        contact.setBuyDate(dateTime);
        contact.setCount(4);
        contact.setName("John");
        contactService.save(contact);

        date = "2017-09-22";
        dateTime = DateTimeFieldHandler.getDateTimeFromString(date, pattern);
        contact.setBuyDate(dateTime);
        contact.setCount(5);
        contact.setName("Roma");
        contactService.save(contact);
    }

    private static void findByDate(ContactService contactService) {
        System.out.println("------------------------------------------------------------------------------------");
        String date = "2017-05-26";
        System.out.println("starting found contacts on date - " + date);
        String pattern = "yyyy-MM-dd";
        DateTime dateTime = DateTimeFieldHandler.getDateTimeFromString(date, pattern);
        List<Contact> byBuyDate = contactService.findByBuyDate(dateTime);
        System.out.println("searched - " + byBuyDate.size() + " elements!");
        listContacts(byBuyDate);
    }

    private static void listContacts(final List<Contact> contactList) {
        System.out.println("");
        for (Contact contact: contactList) {
            System.out.println(contact);
            System.out.println();
        }
    }

}
