package prueba2.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import prueba2.demo.DTO.ComunaDTO;
import prueba2.demo.model.Comuna;
import prueba2.demo.repository.ComunaRepository;

@Service
@Transactional
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    public List<ComunaDTO> obtenerTodos() {
        return comunaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ComunaDTO buscarPorId(Integer id) {
        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La comuna no existe o no esta registrada"));
        return convertirADTO(comuna);
    }

    public ComunaDTO buscarPorNombre(String nombre) {
        Comuna comuna = comunaRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("La comuna no existe o no esta registrada"));
        return convertirADTO(comuna);      
    }

    public List<ComunaDTO> buscarPorRegion(Integer regionId) {
        return comunaRepository.findByRegionId(regionId).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public String eliminar(Integer id) {
        if (!comunaRepository.existsById(id)) {
            throw new RuntimeException("Comuna no encontrada");
        }
        comunaRepository.deleteById(id);
        return "Eliminado Exitosamente";
    }

    private ComunaDTO convertirADTO(Comuna comuna) {
        ComunaDTO dto = new ComunaDTO();
        dto.setId(comuna.getId());
        dto.setNombre(comuna.getNombre());
        dto.setRegion(comuna.getRegion().getNombre());
        return dto;
    }
}
