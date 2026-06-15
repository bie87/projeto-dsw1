
package br.ufscar.dc.dsw1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.ufscar.dc.dsw.domain.Proposta;
import br.ufscar.dc.dsw.service.spec.IPropostaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private IPropostaService service;

    @GetMapping("/cadastrar")
    public String cadastrar(Proposta proposta) {
        return "proposta/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Proposta proposta,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "proposta/cadastro";
        }

        service.salvar(proposta);

        return "redirect:/propostas/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model) {

        model.addAttribute(
                "propostas",
                service.buscarTodos());

        return "proposta/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id,
                         Model model) {

        model.addAttribute(
                "proposta",
                service.buscarPorId(id));

        return "proposta/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Proposta proposta,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "proposta/cadastro";
        }

        service.salvar(proposta);

        return "redirect:/propostas/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {

        service.excluir(id);

        return "redirect:/propostas/listar";
    }
}
```
