package es.tiendamusica.tiendamusica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.tiendamusica.tiendamusica.entity.Clientes;
import es.tiendamusica.tiendamusica.entity.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Integer> {

    List<Pedidos> findByCliente(Clientes clientes);

}
