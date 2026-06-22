package br.ufscar.dc.dsw1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufscar.dc.dsw1.service.spec.IVeiculoService;

//import jakarta.validation.Valid;
@Controller
@RequestMapping
public record HomeController(IVeiculoService veiculoService) {

    @GetMapping("/home")
    public String home(@RequestParam(required = false) String modelo, Model model) {

        if (modelo != null && !modelo.isBlank()) {
            model.addAttribute(
                "veiculos",
                veiculoService.buscarPorModelo(modelo)
            );
        } else {
            model.addAttribute(
                "veiculos",
                veiculoService.buscarTodos()
            );
        }

        return "home";
    }
}

