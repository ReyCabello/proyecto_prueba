package prueba2.demo.service;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import prueba2.demo.DTO.CarreraDTO;
import prueba2.demo.model.Caballo;
import prueba2.demo.model.Carrera;
import prueba2.demo.model.Jinete;
import prueba2.demo.repository.CarreraRepository;

@Service
@Transactional

public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    public List<CarreraDTO> obtenerTodos() {
        return carreraRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

     public CarreraDTO buscarPorId(Integer id) {
        Carrera carrera = carreraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("La carrera con id N° " + id + "no existe"));
            return convertirADTO(carrera);
    }

    public List<CarreraDTO> buscarPorFecha(LocalDate fecha) {
    return carreraRepository.findByFecha(fecha).stream()
            .map(this::convertirADTO)
            .toList();
    }

    public List<CarreraDTO> buscarPorTipoCarrera(String tipoCarrera) {
    return carreraRepository.findByTipoCarrera(tipoCarrera).stream()
            .map(this::convertirADTO)
            .toList();
    }



    private CarreraDTO convertirADTO(Carrera carrera) {
        CarreraDTO dto = new CarreraDTO();
        dto.setId(carrera.getId());
        dto.setFecha(carrera.getFecha());
        dto.setTipoCarrera(carrera.getTipoCarrera());
        dto.setNombresCaballos(carrera.getCaballos().stream()
                                        .map(Caballo::getNombre)
                                        .toList());
        dto.setNombresJinetes(carrera.getJinetes().stream()
                                        .map(Jinete::getNombre)
                                        .toList());
        return dto;
    }

}
