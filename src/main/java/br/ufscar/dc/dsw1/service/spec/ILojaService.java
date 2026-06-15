
package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Loja;

public interface ILojaService {

    void salvar(Loja loja);

    void excluir(Long id);

    Loja buscarPorId(Long id);

    List<Loja> buscarTodos();
}

