
package br.ufscar.dc.dsw1.service.spec;

import java.util.List;

import br.ufscar.dc.dsw1.domain.Loja;

public interface ILojaService {

    void salvar(Loja loja);

    void excluir(Long id);

    Loja buscarPorId(Long id);

    List<Loja> buscarTodos();
}

