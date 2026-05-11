package prueba2.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import prueba2.demo.DTO.RegionDTO;
import prueba2.demo.model.Region;
import prueba2.demo.repository.RegionRepository;

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<RegionDTO> obtenerTodos() {
        return regionRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public RegionDTO buscarPorId(Integer id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La region no existe o no esta registada"));
        return convertirADTO(region);         
    }

    public RegionDTO buscarPorNombre(String nombre) {
        Region region = regionRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("La region no existe o no esta registrada"));
        return convertirADTO(region);
    }

    public String eliminar(Integer id) {
        if (!regionRepository.existsById(id)) {
            throw new RuntimeException("Region no encontrada");
        }
        regionRepository.deleteById(id);
        return "Eliminado Exitosamente";
    }

    private RegionDTO convertirADTO(Region region) {
        RegionDTO dto = new RegionDTO();
        dto.setId(region.getId());
        dto.setNombre(region.getNombre());
        return dto;
    }
}
