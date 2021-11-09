package com.example.prova.controller;


import com.example.prova.model.Contact;
import com.example.prova.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/contacts")
@RequiredArgsConstructor
@RestController
public class ContactController {
    private final ContactService contactService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Contact> index(){
        return contactService.findAll();
    }
}
