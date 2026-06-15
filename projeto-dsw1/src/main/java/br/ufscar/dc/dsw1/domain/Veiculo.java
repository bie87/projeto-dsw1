package br.ufscar.dc.dsw1.domain;

import java.math.BigDecimal;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;


@SuppressWarnings("serial")
@Entity
@Table(name = "Veiculo")
public class Veiculo extends AbstractEntity<Long> {
  
   // @NotBlank(message = "{NotBlank.veiculo.nome}")
    @Column(nullable = false, length = 60)
    private String nome;
    
  //  @NotBlank(message = "{NotBlank.veiculo.tipo}")
    @Column(nullable = false, length = 14)
    private String placa;

	@Column(nullable = false, length = 14)
    private String modelo;

	@Column(nullable = false, length = 14)
    private String chassi;

	//@NotNull(message = "{NotNull.veiculo.ano}")
    @Column(nullable = false, length = 5)
    private Integer ano;
    
	//@NotNull(message = "{NotNull.veiculo.preco}")	
	@Column(nullable = false, columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
	private BigDecimal preco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

   @ManyToOne
   @JoinColumn(name = "loja_id")
    private Loja loja; 
}