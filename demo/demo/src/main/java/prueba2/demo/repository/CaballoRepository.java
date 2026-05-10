package prueba2.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prueba2.demo.model.Caballo;

@Repository

public interface CaballoRepository extends JpaRepository<Caballo, Integer> {

   

    List<Caballo> findByRaza(String raza);

    List<Caballo> findByEdad(Integer edad);
}
