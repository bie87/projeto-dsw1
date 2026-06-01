package br.ufscar.dc.dsw1.domain;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;

//import br.ufscar.dc.dsw.validation.UniqueCNPJ;

@SuppressWarnings("serial")
@Entity
@Table(name = "Loja")
public class Loja extends AbstractEntity<Long> {

    @Column(nullable = false, length = 60)
    private String email;

	@Column(nullable = false,length = 60)
	private String senha;

//	@UniqueCNPJ (message = "{Unique.loja.CNPJ}")
//	@NotBlank
	//@Size(min = 18, max = 18, message = "{Size.loja.CNPJ}")
	@Column(nullable = false, unique = true, length = 60)
	private String CNPJ;

	//@NotBlank
	//@Size(min = 3, max = 60)
	@Column(nullable = false, unique = true, length = 60)
	private String nome;

	@Column(nullable = false,length = 60)
	private String descricao;

	@OneToMany(mappedBy = "loja")
	private List<Cliente> clientes;
	
	@OneToMany(mappedBy = "loja")
    private List<Veiculo> veiculos;

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}