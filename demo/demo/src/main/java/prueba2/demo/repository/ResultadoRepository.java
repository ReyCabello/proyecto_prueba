package prueba2.demo.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prueba2.demo.model.Caballo;
import prueba2.demo.model.Resultado;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Integer> {

    //@Query("SELECT c FROM Resultado c WHERE c.ganador.nombre = :nombre")
    //List<Resultado> findByGanador(@Param("nombre") String nombre);//Este no se usa solo lo artícule

    List<Resultado> findByPrimerLugar(Caballo caballo);

    List<Resultado> findBySegundoLugar(Caballo caballo);

    List<Resultado> findByTercerLugar(Caballo caballo);

    List<Resultado> findByCarreraId(Integer carreraId);


    @Query("SELECT COUNT(r) FROM Resultado r WHERE r.caballo.id = :id AND r.posicion = 1")
    int contarVictoriasPorCaballo(@Param("id") Integer id);
}

