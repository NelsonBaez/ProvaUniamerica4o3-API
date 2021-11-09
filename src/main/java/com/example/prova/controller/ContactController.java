package com.example.prova.controller;


import com.example.prova.dto.ContactDTO;
import com.example.prova.dto.input.NewContactDTO;
import com.example.prova.model.Contact;
import com.example.prova.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/contacts")
@RequiredArgsConstructor
@RestController
public class ContactController {
    private final ContactService contactService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContactDTO> index(){
        return contactService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ContactDTO create(@Valid @RequestBody NewContactDTO newContactDTO){
        return contactService.create(newContactDTO);
    }
}
