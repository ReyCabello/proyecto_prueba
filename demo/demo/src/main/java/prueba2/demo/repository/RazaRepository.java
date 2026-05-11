package prueba2.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import prueba2.demo.model.Raza;

public interface RazaRepository extends JpaRepository<Raza, Integer> {

    Optional<Raza> findByNombre(String nombre);
    List<Raza> findByOrigen(String Origen);

}
