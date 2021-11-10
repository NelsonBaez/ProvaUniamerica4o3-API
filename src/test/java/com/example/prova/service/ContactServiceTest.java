package com.example.prova.service;

import com.example.prova.dto.ContactDTO;
import com.example.prova.dto.input.NewContactDTO;
import com.example.prova.exceptions.UniqueException;
import com.example.prova.model.Contact;
import com.example.prova.repository.ContactRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        var contact = correctContact();

        Mockito.when(contactRepository.findAll()).thenReturn(List.of(contact));

        var result = contactService.findAll();

        List<ContactDTO> contacts = new ArrayList<>();
        contacts.add(new ContactDTO(contact));
        assertThat(result).isEqualTo(contacts);
    }

    @Test
    void shouldCreateContact(){
        var newContactDTO = correctNewContactDTO();
        var contact = correctContact();

        Mockito.doReturn(contact).when(contactRepository).save(any(Contact.class));

        var result = contactService.create(newContactDTO);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(new ContactDTO(contact));
    }

    @Test
    void shouldReturnUniqueExceptionIfEmailNotUnique() {
        var contact = correctContact();

        Mockito.when(contactRepository.findByEmail(any())).thenReturn(Optional.of(contact));

        assertThatThrownBy(() -> contactService.ifEmailExistsReturnException("jorge")).isInstanceOf(UniqueException.class);
    }

    @Test
    void shouldReturnUniqueExceptionIfPhoneNotUnique() {
        var contact = correctContact();

        Mockito.when(contactRepository.findByPhone(any())).thenReturn(Optional.of(contact));

        assertThatThrownBy(() -> contactService.ifPhoneExistsReturnException("888888888")).isInstanceOf(UniqueException.class);
    }

    @Test
    void shouldReturnNotFoundExceptionIfContactNotFound() {
        assertThatThrownBy(() -> contactService.findById(1L)).isInstanceOf(NotFoundException.class);
    }

    Contact correctContact(){
        return Contact.builder()
               .id(1L)
               .name("jorge")
               .email("jorge@gmail.com")
               .phone("88888888")
               .build();
    }
    NewContactDTO correctNewContactDTO(){
        return NewContactDTO.builder()
                .name("jorge")
                .email("jorge@gmail.com")
                .phone("88888888")
                .build();
    }

}
