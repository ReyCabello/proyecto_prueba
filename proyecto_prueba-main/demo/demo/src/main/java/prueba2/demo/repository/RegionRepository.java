package prueba2.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import prueba2.demo.model.Region;


public interface RegionRepository extends JpaRepository<Region, Integer> {

    Optional<Region> findByNombre(String nombre);

}
