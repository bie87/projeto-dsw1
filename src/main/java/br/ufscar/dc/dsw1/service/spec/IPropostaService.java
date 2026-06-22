package br.ufscar.dc.dsw1.service.spec;

import java.util.List;

import br.ufscar.dc.dsw1.domain.Proposta;
import br.ufscar.dc.dsw1.enums.StatusProposta;

public interface IPropostaService {

    void salvar(Proposta proposta);

    void excluir(Long id);

    Proposta buscarPorId(Long id);

    Iterable<Proposta> buscarTodos();

    Iterable<Proposta> buscarPorCliente(Long clienteId);

    Iterable<Proposta> buscarPorVeiculo(Long veiculoId);

    Iterable<Proposta> buscarPorStatus(StatusProposta status);
}

