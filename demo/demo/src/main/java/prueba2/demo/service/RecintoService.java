package prueba2.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import prueba2.demo.DTO.RecintoDTO;
import prueba2.demo.model.Recinto;
import prueba2.demo.repository.RecintoRepository;

@Service
@Transactional
public class RecintoService {

    @Autowired
    private RecintoRepository recintoRepository;

    public List<RecintoDTO> obtenerTodos() {
        return recintoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public RecintoDTO buscarPorId(Integer id) {
        Recinto recinto = recintoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El recinto no existe o no esta registrado"));
        return convertirADTO(recinto);
    }

    public RecintoDTO buscarPorNombre(String nombre) {
        Recinto recinto = recintoRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("El recinto no existe o no esta registrado"));
        return convertirADTO(recinto);
    }

    public Recinto actualizarRecinto(Integer id, Recinto recinto) {
        Recinto recintoExistente = recintoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El recinto no existe en los registros"));
        
        if (recinto.getNombre() != null) {
            recintoExistente.setNombre(recinto.getNombre());
        }
        if (recinto.getUbicacion() != null) {
            recintoExistente.setUbicacion(recinto.getUbicacion());
        }
        if (recinto.getCapacidad() != null) {
            recintoExistente.setCantidadPistas(recinto.getCantidadPistas());
        }
        return recintoRepository.save(recintoExistente);
    }

    public String eliminar(Integer id) {
        if (!recintoRepository.existsById(id)) {
            throw new RuntimeException("Recinto no encontrado");
        }
        recintoRepository.deleteById(id);
        return "Eliminado Exitosamente";
    }

    private RecintoDTO convertirADTO(Recinto recinto) {
        RecintoDTO dto = new RecintoDTO();
        dto.setId(recinto.getId());
        dto.setNombre(recinto.getNombre());
        dto.setUbicacion(recinto.getUbicacion());
        dto.setCapacidad(recinto.getCapacidad());
        dto.setCantidadPistas(recinto.getCantidadPistas());
        dto.setComuna(recinto.getComuna().getNombre());
        dto.setRegion(recinto.getComuna().getRegion().getNombre());
        return dto;
    }
}
