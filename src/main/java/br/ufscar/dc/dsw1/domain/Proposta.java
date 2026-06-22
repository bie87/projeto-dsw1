package br.ufscar.dc.dsw1.domain;

import java.time.LocalDate;

import br.ufscar.dc.dsw1.enums.StatusProposta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public LocalDate getData() {
        return data;
    }   
    public void setData(LocalDate data) {
        this.data = data;
    }
    public StatusProposta getStatus() {
        return status;
    }
    public void setStatus(StatusProposta status) {
        this.status = status;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
}