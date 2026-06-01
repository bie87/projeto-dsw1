package br.ufscar.dc.dsw1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotBlank;


@SuppressWarnings("serial")
@Entity
@Table(name = "Administrador")
public class Administrador extends AbstractEntity<Long> {
  
  // @NotBlank
    @Column(nullable = false, length = 60)
    private String nome;
    
	@Column(nullable = false, length = 60)
     private String email;

	@Column(nullable = false,length = 60)
	private String senha;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}   