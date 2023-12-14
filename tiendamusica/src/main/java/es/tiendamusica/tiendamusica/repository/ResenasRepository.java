package es.tiendamusica.tiendamusica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.tiendamusica.tiendamusica.entity.Productos;
import es.tiendamusica.tiendamusica.entity.Resenas;

public interface ResenasRepository extends JpaRepository<Resenas, Integer> {

    List<Resenas> findByProducto(Productos productos);

}
