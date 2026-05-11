package prueba2.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import prueba2.demo.model.Carrera;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer>{

    List<Carrera> findByFecha(LocalDate fecha);

    List<Carrera> findByTipoCarrera(String tipoCarrera);
    

}
