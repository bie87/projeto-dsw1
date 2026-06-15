```java
package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Proposta;
import br.ufscar.dc.dsw.enums.StatusProposta;

public interface IPropostaService {

    void salvar(Proposta proposta);

    void excluir(Long id);

    Proposta buscarPorId(Long id);

    List<Proposta> buscarTodos();

    List<Proposta> buscarPorCliente(Long clienteId);

    List<Proposta> buscarPorVeiculo(Long veiculoId);

    List<Proposta> buscarPorStatus(StatusProposta status);
}
```
