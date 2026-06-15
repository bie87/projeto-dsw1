package br.ufscar.dc.dsw1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw1.domain.Loja;

@SuppressWarnings("unchecked")
public interface IlojaDAO extends CrudRepository<Loja, Long>{

	Loja findById(long id);
	
	Loja findByCNPJ (String CNPJ);

	List<Loja> findAll();
	
	Loja save(Loja loja);

	void deleteById(Long id);

	 @Query("SELECT j FROM Loja j WHERE j.nome = :nome")
    public Loja getLojaByNome(@Param("nome") String nome);
}