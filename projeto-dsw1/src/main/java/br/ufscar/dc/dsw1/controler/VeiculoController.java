package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.ufscar.dc.dsw.domain.Veiculo;
import br.ufscar.dc.dsw.service.spec.IVeiculoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private IVeiculoService service;

    @GetMapping("/cadastrar")
    public String cadastrar(Veiculo veiculo) {
        return "veiculo/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Veiculo veiculo,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "veiculo/cadastro";
        }

        service.salvar(veiculo);

        return "redirect:/veiculos/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model) {

        model.addAttribute(
                "veiculos",
                service.buscarTodos());

        return "veiculo/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id,
                         Model model) {

        model.addAttribute(
                "veiculo",
                service.buscarPorId(id));

        return "veiculo/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Veiculo veiculo,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "veiculo/cadastro";
        }

        service.salvar(veiculo);

        return "redirect:/veiculos/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {

        service.excluir(id);

        return "redirect:/veiculos/listar";
    }

    @GetMapping("/modelo")
    public String buscarPorModelo(
            @RequestParam String modelo,
            Model model) {

        model.addAttribute(
                "veiculos",
                service.buscarPorModelo(modelo));

        return "veiculo/lista";
    }
}

