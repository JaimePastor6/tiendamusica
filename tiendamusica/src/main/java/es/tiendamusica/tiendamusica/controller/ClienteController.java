package es.tiendamusica.tiendamusica.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.tiendamusica.tiendamusica.entity.Clientes;
import es.tiendamusica.tiendamusica.repository.CategoriasRepository;
import es.tiendamusica.tiendamusica.repository.ClientesRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class ClienteController {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @GetMapping("/cliente/alta")
    public String pedidoComprar(Model model,
            RedirectAttributes redirectAttributes, HttpSession session) {

        Clientes cliente = new Clientes();

        model.addAttribute("cliente", cliente);
        model.addAttribute("listaCategorias", categoriasRepository.findAll());
        return "clientealta";
    }

    @PostMapping("/cliente/procesaralta")
    public String lanzarPedido(@ModelAttribute Clientes cliente,
            Model model, RedirectAttributes redirectAttributes, HttpSession session) {

        try {
            clientesRepository.save(cliente);
            redirectAttributes.addFlashAttribute("mensaje", "Alta realizada");
            return "redirect:/login";
        } catch (Exception e) {
             model.addAttribute("mensaje", "Error al dar de alta el usuario");
             model.addAttribute("cliente", cliente);
             model.addAttribute("listaCategorias", categoriasRepository.findAll());
             return "clientealta";
        }

        

        

    }

}
