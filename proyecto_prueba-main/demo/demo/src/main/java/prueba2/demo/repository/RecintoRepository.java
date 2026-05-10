package prueba2.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import prueba2.demo.model.Recinto;

public interface RecintoRepository extends JpaRepository<Recinto, Integer> {

    Optional<Recinto> findByNombre(String nombre);
    List<Recinto> findByUbicacion(String ubicacion);
    List<Recinto> findByCapacidadGreaterThanEqual(Integer capacidad);

}
