package com.crud.client.repositories;

import com.crud.client.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

@RestController

public interface ClientRepository extends JpaRepository<Client, Long> {

}
