
package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.ClienteService;
import br.ufscar.dc.dsw.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/cadastrar")
    public String cadastrar(Cliente cliente) {
        return "cliente/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Cliente cliente,
                         BindingResult result,
                         Model model) {

        if (result.hasErrors()) {
            return "cliente/cadastro";
        }

        Usuario usuario = cliente.getUsuario();

        usuario.setPassword(
                passwordEncoder.encode(usuario.getPassword()));

        usuario.setRole("ROLE_CLIENTE");
        usuario.setEnabled(true);

        usuarioService.salvar(usuario);

        cliente.setUsuario(usuario);

        clienteService.salvar(cliente);

        return "redirect:/clientes/listar";
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

        model.addAttribute("cliente", cliente);

        return "cliente/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Cliente cliente,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "cliente/cadastro";
        }

        clienteService.salvar(cliente);

        return "redirect:/clientes/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {

        clienteService.excluir(id);

        return "redirect:/clientes/listar";
    }
}
```
