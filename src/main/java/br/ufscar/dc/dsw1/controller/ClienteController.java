
package br.ufscar.dc.dsw1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw1.domain.Cliente;
import br.ufscar.dc.dsw1.domain.Usuario;
import br.ufscar.dc.dsw1.service.spec.IClienteService;

//import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes")
public record ClienteController(IClienteService clienteService) {


    @GetMapping("/cadastrar")
    public String cadastrar(Model model) {
        Cliente cliente = new Cliente();
        cliente.setUsuario(new Usuario());
        model.addAttribute("cliente", cliente);
        return "cliente/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar( Cliente cliente,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "cliente/cadastro";
        }

        if (cliente.getUsuario() == null) {
            cliente.setUsuario(new Usuario());
        }

        cliente.getUsuario().setRole("ROLE_CLIENTE");
        cliente.getUsuario().setEnabled(true);
        clienteService.salvar(cliente);

        redirectAttributes.addFlashAttribute("sucesso", "Cliente cadastrado com sucesso.");

        return "redirect:/clientes/cadastrar";
    }

    @GetMapping("/listar")
    public String listar(Model model) {

        model.addAttribute(
                "clientes",
                clienteService.buscarTodos());

        return "cliente/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id,
                         Model model) {

        Cliente cliente = clienteService.buscarPorId(id);

        if (cliente.getUsuario() == null) {
            cliente.setUsuario(new Usuario());
        }

        model.addAttribute("cliente", cliente);

        return "cliente/cadastro";
    }

    @PostMapping("/editar")
    public String editar( Cliente cliente,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "cliente/cadastro";
        }

        clienteService.salvar(cliente);

        redirectAttributes.addFlashAttribute("sucesso", "Cliente atualizado com sucesso.");

        return "redirect:/clientes/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {

        clienteService.excluir(id);

        return "redirect:/clientes/listar";
    }
}