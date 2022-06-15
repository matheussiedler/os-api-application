/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siedler.OSApiApplication.api.controller;

import com.siedler.OSApiApplication.domain.model.OrdemServico;
import com.siedler.OSApiApplication.domain.repository.OrdemServicoRepository;
import com.siedler.OSApiApplication.domain.service.OrdemServicoService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author devsys-b
 */
@RestController
public class OrdemServicoController {
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    @Autowired
    private OrdemServicoService ordemServicoService;
    
    @GetMapping("/ordem-servico/{ordemID}")
    public ResponseEntity<OrdemServico> procurar(@PathVariable Long ordemID) {
        Optional<OrdemServico> ordem = ordemServicoRepository.findById(ordemID);
        
        if (ordem.isPresent()) {
            return ResponseEntity.ok(ordem.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/ordem-servico")
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }
    
    @PutMapping("/ordem-servico/{ordemID}")
    public ResponseEntity<OrdemServico> update(@PathVariable Long ordemID, @RequestBody OrdemServico ordem) {
        
        if (!ordemServicoRepository.existsById(ordemID)) {
            return ResponseEntity.notFound().build();
        }
        
        ordem.setId(ordemID);
        ordem = ordemServicoService.salvar(ordem);
        return ResponseEntity.ok(ordem);
    }
    
    @DeleteMapping("/ordem-servico/{ordemID}")
    public ResponseEntity<Void> deletar(@PathVariable Long ordemID) {
        if (!ordemServicoRepository.existsById(ordemID)) {
            return ResponseEntity.notFound().build();
        }
        
        ordemServicoService.deletar(ordemID);
        return ResponseEntity.noContent().build();
    }
}
