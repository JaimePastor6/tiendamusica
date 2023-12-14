package es.tiendamusica.tiendamusica.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.tiendamusica.tiendamusica.entity.Clientes;
import es.tiendamusica.tiendamusica.entity.Productos;
import es.tiendamusica.tiendamusica.entity.Resenas;
import es.tiendamusica.tiendamusica.repository.CategoriasRepository;
import es.tiendamusica.tiendamusica.repository.ClientesRepository;
import es.tiendamusica.tiendamusica.repository.ProductosRepository;
import es.tiendamusica.tiendamusica.repository.ResenasRepository;
import jakarta.validation.Valid;

@Controller
public class ResenaController {

    @Autowired
    private ProductosRepository productoRepository;

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    ResenasRepository resenasRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @GetMapping("/resena/anadir/{id}")
    public String resenaAnadir(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Productos> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            Resenas resena = new Resenas();
            model.addAttribute("producto", producto.get());
            resena.setProducto(producto.get());
            model.addAttribute("resena", resena);

        }

        model.addAttribute("listaCategorias", categoriasRepository.findAll());
        return "resenaalta";
    }

    @PostMapping("/resena/alta")
    public String crearResena(@Valid @ModelAttribute Resenas resena, Model model,
            RedirectAttributes redirectAttributes) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<Clientes> cliente = clientesRepository.findByNombre(authentication.getName());

        resena.setCliente(cliente.get());

        resena.setFechaResena(new Date());
        resenasRepository.save(resena);

        model.addAttribute("producto", resena.getProducto());

        model.addAttribute("listaResenas", resenasRepository.findByProducto(resena.getProducto()));

        model.addAttribute("listaCategorias", categoriasRepository.findAll());
        redirectAttributes.addFlashAttribute("message", "Reseña añadida");

        return "redirect:/producto/ver/" + resena.getProducto().getId();
    }

}
