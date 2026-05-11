package prueba2.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import prueba2.demo.DTO.RazaDTO;
import prueba2.demo.model.Raza;
import prueba2.demo.repository.RazaRepository;

@Service
@Transactional
public class RazaService {

    @Autowired
    private RazaRepository razaRepository;

    public List<RazaDTO> obtenerTodos() {
        return razaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public RazaDTO buscarPorId(Integer id) {
        Raza raza = razaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La raza no existe o no esta registrada"));
        return convertirADTO(raza);
    }

    public RazaDTO buscarPorNombre(String nombre) {
        Raza raza = razaRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("La raza no existe o no esta registrada"));
        return convertirADTO(raza);
    }

    public RazaDTO buscarPorOrigen(String origen) {
        Raza raza = razaRepository.findByNombre(origen)
                .orElseThrow(() -> new RuntimeException("El origen de esta raza no está registrado"));
        return convertirADTO(raza);
    }

    public Raza actualizarRaza(Integer id, Raza raza) {
        Raza razaExistente = razaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La raza no existe en los registros"));
        
        if (raza.getNombre() != null) {
            razaExistente.setNombre(raza.getNombre());
        }
        if (raza.getDescripcion() != null) {
            razaExistente.setDescripcion(raza.getDescripcion());
        }
        if (raza.getOrigen() != null) {
            razaExistente.setOrigen(raza.getOrigen());
        }
        return razaRepository.save(razaExistente);
    }

    public String eliminar(Integer id) {
        if (!razaRepository.existsById(id)) {
            throw new RuntimeException("Raza no encontrada");
        }
        razaRepository.deleteById(id);
        return "Eliminado Exitosamente";
    }

    private RazaDTO convertirADTO(Raza raza) {
        RazaDTO dto = new RazaDTO();
        dto.setId(raza.getId());
        dto.setNombre(raza.getNombre());
        dto.setDescripcion(raza.getDescripcion());
        dto.setOrigen(raza.getOrigen());
        return dto;
    }
}
