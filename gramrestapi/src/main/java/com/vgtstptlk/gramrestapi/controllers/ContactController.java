package com.vgtstptlk.gramrestapi.controllers;

import com.vgtstptlk.gramrestapi.domains.Contact;
import com.vgtstptlk.gramrestapi.exception.ContactExistException;
import com.vgtstptlk.gramrestapi.exception.UserNotFoundException;
import com.vgtstptlk.gramrestapi.repository.ContactRepository;
import com.vgtstptlk.gramrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ContactController {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    @GetMapping("{author}/contact")
    List<Contact> getContactsByAuthor(@PathVariable String author){
        return contactRepository.findAllByAuthor(author);
    }

    @PostMapping("{author}/contact")
    ResponseEntity<?> addContactToUser(@PathVariable String author, @RequestParam String login){

        userRepository.findByLogin(login).orElseThrow(
                () -> new UserNotFoundException(login)
        );

        if (contactRepository.findByAuthorAndLoginContact(author, login).isPresent()){
            throw new ContactExistException();
        }

        Contact contact = new Contact(author, login);
        contactRepository.save(contact);

        return ResponseEntity.ok(contact);
    }

    @Autowired
    public ContactController(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }
}
