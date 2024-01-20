package com.crud.client.services;

import com.crud.client.dto.ClientDTO;
import com.crud.client.entities.Client;
import com.crud.client.repositories.ClientRepository;
import com.crud.client.services.exceptions.DataBaseException;
import com.crud.client.services.exceptions.ResourceNotFoundExceptions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
         Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundExceptions("Recurso não encontrado")
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

    @Transactional
    public ClientDTO update(Long id,  ClientDTO clientDTO) {
        try {
            Client client = clientRepository.getReferenceById(id);
            copyToDto(clientDTO, client);
            client = clientRepository.save(client);

            return new ClientDTO(client);
        }
        catch (ResourceNotFoundExceptions resourceNotFoundExceptions) {
            throw new ResourceNotFoundExceptions("Recurso não encontado para fazer a atualixação");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if(!clientRepository.existsById(id)) {
            throw new ResourceNotFoundExceptions("Recurso não encontrado");
        }

        try {
            clientRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException error) {
            throw new DataBaseException("Erro de integridade relacional:" + error.getMessage());
        }
    }


    public void copyToDto(  ClientDTO clientDTO, Client client) {

        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCpf());
        client.setIncome(clientDTO.getIncome());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setChildren(clientDTO.getChildren());

    }


}
