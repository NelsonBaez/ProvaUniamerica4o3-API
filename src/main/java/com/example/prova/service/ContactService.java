package com.example.prova.service;

import com.example.prova.exceptions.UniqueException;
import com.example.prova.model.Contact;
import com.example.prova.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public Contact create(Contact contact) {
        ifEmailExistsReturnException(contact.getEmail());
        ifPhoneExistsReturnException(contact.getPhone());
        return contactRepository.save(contact);
    }

    public void ifEmailExistsReturnException(String email){
        var contact = contactRepository.findByEmail(email);
        if(contact.isPresent()){
            throw new UniqueException("Email já está cadastrado");
        }
    }

    public void ifPhoneExistsReturnException(String phone){
        var contact = contactRepository.findByPhone(phone);
        if(contact.isPresent()){
            throw new UniqueException("Telefone já está cadastrado");
        }
    }
}
