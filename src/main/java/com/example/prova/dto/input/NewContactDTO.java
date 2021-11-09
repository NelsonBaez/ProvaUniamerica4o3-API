package com.example.prova.dto.input;

import com.example.prova.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NewContactDTO {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "phone is required")
    private String phone;

    public Contact toModel(){
        Contact contact = new Contact();
        contact.setName(getName());
        contact.setPhone(getPhone());
        contact.setEmail(getEmail());
        return contact;
    }
}
