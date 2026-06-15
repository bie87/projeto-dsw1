
package br.ufscar.dc.dsw1.controller;

import br.ufscar.dc.dsw.domain.Loja;
import br.ufscar.dc.dsw.service.spec.ILojaService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lojas")
public class LojaController {

    @Autowired
    private ILojaService service;

    // Formulário de cadastro
    @GetMapping("/cadastrar")
    public String cadastrar(Loja loja) {
        return "loja/cadastro";
    }

    // Salvar nova loja
    @PostMapping("/salvar")
    public String salvar(@Valid Loja loja,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "loja/cadastro";
        }

        service.salvar(loja);

        return "redirect:/lojas/listar";
    }

    // Listar todas as lojas
    @GetMapping("/listar")
    public String listar(Model model) {

        model.addAttribute(
                "lojas",
                service.buscarTodos());

        return "loja/lista";
    }

    // Abrir formulário de edição
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id,
                         Model model) {

        model.addAttribute(
                "loja",
                service.buscarPorId(id));

        return "loja/cadastro";
    }

    // Atualizar loja
    @PostMapping("/editar")
    public String editar(@Valid Loja loja,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "loja/cadastro";
        }

        service.salvar(loja);

        return "redirect:/lojas/listar";
    }

    // Excluir loja
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {

        service.excluir(id);

        return "redirect:/lojas/listar";
    }
}
```
