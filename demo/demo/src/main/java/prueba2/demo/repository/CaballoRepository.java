package prueba2.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prueba2.demo.model.Caballo;

@Repository

public interface CaballoRepository extends JpaRepository<Caballo, Integer> {

    private List<Caballo> caballos = new ArrayList<>();

    public List<Caballo> listarTodos() {
        return this.caballos;
    }

    public Caballo buscarPorId(Integer id) {
        for (Caballo caballo : caballos) {
            if(caballo.getId() == id){
                return caballo;
            }    
        }
         return null;
    }

    public Caballo guardarCaballo(Caballo caballo) {
        this.caballos.add(caballo);
        return caballo;
    }

    public Caballo editarCaballo(Caballo caballito) {
        for(Caballo caballo : caballos) {
            if(caballo.getId() == caballito.getId()){
                caballo.setNombre(caballito.getNombre());
                caballo.setEdad(caballito.getEdad());
                caballo.setRaza(caballito.getRaza());
                caballo.setCuadra(caballito.getCuadra());
                return caballo;
            }
        }g
        return caballito;
    }

}
