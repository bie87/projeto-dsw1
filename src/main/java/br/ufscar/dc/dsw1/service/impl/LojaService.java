package br.ufscar.dc.dsw1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufscar.dc.dsw1.dao.ILojaDAO;
import br.ufscar.dc.dsw1.domain.Loja;
import br.ufscar.dc.dsw1.service.spec.ILojaService;

@Service
public class LojaService implements ILojaService {

    @Autowired
    private ILojaDAO dao;

	@Override
	public void salvar(Loja loja) {
		dao.save(loja);
	}

	@Override
	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Override
	public Loja buscarPorId(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public List<Loja> buscarTodos() {
		return dao.findAll();
	}
}
