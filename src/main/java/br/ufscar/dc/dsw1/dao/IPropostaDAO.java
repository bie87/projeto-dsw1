package br.ufscar.dc.dsw1.dao;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw1.domain.Proposta;
import br.ufscar.dc.dsw1.enums.StatusProposta;


public interface IPropostaDAO extends CrudRepository<Proposta, Long> {

    Iterable<Proposta> findByClienteId(Long id);

    Iterable<Proposta> findByVeiculoId(Long id);

    Iterable<Proposta> findByStatus(StatusProposta status);

    Iterable<Proposta> findByLojaId(Long Id);

}