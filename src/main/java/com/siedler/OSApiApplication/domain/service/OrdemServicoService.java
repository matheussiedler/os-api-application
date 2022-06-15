/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siedler.OSApiApplication.domain.service;

import com.siedler.OSApiApplication.domain.exception.DomainException;
import com.siedler.OSApiApplication.domain.model.OrdemServico;
import com.siedler.OSApiApplication.domain.model.StatusOrdemServico;
import com.siedler.OSApiApplication.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author devsys-b
 */
@Service
public class OrdemServicoService {
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    public OrdemServico criar(OrdemServico ordemServico) {
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());
        
        return ordemServicoRepository.save(ordemServico);
    }
    
    public OrdemServico finalizar(OrdemServico ordemServico) {
        ordemServico.setStatus(StatusOrdemServico.FINALIZADA);
        ordemServico.setDataFinalizacao(LocalDateTime.now());
        
        return ordemServicoRepository.save(ordemServico);
    }
    
    public OrdemServico salvar(OrdemServico cliente) {
        OrdemServico ordemExistente = ordemServicoRepository.findByCliente(cliente.getCliente());
        
        if (ordemExistente != null && !ordemExistente.equals(cliente)) {
            throw new DomainException("Já existe um cliente cadastrado com esse email.");
        }
        
        return ordemServicoRepository.save(cliente);
    }
    
    public void deletar(Long clienteID) {
        ordemServicoRepository.deleteById(clienteID);
    }
}
