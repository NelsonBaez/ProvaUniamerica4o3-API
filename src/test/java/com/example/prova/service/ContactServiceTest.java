package com.example.prova.service;

import com.example.prova.dto.ContactDTO;
import com.example.prova.model.Contact;
import com.example.prova.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {
    @Mock
    private ContactRepository contactRepository;

    private ContactService contactService;

    @BeforeEach
    void setUp() {
        contactService = new ContactService(contactRepository);
    }

    @Test
    void shouldReturnAllContacts() {
        Contact contact = Contact.builder()
                .id(1L)
                .name("jorge")
                .email("jorge@gmail.com")
                .phone("88888888")
                .build();
        Mockito.when(contactRepository.findAll()).thenReturn(List.of(contact));

        var result = contactService.findAll();

        List<ContactDTO> contacts = new ArrayList<>();
        contacts.add(new ContactDTO(contact));
        assertThat(result).isEqualTo(contacts);
    }

}
