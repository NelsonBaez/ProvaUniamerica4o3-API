package com.example.prova.dto;

import com.example.prova.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO implements Serializable {
    private Long id;
    private String name;
    private String phone;
    private String email;

    public ContactDTO(Contact contact){
        id = contact.getId();
        name = contact.getName();
        phone = contact.getPhone();
        email = contact.getEmail();
    }
}
