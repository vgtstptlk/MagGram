package com.vgtstptlk.gramrestapi.repository;

import com.vgtstptlk.gramrestapi.domains.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findAll();
    Optional<Contact> findById(Long id);
    List<Contact> findAllByAuthor(String author);
    Optional<Contact> findByAuthorAndLoginContact(String author, String loginContact);
}
