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
import br.ufscar.dc.dsw1.dao.ILojaDAO;
import br.ufscar.dc.dsw1.domain.Loja;
import br.ufscar.dc.dsw1.domain.Proposta;
import br.ufscar.dc.dsw1.domain.Veiculo;
import br.ufscar.dc.dsw1.enums.StatusProposta;
import br.ufscar.dc.dsw1.service.spec.IPropostaService;
import br.ufscar.dc.dsw1.service.spec.IVeiculoService;

@Controller
@RequestMapping("/loja")
public class LojaAreaController {

    private IVeiculoService veiculoService() {
        return SpringContext.getBean(IVeiculoService.class);
    }

    private ILojaDAO lojaDAO() {
        return SpringContext.getBean(ILojaDAO.class);
    }

    private IPropostaService propostaService() {
        return SpringContext.getBean(IPropostaService.class);
    }

    private Loja lojaLogada(Principal principal) {
        Loja loja = lojaDAO().findByUsuarioEmail(principal.getName());
        if (loja == null) {
            throw new IllegalStateException("Loja não encontrada para o usuário autenticado");
        }
        return loja;
    }

    @GetMapping("/home")
    public String home(@RequestParam(required = false) String modelo,
                       Principal principal,
                       Model model) {
        Loja loja = lojaLogada(principal);

        if (modelo != null && !modelo.isBlank()) {
            model.addAttribute("veiculos", veiculoService().buscarPorModelo(modelo));
        } else {
            model.addAttribute("veiculos", veiculoService().buscarTodos());
        }

        model.addAttribute("loja", loja);
        return "loja/home";
    }


  
    @GetMapping("/veiculos/cadastrar")
    public String cadastrarVeiculoForm(Principal principal, Model model) {
        Loja loja = lojaLogada(principal);
        
        model.addAttribute("loja", loja);
        model.addAttribute("veiculo", new Veiculo());
        return "loja/cadastro_veiculo"; 
    }


    @PostMapping("/veiculos")
    public String salvarVeiculo(Veiculo veiculo, Principal principal) {
        Loja loja = lojaLogada(principal);
        

        veiculo.setLoja(loja); 
        veiculoService().salvar(veiculo); 
        
        return "redirect:/loja/home";
    }

    
    @GetMapping("/veiculos")
    public String meusVeiculos(Principal principal, Model model) {
        Loja loja = lojaLogada(principal);
        
  
        model.addAttribute("veiculos", veiculoService().buscarPorLoja(loja.getId())); 
        model.addAttribute("loja", loja);
        
        return "loja/pertence_veiculos";
    }


    @GetMapping("/propostas")
    public String minhasPropostas(Principal principal, Model model) {
        Loja loja = lojaLogada(principal);
        
        model.addAttribute("propostas", propostaService().buscarPorLoja(loja.getId()));
        model.addAttribute("loja", loja);
        
        return "loja/pertence_propostas";
    }
}