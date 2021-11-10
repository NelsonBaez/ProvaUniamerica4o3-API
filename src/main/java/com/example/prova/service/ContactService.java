package com.example.prova.service;

import com.example.prova.dto.ContactDTO;
import com.example.prova.dto.input.NewContactDTO;
import com.example.prova.exceptions.UniqueException;
import com.example.prova.model.Contact;
import com.example.prova.repository.ContactRepository;
import javassist.NotFoundException;
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
    public void ifEmailExistsReturnException(String email, Long id){
        var contact = contactRepository.findByEmail(email);
        if(contact.isPresent() && !contact.get().getId().equals(id)){
            throw new UniqueException("Email já está cadastrado");
        }
    }

    public void ifPhoneExistsReturnException(String phone){
        var contact = contactRepository.findByPhone(phone);
        if(contact.isPresent()){
            throw new UniqueException("Telefone já está cadastrado");
        }
    }
    public void ifPhoneExistsReturnException(String phone, Long id){
        var contact = contactRepository.findByPhone(phone);
        if(contact.isPresent() && !contact.get().getId().equals(id)){
            throw new UniqueException("Telefone já está cadastrado");
        }
    }

    public ContactDTO findById(long id) throws NotFoundException {
        Contact contact = findUserByIdOrReturnException(id);
        return new ContactDTO(contact);
    }

    private Contact findUserByIdOrReturnException(long id) throws NotFoundException {
        return contactRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Contact by ID:" + id));
    }

    public String deleteById(long id) throws NotFoundException {
        findUserByIdOrReturnException(id);
        contactRepository.deleteById(id);
        return "Deletado com sucesso";
    }

    public ContactDTO update(long id, NewContactDTO newContactDTO) throws NotFoundException {
        Contact contact = findUserByIdOrReturnException(id);
        ifEmailExistsReturnException(newContactDTO.getEmail(), id);
        ifPhoneExistsReturnException(newContactDTO.getPhone(), id);
        contact.setName(newContactDTO.getName());
        contact.setPhone(newContactDTO.getPhone());
        contact.setEmail(newContactDTO.getEmail());
        return new ContactDTO(contact);
    }
}
