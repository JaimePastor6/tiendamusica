package es.tiendamusica.tiendamusica.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import es.tiendamusica.tiendamusica.entity.Pedidos;
import es.tiendamusica.tiendamusica.entity.Productos;
import es.tiendamusica.tiendamusica.repository.CategoriasRepository;
import es.tiendamusica.tiendamusica.repository.ClientesRepository;
import es.tiendamusica.tiendamusica.repository.PedidosRepository;
import es.tiendamusica.tiendamusica.repository.ProductosRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class PedidoController {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private ProductosRepository productosRepository;

    @GetMapping("/pedido/comprar")
    public String pedidoComprar(Model model,
            RedirectAttributes redirectAttributes, HttpSession session) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Clientes cliente = new Clientes();

        Optional<Clientes> clienteSeg = clientesRepository.findByNombre(authentication.getName());

        if (clienteSeg.isPresent()) {
            cliente = clienteSeg.get();
        }

        model.addAttribute("cliente", cliente);
        model.addAttribute("listaCategorias", categoriasRepository.findAll());
        return "comprar";
    }

    @PostMapping("/pedido/lanzarPedido")
    public String lanzarPedido(@ModelAttribute Clientes cliente, Model model,
            RedirectAttributes redirectAttributes, HttpSession session) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<Clientes> clienteSeg = clientesRepository.findByNombre(authentication.getName());

        List<Productos> carrito = (List<Productos>) session.getAttribute("carrito");
         List<Productos> resumen = new ArrayList<Productos>();

        BigDecimal total = BigDecimal.ZERO;
        if (carrito != null) {
            for (Productos producto : carrito) {
                if (producto.getCantidadEnStock() > 0) {
                    producto.setCantidadEnStock(producto.getCantidadEnStock() - 1);
                    productosRepository.save(producto);
                }
                resumen.add(producto);
                total = total.add(producto.getPrecio());
            }
        }

        Clientes clienteAlta = new Clientes();

        if (clienteSeg.isPresent()) {

            clienteSeg.get().setCorreoElectronico(cliente.getCorreoElectronico());
            clienteSeg.get().setDireccionEnvio(cliente.getDireccionEnvio());
            clienteSeg.get().setNombre(cliente.getNombre());
            clientesRepository.save(clienteSeg.get());
            clienteAlta = clienteSeg.get();

        } else {

            clienteAlta.setCorreoElectronico(cliente.getCorreoElectronico());
            clienteAlta.setDireccionEnvio(cliente.getDireccionEnvio());
            clienteAlta.setNombre(cliente.getNombre());
            clienteAlta.setPassword(cliente.getPassword());
            try {
                clientesRepository.save(clienteAlta);
                
            } catch (Exception e) {
                model.addAttribute("mensaje", "Error al dar de alta el usuario");
                model.addAttribute("cliente", cliente);
                model.addAttribute("listaCategorias", categoriasRepository.findAll());
                return "comprar";
            }
           
        }

        Pedidos pedido = new Pedidos();
        pedido.setCliente(clienteAlta);
        pedido.setEstadoPedido("Registrado");
        pedido.setFechaPedido(new Date());
        pedido.setTotal(total);

        pedidosRepository.save(pedido);

        session.setAttribute("carrito", null);

        model.addAttribute("pedido", pedido);
        model.addAttribute("listaProductos", resumen);
        model.addAttribute("listaCategorias", categoriasRepository.findAll());

        return "pedidoconfirmar";
    }

    @GetMapping("/seguro/pedidos")
    public String pedidos(Model model,
            RedirectAttributes redirectAttributes, HttpSession session) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<Clientes> cliente = clientesRepository.findByNombre(authentication.getName());

        model.addAttribute("listaCategorias", categoriasRepository.findAll());

        if (cliente.isPresent()) {
            List<Pedidos> listaPedidos = pedidosRepository.findByCliente(cliente.get());
            model.addAttribute("listaPedidos", listaPedidos);
        } else {
            List<Pedidos> listaPedidos = pedidosRepository.findAll();
            model.addAttribute("listaPedidos", listaPedidos);
        }

        return "pedidos";
    }

}
