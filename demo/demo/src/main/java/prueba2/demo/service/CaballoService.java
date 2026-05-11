package prueba2.demo.service;
import java.util.List;
import java.lang.RuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import prueba2.demo.DTO.CaballoDTO;
import prueba2.demo.model.Caballo;
import prueba2.demo.repository.CaballoRepository;

@Service
@Transactional
public class CaballoService {

    @Autowired
    private CaballoRepository caballoRepository;

    public List<CaballoDTO> obtenerTodos() {
        return caballoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public CaballoDTO buscarPorId(Integer id) {
        Caballo caballo = caballoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("El caballo no existe"));
            return convertirADTO(caballo);
    }

    public String eliminar(Integer id) {
        try {
            Caballo caballo = caballoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! El caballo con ID " + id + " no existe."));
            caballoRepository.delete(caballo);
            return "El caballo" + caballo.getNombre() + " ha sido eliminado de la exitosamente";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

     public Caballo guardarCaballo(Caballo caballo) {
        return caballoRepository.save(caballo);
    }

    public Caballo actualizarCaballo(Integer id, Caballo caballo){
        Caballo caballito = caballoRepository.findById(id).orElseThrow(() -> new RuntimeException("¡El héroe no existe en los registros!"));
        if(caballo.getNombre() != null){
            caballito.setNombre(caballo.getNombre());
        }
        if(caballo.getEdad() != null){
            caballito.setEdad(caballo.getEdad());
        }
        if(caballo.getRaza() != null){
            caballito.setRaza(caballo.getRaza());
        }
        if(caballo.getCuadra() != null){
            caballito.setCuadra(caballo.getCuadra());
        }
        return caballoRepository.save(caballito);
    }

    public List<CaballoDTO> buscarPorRaza(String raza){
        return caballoRepository.findByRaza(raza).stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public List<CaballoDTO> buscarPorEdad(Integer edad){
        return caballoRepository.findByEdad(edad).stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public List<CaballoDTO> buscarPorNombre(String nombre) {
    return caballoRepository.findByNombre(nombre).stream()
            .map(this::convertirADTO)
            .toList();
}

    public List<CaballoDTO> buscarSeniors() {
        return caballoRepository.buscarSeniors().stream()
                    .map(this::convertirADTO)
                    .toList();
    }

    private CaballoDTO convertirADTO(Caballo caballo) {
        CaballoDTO dto = new CaballoDTO();
        dto.setId(caballo.getId());
        dto.setNombre(caballo.getNombre());
        dto.setEdad(caballo.getEdad());
        if (caballo.getRaza() != null) {
        dto.setRaza(caballo.getRaza());
        }
        if (caballo.getCuadra() != null) {
            dto.setCuadra(caballo.getCuadra().getNombre());
        }
        return dto;
    }


}
