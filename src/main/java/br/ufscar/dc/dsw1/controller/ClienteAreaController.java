package br.ufscar.dc.dsw1.controller;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufscar.dc.dsw1.config.SpringContext;
import br.ufscar.dc.dsw1.dao.IClienteDAO;
import br.ufscar.dc.dsw1.domain.Cliente;
import br.ufscar.dc.dsw1.domain.Proposta;
import br.ufscar.dc.dsw1.domain.Veiculo;
import br.ufscar.dc.dsw1.enums.StatusProposta;
import br.ufscar.dc.dsw1.service.spec.IPropostaService;
import br.ufscar.dc.dsw1.service.spec.IVeiculoService;

@Controller
@RequestMapping("/cliente")
public class ClienteAreaController {

	private IVeiculoService veiculoService() {
		return SpringContext.getBean(IVeiculoService.class);
	}

	private IClienteDAO clienteDAO() {
		return SpringContext.getBean(IClienteDAO.class);
	}

	private IPropostaService propostaService() {
		return SpringContext.getBean(IPropostaService.class);
	}

	private Cliente clienteLogado(Principal principal) {
		Cliente cliente = clienteDAO().findByUsuarioEmail(principal.getName());
		if (cliente == null) {
			throw new IllegalStateException("Cliente não encontrado para o usuário autenticado");
		}
		return cliente;
	}

	@GetMapping("/home")
	public String home(@RequestParam(required = false) String modelo,
					   Principal principal,
					   Model model) {
		Cliente cliente = clienteLogado(principal);

		if (modelo != null && !modelo.isBlank()) {
			model.addAttribute("veiculos", veiculoService().buscarPorModelo(modelo));
		} else {
			model.addAttribute("veiculos", veiculoService().buscarTodos());
		}

		model.addAttribute("cliente", cliente);
		return "cliente/home";
	}

	@GetMapping("/propostas/nova/{veiculoId}")
	public String novaProposta(@PathVariable Long veiculoId,
							  Principal principal,
							  Model model) {
		Cliente cliente = clienteLogado(principal);
		Veiculo veiculo = veiculoService().buscarPorId(veiculoId);

		model.addAttribute("cliente", cliente);
		model.addAttribute("veiculo", veiculo);
		model.addAttribute("proposta", new Proposta());
		return "cliente/proposta";
	}

	@PostMapping("/propostas/salvar")
	public String salvarProposta(@RequestParam Long veiculoId,
								 @RequestParam Double valor,
								 Principal principal) {
		Cliente cliente = clienteLogado(principal);
		Veiculo veiculo = veiculoService().buscarPorId(veiculoId);

		Proposta proposta = new Proposta();
		proposta.setCliente(cliente);
		proposta.setVeiculo(veiculo);
		proposta.setValor(valor);
		proposta.setData(LocalDate.now());
		proposta.setStatus(StatusProposta.ABERTO);
		propostaService().salvar(proposta);

		return "redirect:/cliente/propostas/minhas";
	}

	@GetMapping("/propostas/minhas")
	public String minhasPropostas(Principal principal, Model model) {
		Cliente cliente = clienteLogado(principal);
		model.addAttribute("propostas", propostaService().buscarPorCliente(cliente.getId()));
		model.addAttribute("cliente", cliente);
		return "cliente/minhas-propostas";
	}
}