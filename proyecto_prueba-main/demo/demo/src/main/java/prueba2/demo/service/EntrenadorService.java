package prueba2.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import prueba2.demo.DTO.EntrenadorDTO;
import prueba2.demo.model.Entrenador;
import prueba2.demo.repository.EntrenadorRepository;

@Service
@Transactional
public class EntrenadorService {

     @Autowired
     private EntrenadorRepository entrenadorRepository;

     public List<EntrenadorDTO> obtenerTodos() {
        return entrenadorRepository.findAll().stream()
               .map(this::convertirADTO)
               .toList();

     }

     public EntrenadorDTO buscarPorId(Integer id) {
        Entrenador entrenador = entrenadorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("El entrenador no existe o no esta registrado"));
        return convertirADTO(entrenador);

     }

     public String eliminar(Integer id) {
         if (!entrenadorRepository.existsById(id)) {
            throw new RuntimeException("Entrenador no encontrado");
         }
         entrenadorRepository.deleteById(id);
         return "Eliminado Exitosamente";
      
   }

   public Entrenador guardarEntrenador(Entrenador entrenador) {
      return entrenadorRepository.save(entrenador);

   }

   public Entrenador actualizarEntrenador(Integer id, Entrenador entrenador){
      Entrenador entrenadoor = entrenadorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡El entrenador no existe en los registros"));
      
      if (entrenador.getNombre() !=null) {
          entrenadoor.setNombre(entrenador.getNombre());
      }
      if (entrenador.getAnos_exp() !=null) {
         entrenadoor.setAnos_exp(entrenador.getAnos_exp());
      }
      if (entrenador.getComentario() != null) {
         entrenadoor.setComentario(entrenador.getComentario());
      }
      return entrenadorRepository.save(entrenador);
   }

   public List<EntrenadorDTO> buscarPorNombre(String nombre){
      return entrenadorRepository.findByNombre(nombre).stream()
               .map(this::convertirADTO)
               .toList();

   }
   public List<EntrenadorDTO> buscarPorExperienciaMinima(Integer anos_exp) {
    return entrenadorRepository.buscarPorExperienciaMinima(anos_exp).stream()
            .map(this::convertirADTO)
            .toList();
   
   }

   private EntrenadorDTO convertirADTO(Entrenador entrenador) {
      EntrenadorDTO dto = new EntrenadorDTO();
      dto.setId(entrenador.getId());
      dto.setNombre(entrenador.getNombre());
      return dto;
   }
}
