package com.simple.controller;

import com.simple.entities.Contact;
import com.simple.service.ContactService;
import com.simple.util.DateTimeFieldHandler;
import com.simple.util.PriceValidator;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final ContactService contactService;

    private final PriceValidator priceValidator;

    @Autowired
    public OrderController(ContactService contactService, PriceValidator priceValidator) {
        this.contactService = contactService;
        this.priceValidator = priceValidator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String startUp(Model uiModel, HttpServletRequest httpServletRequest) {
        logger.info("Showing start page!");
        List<Contact> all = contactService.findAll();
        uiModel.addAttribute("contacts", all);
        uiModel.addAttribute("contact", new Contact());
        uiModel.addAttribute("basePath", httpServletRequest.getServletPath());
        logger.info("contacts size - " + all.size());
        return "startUpPage";
    }

    @RequestMapping(value = "/{date}", method = RequestMethod.GET)
    public String findContactsByDate(@PathVariable(value = "date")
                                                          @DateTimeFormat(pattern = "yyyy-MM-dd") DateTime date, Model uiModel) {
        logger.info("Searching contacts on date: " + date);
        List<Contact> contacts = contactService.findByBuyDate(date);
        List<Contact> contactsWithPrice = priceValidator.getContactWithPrice(contacts);
        uiModel.addAttribute("contacts", contactsWithPrice);
        uiModel.addAttribute("orderDate", DateTimeFieldHandler.getStringViewOfDate(date));
        logger.info("Contacts searched successfully!");
        logger.info("Contacts count - " + contacts.size());
        return "allContactsByDate";
    }

    @RequestMapping(value = "order", method = RequestMethod.POST)
    public String addOrder(@ModelAttribute("contact") Contact contact, BindingResult result, ModelMap modelMap) {
        logger.info("Trying to add Contact - " + contact.toString());
        DateTime today = DateTimeFieldHandler.getNewDate();
        contact.setBuyDate(today);
        contactService.save(contact);
        logger.info("Add Contact successfully");
        return "redirect:" + DateTimeFieldHandler.getStringViewOfDate(today);
    }

    @RequestMapping(value = "date", method = RequestMethod.POST)
    public String getContactsOnDate(@ModelAttribute(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") DateTime date,
                                    BindingResult result) {
        logger.info("Trying to read date - " + date);
        return "redirect:" + DateTimeFieldHandler.getStringViewOfDate(date);
    }

    /*@RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody Contact findOrderByNameAndDate(@RequestParam("name") String name, @RequestParam(value = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd") DateTime date) {
        logger.info("Searching contact by name - " + name + " for date - " + date);
        return contactService.findByDateAndName(date, name);
    }*/
}
