package com.cesin.esercitazione.web;

import com.cesin.esercitazione.entity.Cliente;
import com.cesin.esercitazione.repo.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.val;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository repository;


    @PostMapping("add")
    void add(@RequestBody Cliente cliente) {
        repository.save(cliente);
    }

    @PostMapping("edit")
    void edit(@RequestBody Cliente cliente) {
        val oldCliente = repository.getById(cliente.getId());
        if (oldCliente != null) repository.save(cliente);
        else throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    @PostMapping("remove/{id}")
    void remove(@PathVariable long id) {
        val cliente = repository.getById(id);
        if (cliente != null) repository.delete(cliente);
        else throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    @GetMapping("list")
    Iterable<Cliente> list() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    Cliente get(@PathVariable long id) {
        return repository.getById(id);
    }
}
