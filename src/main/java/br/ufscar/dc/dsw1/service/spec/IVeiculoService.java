
package br.ufscar.dc.dsw1.service.spec;

import java.util.List;

import br.ufscar.dc.dsw1.domain.Veiculo;

public interface IVeiculoService {

    void salvar(Veiculo veiculo);

    void excluir(Long id);

    Veiculo buscarPorId(Long id);

    List<Veiculo> buscarTodos();

    List<Veiculo> buscarPorLoja(Long lojaId);

    List<Veiculo> buscarPorModelo(String modelo);
}

