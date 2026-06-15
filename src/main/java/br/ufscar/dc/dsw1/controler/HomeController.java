package br.ufscar.dc.dsw1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.ufscar.dc.dsw.domain.Veiculo;
import br.ufscar.dc.dsw.service.spec.IVeiculoService;

import jakarta.validation.Valid;
@Controller
public class HomeController {

    @Autowired
    private IVeiculoService veiculoService;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute(
            "veiculos",
            veiculoService.buscarTodos()
        );

        return "index";
    }
}

