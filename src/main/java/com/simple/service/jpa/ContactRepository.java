package com.simple.service.jpa;

import com.simple.entities.Contact;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    Contact findByBuyDateAndId(DateTime dateTime, Long id);

    Contact findByBuyDateAndName(DateTime dateTime, String name);

    @Query("select contact from Contact contact where contact.buyDate=:dateTime")
    List<Contact> findByBuyDate(@Param("dateTime") DateTime dateTime);

    List<Contact> findByBuyDateBetween(DateTime from, DateTime to);

    List<Contact> findByNameOrderByBuyDateDesc(String name);
}
