package com.example.prova.repository;

import com.example.prova.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByEmail(String username);

    Optional<Contact> findByPhone(String phone);
}
