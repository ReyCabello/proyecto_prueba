package prueba2.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import prueba2.demo.DTO.CuadraDTO;
import prueba2.demo.model.Cuadra;
import prueba2.demo.repository.CuadraRepository;

@Service
@Transactional
public class CuadraService {
    
    @Autowired
    private CuadraRepository cuadraRepository;

    public List<CuadraDTO> obtenerTodos() {
        return cuadraRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public CuadraDTO buscarPorId(Integer id) {
        Cuadra cuadra = cuadraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("La cuadra no existe o no está registrada"));
            return convertirADTO(cuadra);
    }

    public String eliminar(Integer id) {
        try {
            Cuadra cuadra = cuadraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! La cuadra ID " + id + " no existe."));
            cuadraRepository.delete(cuadra);
            return "La cuadra" + cuadra.getNombre() + " ha sido eliminada de la exitosamente";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

     public Cuadra guardarCuadra(Cuadra cuadra) {
        return cuadraRepository.save(cuadra);
    }

    public Cuadra actualizarCuadra(Integer id, Cuadra cuadra){
        Cuadra cuadraa = cuadraRepository.findById(id).orElseThrow(() -> new RuntimeException("¡La cuadra no existe en los registros!"));
        if(cuadra.getNombre() != null){
            cuadraa.setNombre(cuadra.getNombre());
        }
        return cuadraRepository.save(cuadraa);
    }


    public List<CuadraDTO> buscarPorNombre(String nombre){
        return cuadraRepository.findByNombre(nombre).stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    private CuadraDTO convertirADTO(Cuadra cuadra) {
        CuadraDTO dto = new CuadraDTO();
        dto.setId(cuadra.getId());
        dto.setNombre(cuadra.getNombre());
        return dto;
    }

}
