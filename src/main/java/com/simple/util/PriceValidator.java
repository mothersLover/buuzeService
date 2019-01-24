package com.simple.util;

import com.simple.StartUpProperties;
import com.simple.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component(value = "priceValidator")
public class PriceValidator {
    private final float buuzePrice;
    private final int containerCapacity;
    private final float containerPrice;
    private final int deliveryPrice;

    @Autowired
    public PriceValidator(StartUpProperties startUpProperties) {
        buuzePrice = startUpProperties.getBuuzePrice();
        containerCapacity = startUpProperties.getContainerCapacity();
        containerPrice = startUpProperties.getContainerPrice();
        deliveryPrice = startUpProperties.getDeliveryPrice();
    }

    public List<Contact> getContactWithPrice(List<Contact> contacts) {
        int countAll = 0;
        int contactsCount = contacts.size();
        for (Contact contact: contacts) {
            countAll += contact.getCount();
        }
        int containersCount = countAll / containerCapacity;
        if (countAll % containerCapacity > 0) {
            containersCount++;
        }
        float deliveryAddPrice = deliveryPrice / contactsCount;

        for (Contact contact : contacts) {
            float buuzeProportionInAll =  contact.getCount() / countAll;
            float containerAddPrice = containerPrice * containersCount * buuzeProportionInAll;

            float resultContactSum = contact.getCount() * buuzePrice + containerAddPrice + deliveryAddPrice;
            resultContactSum = new BigDecimal(resultContactSum).setScale(2, RoundingMode.UP).floatValue();
            contact.setPrice(resultContactSum);
        }

        return contacts;
    }
}
