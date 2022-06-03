package com.example.cadastroClientesBasico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastroClientesBasico.model.Cliente;
import com.example.cadastroClientesBasico.repository.ClienteRepository;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping("/clientes")
    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable(value = "id") long id) {
        Optional<Cliente> c = repository.findById(id);

        if (c.isPresent()) {
            return new ResponseEntity<Cliente>(c.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/clientes")
    public Cliente cadastrar(@Validated @RequestBody Cliente novoCliente) {
        return repository.save(novoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable long id, @RequestBody Cliente cliente) {
        return repository.findById(id).map(registro -> {
            registro.setNome(cliente.getNome());
            registro.setIdade(cliente.getIdade());
            registro.setProfissao(cliente.getProfissao());
            Cliente clienteAtualziado = repository.save(registro);
            return ResponseEntity.ok().body(clienteAtualziado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
}
