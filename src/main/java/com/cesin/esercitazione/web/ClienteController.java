package com.cesin.esercitazione.web;

import com.cesin.esercitazione.entity.Cliente;
import com.cesin.esercitazione.repo.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.val;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository repository;

    @GetMapping("addtest")
    void addTest() {
        repository.saveAll(List.of(
                new Cliente(1, "AlphaTauri2", "PP, 1 Faenza", true),
                new Cliente(2, "TBD", "Via Brescia, 1", true),
                new Cliente(3, "Alpine", "FR, 1 Gentt", true),
                new Cliente(4, "Test", "Via Boh 404", true)
        ));
    }


    @PostMapping("add")
    void add(@RequestBody Cliente cliente) {
        if (repository.getById(cliente.getId()) == null) repository.save(cliente);
        else throw new ResponseStatusException(HttpStatus.CONFLICT);
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

    @GetMapping("id/{id}")
    Cliente get(@PathVariable long id) {
        val cliente = repository.getById(id);
        if (cliente != null) return  cliente;
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
