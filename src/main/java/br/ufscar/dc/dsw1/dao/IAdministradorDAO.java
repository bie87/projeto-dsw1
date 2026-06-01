package br.ufscar.dc.dsw1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw1.domain.Administrador;

@SuppressWarnings("unchecked")
public interface IAdministradorDAO extends CrudRepository<Administrador, Long> {
	
	Administrador findById(long id);

	List<Administrador> findAll();
	
	Administrador save(Administrador administrador);

	void deleteById(Long id);
	
    @Query("SELECT c FROM Administrador c WHERE c.nome = :nome")
    public Administrador getAdministradorByUsername(@Param("nome") String nome);
}
