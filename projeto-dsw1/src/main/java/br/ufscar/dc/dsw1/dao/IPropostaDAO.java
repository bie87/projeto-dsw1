package br.ufscar.dc.dsw1.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufscar.dc.dsw.domain.Proposta;
import br.ufscar.dc.dsw.enums.StatusProposta;


public interface IPropostaDAO extends CrudRepository<Proposta, Long> {

    List<Proposta> findByClienteId(Long id);

    List<Proposta> findByVeiculoId(Long id);

    List<Proposta> findByStatus(StatusProposta status);
}