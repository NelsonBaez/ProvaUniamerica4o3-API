package com.example.prova.service;

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
}
