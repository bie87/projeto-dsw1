package br.ufscar.dc.dsw1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw1.domain.Veiculo;

@SuppressWarnings("unchecked")
public interface IVeiculoDAO extends CrudRepository<Veiculo, Long> {
	
	Veiculo findById(long id);

	List<Veiculo> findAll();
	
	Veiculo save(Veiculo veiculo);

	void deleteById(Long id);

	List<Veiculo> findByLojaId(Long lojaId);
	
	 List<Veiculo> findByModeloContainingIgnoreCase(String modelo);
	
    @Query("SELECT c FROM Veiculo c WHERE c.nome = :nome")
    public Veiculo getVeiculoByNome(@Param("nome") String nome);
}