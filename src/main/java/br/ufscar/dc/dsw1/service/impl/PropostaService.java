
package br.ufscar.dc.dsw1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufscar.dc.dsw1.dao.IPropostaDAO;
import br.ufscar.dc.dsw1.domain.Proposta;
import br.ufscar.dc.dsw1.enums.StatusProposta;
import br.ufscar.dc.dsw1.service.spec.IPropostaService;

@Service
public class PropostaService implements IPropostaService {

    @Autowired
    private IPropostaDAO dao;

    @Override
    public void salvar(Proposta proposta) {
        dao.save(proposta);
    }

    @Override
    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Override
    public Proposta buscarPorId(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Iterable<Proposta> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public Iterable<Proposta> buscarPorCliente(Long clienteId) {
        return dao.findByClienteId(clienteId);
    }

    @Override
    public Iterable<Proposta> buscarPorVeiculo(Long veiculoId) {
        return dao.findByVeiculoId(veiculoId);
    }

    @Override
    public Iterable<Proposta> buscarPorStatus(StatusProposta status) {
        return dao.findByStatus(status);
    }
}

