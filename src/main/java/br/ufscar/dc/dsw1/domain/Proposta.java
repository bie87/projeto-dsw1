package br.ufscar.dc.dsw1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "Proposta")
public class Proposta extends AbstractEntity<Long> {

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private LocalDate data;

   @Enumerated(EnumType.STRING)
   private StatusProposta status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    // getters e setters
}