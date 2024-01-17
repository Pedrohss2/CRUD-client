package com.crud.client.services;

import com.crud.client.dto.ClientDTO;
import com.crud.client.entities.Client;
import com.crud.client.repositories.ClientRepository;
import com.crud.client.services.exceptions.ResoruceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
         Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResoruceNotFound("Recurso não encontrado")
        );

         return new ClientDTO(client);
    }


    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(x -> new ClientDTO(x));
    }





}
