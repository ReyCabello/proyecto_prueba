package prueba2.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import prueba2.demo.model.Cuadra;

public interface CuadraRepository extends JpaRepository<Cuadra, Integer> {

    List<Cuadra> findByNombre(String nombre);

   

}
