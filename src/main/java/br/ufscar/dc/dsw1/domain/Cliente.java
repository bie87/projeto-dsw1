package br.ufscar.dc.dsw1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotBlank;


@SuppressWarnings("serial")
@Entity
@Table(name = "Cliente")
public class Cliente extends AbstractEntity<Long> {
  
  // @NotBlank
    @Column(nullable = false, length = 60)
    private String nome;
    
	@Column(nullable = false, length = 60)
     private String email;

	@Column(nullable = false,length = 60)
	private String senha;
  //  @NotBlank
    @Column(nullable = false, length = 14)
    private String CPF;

	@Column(nullable = false,length = 13)
	private int telefone;
	
	@Column(nullable = false,length =20)
	private String data_nascimento;

   @Column(nullable = false,length =10)
	private String sexo;


	@ManyToOne
	@JoinColumn(name = "loja-id")
	private Loja loja; 
	public String getNome() {
		return nome;
	}
	
	public void setName(String nome) {
		this.nome = nome;
	}
	
	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}
   
}