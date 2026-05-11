package prueba2.demo.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import prueba2.demo.model.Comuna;

public interface ComunaRepository extends JpaRepository<Comuna, Integer> {

    Optional<Comuna> findByNombre(String nombre);
    List<Comuna> findByRegionId(Integer regionId);

}
