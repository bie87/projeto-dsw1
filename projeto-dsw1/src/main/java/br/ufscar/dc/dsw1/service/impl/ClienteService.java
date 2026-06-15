package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IClienteService;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteDAO dao;

    @Override
    public void salvar(Cliente cliente) {
        dao.save(cliente);
    }

    @Override
    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public List<Cliente> buscarTodos() {
        return dao.findAll();
    }
}