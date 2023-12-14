package es.tiendamusica.tiendamusica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.tiendamusica.tiendamusica.entity.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Integer> {

    Optional<Clientes> findByNombre(String user);

    Optional<Clientes> findByNombreAndPassword(String user, String password);

    

}
