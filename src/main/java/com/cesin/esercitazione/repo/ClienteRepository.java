package com.cesin.esercitazione.repo;

import com.cesin.esercitazione.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    default Cliente getById(long id) {
        return findById(id).orElse(null);
    }
}
