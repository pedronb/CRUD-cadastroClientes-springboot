package com.example.cadastroClientesBasico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.example.cadastroClientesBasico.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findById(long id);

    ResponseEntity<Void> deleteById(long id);
    
}
