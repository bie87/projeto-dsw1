package br.ufscar.dc.dsw1.controller;

import br.ufscar.dc.dsw1.domain.Loja;
import br.ufscar.dc.dsw1.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/lojas")
public class LojaController {

    private Object service() {
        return br.ufscar.dc.dsw1.config.SpringContext.getBean("lojaService");
    }

    private Loja buscarPorId(Long id) {
        try {
            return (Loja) service().getClass().getMethod("buscarPorId", Long.class).invoke(service(), id);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(e);
        }
    }

    private java.util.List<Loja> buscarTodos() {
        try {
            @SuppressWarnings("unchecked")
            java.util.List<Loja> lojas = (java.util.List<Loja>) service().getClass().getMethod("buscarTodos").invoke(service());
            return lojas;
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(e);
        }
    }

    private void salvar(Loja loja) {
        try {
            service().getClass().getMethod("salvar", Loja.class).invoke(service(), loja);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(e);
        }
    }

    private void excluirLoja(Long id) {
        try {
            service().getClass().getMethod("excluir", Long.class).invoke(service(), id);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(e);
        }
    }

    // Formulário de cadastro
    @GetMapping("/cadastrar")
    public String cadastrar(Model model) {
        Loja loja = new Loja();
        loja.setUsuario(new Usuario());
        model.addAttribute("loja", loja);
        return "loja/cadastro";
    }

    // Salvar nova loja
    @PostMapping("/salvar")
    public String salvar( Loja loja,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "loja/cadastro";
        }

        if (loja.getUsuario() == null) {
            loja.setUsuario(new Usuario());
        }

        loja.getUsuario().setRole("ROLE_LOJA");
        loja.getUsuario().setEnabled(true);

        salvar(loja);

        redirectAttributes.addFlashAttribute("sucesso", "Loja cadastrada com sucesso.");

        return "redirect:/lojas/cadastrar";
    }

    // Listar todas as lojas
    @GetMapping("/listar")
    public String listar(Model model) {

        model.addAttribute(
                "lojas",
                buscarTodos());

        return "loja/lista";
    }

    // Abrir formulário de edição
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id,
                         Model model) {

        Loja loja = buscarPorId(id);

        if (loja.getUsuario() == null) {
            loja.setUsuario(new Usuario());
        }

        model.addAttribute("loja", loja);

        return "loja/cadastro";
    }

    // Atualizar loja
    @PostMapping("/editar")
    public String editar( Loja loja,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "loja/cadastro";
        }

        salvar(loja);

        redirectAttributes.addFlashAttribute("sucesso", "Loja atualizada com sucesso.");

        return "redirect:/lojas/cadastrar";
    }

    // Excluir loja
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {

        excluirLoja(id);

        return "redirect:/lojas/listar";
    }
}
