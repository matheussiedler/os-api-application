/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siedler.OSApiApplication.domain.repository;

import com.siedler.OSApiApplication.domain.model.Cliente;
import com.siedler.OSApiApplication.domain.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author devsys-b
 */
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    OrdemServico findByCliente(Cliente cliente);
}
