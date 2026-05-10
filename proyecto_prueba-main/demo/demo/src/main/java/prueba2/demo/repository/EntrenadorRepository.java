package prueba2.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import prueba2.demo.model.Entrenador;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer> {

    List<Entrenador> findByNombre(String nombre);
    List<Entrenador> findByEspecialidad(String especialidad);
    List<Entrenador> findByNacionalidad(String nacionalidad);
    @Query("SELECT e FROM Entrenador e WHERE e.anos_exp >= :anos_exp")
List<Entrenador> buscarPorExperienciaMinima(@Param("anos_exp") Integer anos_exp);
}
