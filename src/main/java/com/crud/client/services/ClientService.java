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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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

    @Transactional
    public ClientDTO insert(ClientDTO clientDTO) {
        Client client = new Client();

        copyToDto(clientDTO, client);

        client = clientRepository.save(client);

        return new ClientDTO(client);
    }


    public ClientDTO update(Long id,  ClientDTO clientDTO) {
        Client client = clientRepository.getReferenceById(id);
        copyToDto(clientDTO,client);
        client = clientRepository.save(client);

        return new ClientDTO(client);
    }

    public void delete(Long id) {
        if(!clientRepository.existsById(id)) {
            throw  new ResoruceNotFound("Resource not fount");
        }

        clientRepository.deleteById(id);
    }


    public void copyToDto(ClientDTO clientDTO, Client client) {

        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCpf());
        client.setIncome(clientDTO.getIncome());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setChildren(clientDTO.getChildren());

    }


}
