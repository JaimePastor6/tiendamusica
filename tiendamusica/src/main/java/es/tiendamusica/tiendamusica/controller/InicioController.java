package es.tiendamusica.tiendamusica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.tiendamusica.tiendamusica.entity.Clientes;
import es.tiendamusica.tiendamusica.entity.Productos;
import es.tiendamusica.tiendamusica.repository.CategoriasRepository;
import es.tiendamusica.tiendamusica.repository.PedidosRepository;
import es.tiendamusica.tiendamusica.repository.ProductosRepository;

@Controller
public class InicioController {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private ProductosRepository productoRepository;

    @GetMapping("/")
    public String inicio(Model model) {
        return "redirect:/inicio";
    }

    @GetMapping("/inicio")
    public String inicioUsuario(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ("admin".equals(authentication.getName())) {
            List<Productos> listaProductos = productoRepository.findByCantidadEnStockLessThanEqual(1);
            model.addAttribute("listaProductos", listaProductos);
        }

        model.addAttribute("nombreUsuario", authentication.getName());
        model.addAttribute("listaCategorias", categoriasRepository.findAll());
        return "inicio";

    }

    @GetMapping("/login")
    public String login(Model model) {
        Clientes cliente = new Clientes();
        model.addAttribute("listaCategorias", categoriasRepository.findAll());
        model.addAttribute("usuario", cliente);
        return "login";
    }

}