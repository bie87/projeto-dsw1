```java
package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufscar.dc.dsw.dao.IVeiculoDAO;
import br.ufscar.dc.dsw.domain.Veiculo;
import br.ufscar.dc.dsw.service.spec.IVeiculoService;

@Service
public class VeiculoService implements IVeiculoService {

    @Autowired
    private IVeiculoDAO dao;

    @Override
    public void salvar(Veiculo veiculo) {
        dao.save(veiculo);
    }

    @Override
    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Override
    public Veiculo buscarPorId(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public List<Veiculo> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public List<Veiculo> buscarPorLoja(Long lojaId) {
        return dao.findByLojaId(lojaId);
    }

    @Override
    public List<Veiculo> buscarPorModelo(String modelo) {
        return dao.findByModeloContainingIgnoreCase(modelo);
    }
}
```
