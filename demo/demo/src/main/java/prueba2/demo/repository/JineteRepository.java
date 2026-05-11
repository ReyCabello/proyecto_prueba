package prueba2.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import prueba2.demo.model.Jinete;

@Repository
public interface JineteRepository extends JpaRepository<Jinete, Integer> {

    List<Jinete> findByNombre(String nombre);

    @Query("SELECT j FROM Jinete j WHERE j.anosExp >= 5 ")
    List<Jinete> buscarExperimentados();

    List<Jinete> findByEdad(Integer edad);
}
