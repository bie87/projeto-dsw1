package br.ufscar.dc.dsw1.service.spec;

import java.util.List;

import br.ufscar.dc.dsw1.domain.Cliente;

public interface IClienteService {

    void salvar(Cliente cliente);

    void excluir(Long id);

    Cliente buscarPorId(Long id);

    List<Cliente> buscarTodos();
}