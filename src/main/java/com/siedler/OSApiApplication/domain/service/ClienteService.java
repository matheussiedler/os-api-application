/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siedler.OSApiApplication.domain.service;

import com.siedler.OSApiApplication.domain.exception.DomainException;
import com.siedler.OSApiApplication.domain.model.Cliente;
import com.siedler.OSApiApplication.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author devsys-b
 */
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    
    public Cliente salvar(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
        
        // Lembre-se que o método SAVE pode ser usado para atualizar um cliente também!!!
        // ID vazio --> Novo Registro
        // ID Preenchido --> Alterar existente
        
        // Verifica se o cliente existe
        if (clienteExistente != null && !clienteExistente.equals(cliente)) {
            // Lançar exception
            throw new DomainException("Já existe um cliente cadastrado com esse email!");
        }
        
        return clienteRepository.save(cliente);
    }
    
    public void excluir(Long clienteID) {
        clienteRepository.deleteById(clienteID);
    }
}
