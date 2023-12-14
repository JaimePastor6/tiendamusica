package es.tiendamusica.tiendamusica.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.tiendamusica.tiendamusica.entity.Categorias;
import es.tiendamusica.tiendamusica.entity.Productos;
import es.tiendamusica.tiendamusica.repository.CategoriasRepository;
import es.tiendamusica.tiendamusica.repository.ProductosRepository;
import es.tiendamusica.tiendamusica.repository.ResenasRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProductoController {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private ProductosRepository productoRepository;

    @Autowired
    ResenasRepository resenasRepository;

    @Value("${aplicacion.imagenes.path}")
    private String pathImagenes;

    @GetMapping("/producto/listaCategoria/{id}")
    public String productoCategorias(@PathVariable("id") Integer id, Model model,
            RedirectAttributes redirectAttributes) {
        List<Productos> listaProductos = new ArrayList<>();

        model.addAttribute("listaCategorias", categoriasRepository.findAll());
        Optional<Categorias> categoria = categoriasRepository.findById(id);
        if (categoria.isPresent()) {
            listaProductos = productoRepository.findByCategoria(categoria);
            model.addAttribute("listaProductos", listaProductos);
        }

        return "listaProductos";
    }

    @GetMapping("/producto/ver/{id}")
    public String productoVer(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Productos> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
            model.addAttribute("listaResenas", resenasRepository.findByProducto(producto.get()));
        }
        model.addAttribute("listaCategorias", categoriasRepository.findAll());
        return "producto";
    }

    @GetMapping("/producto/anadirCarrito/{id}")
    public String productoAnadirCarrito(@PathVariable("id") Integer id, Model model,
            RedirectAttributes redirectAttributes, HttpSession session) {
        Optional<Productos> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
            model.addAttribute("listaResenas", resenasRepository.findByProducto(producto.get()));

            List<Productos> carrito = (List<Productos>) session.getAttribute("carrito");
            if (carrito == null) {
                carrito = new ArrayList<>();
            }
            carrito.add(producto.get());
            session.setAttribute("carrito", carrito);
            model.addAttribute("mensaje", "El producto se ha añadido al carrito");
        }
        model.addAttribute("listaCategorias", categoriasRepository.findAll());
        return "producto";
    }

    @GetMapping("/producto/verCarrito")
    public String productoVerCarrito(Model model,
            RedirectAttributes redirectAttributes, HttpSession session) {

        List<Productos> carrito = (List<Productos>) session.getAttribute("carrito");
        model.addAttribute("carrito", carrito);

        BigDecimal total = BigDecimal.ZERO;
        if (carrito != null) {
            for (Productos producto : carrito) {
                total = total.add(producto.getPrecio());
            }
        }
        model.addAttribute("total", total);
        model.addAttribute("listaCategorias", categoriasRepository.findAll());

        return "carrito";
    }

    @GetMapping("/producto/imagenes/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = new FileSystemResource(pathImagenes + filename);
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(file);
    }

    @GetMapping("/producto/busqueda")
    public String productoBusqueda(@RequestParam String query, Model model,
            RedirectAttributes redirectAttributes) {

        model.addAttribute("listaCategorias", categoriasRepository.findAll());
        List<Productos> listaProductos = productoRepository.findByNombreOrDescripcionOrMarcaOrCategoria(query);

        model.addAttribute("listaProductos", listaProductos);

        return "listaProductos";
    }
}
