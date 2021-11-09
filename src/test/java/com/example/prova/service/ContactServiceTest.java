package com.example.prova.service;

import com.example.prova.model.Contact;
import com.example.prova.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
                .name("jorge")
                .email("jorge@gmail.com")
                .phone("8855857858")
                .build();


        Mockito.doReturn(List.of(contact)).when(contactRepository).findAll();

        var result = contactService.findAll();

        assertThat(result).isEqualTo(List.of(contact));
    }

}
