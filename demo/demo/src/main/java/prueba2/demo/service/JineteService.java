package prueba2.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import prueba2.demo.DTO.JineteDTO;
import prueba2.demo.model.Jinete;
import prueba2.demo.repository.JineteRepository;

@Service
@Transactional

public class JineteService {

    @Autowired
    private JineteRepository jineteRepository;
    
    public List<JineteDTO> obtenerTodos() {
        return (List<JineteDTO>) jineteRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public JineteDTO buscarPorId(Integer id) {
        Jinete jinete = jineteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El jinete con ID " + id + "no existe"));
        return convertirADTO(jinete);
    }

    public List<JineteDTO> buscarPorEdad(Integer edad){
        return jineteRepository.findByEdad(edad).stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public List<JineteDTO> buscarPorNombre(String nombre) {
    return jineteRepository.findByNombre(nombre).stream()
            .map(this::convertirADTO)
            .toList();
    }

    public String eliminar(Integer id) {
        try {
            Jinete jinete = jineteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El jinete con ID " + id + " no existe"));
            jineteRepository.delete(jinete);
            return "El jinete '" + jinete.getNombre() + "' ha sido removido exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Jinete guardarJinete(Jinete jinete) {
        return jineteRepository.save(jinete);
    }

    public Jinete actualizarJinete(Integer id, Jinete jinete) {
        Jinete jinete1 = jineteRepository.findById(id).orElseThrow(() -> new RuntimeException("El jinete no existe"));
        if(jinete.getNombre() != null) {
            jinete1.setNombre(jinete.getNombre());
        } 
        if(jinete.getEdad() != null){
            jinete1.setEdad(jinete.getEdad());
        }
        if(jinete.getAnosExp() != null){
            jinete1.setAnosExp((jinete.getAnosExp()));
        }
        if(jinete.getTitulos() != null){
            jinete1.setTitulos(jinete.getTitulos());
        }         
        if(jinete.getComentario() != null){
            jinete1.setComentario(jinete.getComentario());
        }
        return jineteRepository.save(jinete1);
    }

    public List<JineteDTO> buscarExperimentados() {
        return jineteRepository.buscarExperimentados().stream()
                .map(this::convertirADTO)
                .toList();
    }
    


    private JineteDTO convertirADTO(Jinete jinete) {
        JineteDTO dto = new JineteDTO();
        dto.setId(jinete.getId());
        dto.setNombre(jinete.getNombre());
        dto.setEdad(jinete.getEdad());
        dto.setAnosExp(jinete.getAnosExp());
        if (jinete.getTitulos() != null) {
            dto.setTitulos(jinete.getTitulos());
        } else{
            dto.setTitulos("Este jinete no tiene títulos a su haber");;
        }
        if (jinete.getComentario() != null) {
            dto.setComentario(jinete.getComentario());
        } else{
            dto.setComentario("No hay información sobre este jinete");
        } 
        return dto;      
    }
}
