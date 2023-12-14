package es.tiendamusica.tiendamusica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.tiendamusica.tiendamusica.entity.Categorias;
import es.tiendamusica.tiendamusica.entity.Productos;

public interface ProductosRepository extends JpaRepository<Productos, Integer> {

    List<Productos> findByCategoria(Optional<Categorias> categoria);

    List<Productos> findByCantidadEnStockLessThanEqual(int cantidadEnStock);

    @Query("SELECT p FROM Productos p WHERE p.nombre LIKE %:query% OR p.descripcion LIKE %:query% OR p.marca.nombre LIKE %:query% OR p.categoria.nombre LIKE %:query%")
    List<Productos> findByNombreOrDescripcionOrMarcaOrCategoria(String query);

}
