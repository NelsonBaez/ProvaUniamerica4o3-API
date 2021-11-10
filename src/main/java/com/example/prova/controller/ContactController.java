package com.example.prova.controller;


import com.example.prova.dto.ContactDTO;
import com.example.prova.dto.input.NewContactDTO;
import com.example.prova.model.Contact;
import com.example.prova.service.ContactService;
import javassist.NotFoundException;
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
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@Valid @RequestBody NewContactDTO newContactDTO){
        return contactService.create(newContactDTO);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContactDTO show(@PathVariable long id) throws NotFoundException {
        return contactService.findById(id);
    }

    @DeleteMapping(path ={"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable long id) throws NotFoundException {
        return contactService.deleteById(id);
    }

    @PutMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContactDTO update(@PathVariable long id,
                          @Valid @RequestBody NewContactDTO newContactDTO) throws NotFoundException {
        return contactService.update(id, newContactDTO);
    }

}
