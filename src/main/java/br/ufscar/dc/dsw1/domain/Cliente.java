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
    

  //  @NotBlank
    @Column(nullable = false, length = 14)
    private String CPF;

	@Column(nullable = false,length = 13)
	private String telefone;
	
	@Column(nullable = false,length =20)
	private LocalDate data_nascimento;

   @Column(nullable = false,length =10)
	private String sexo;

	 @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

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