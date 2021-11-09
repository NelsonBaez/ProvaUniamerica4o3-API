package com.example.prova.service;

import com.example.prova.dto.ContactDTO;
import com.example.prova.dto.input.NewContactDTO;
import com.example.prova.exceptions.UniqueException;
import com.example.prova.model.Contact;
import com.example.prova.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public List<ContactDTO> findAll() {
        return contactRepository.findAll().stream().map(ContactDTO::new).collect(Collectors.toList());
    }

    public ContactDTO create(NewContactDTO newContactDTO) {
        ifEmailExistsReturnException(newContactDTO.getEmail());
        ifPhoneExistsReturnException(newContactDTO.getPhone());
        return new ContactDTO(contactRepository.save(newContactDTO.toModel()));
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
