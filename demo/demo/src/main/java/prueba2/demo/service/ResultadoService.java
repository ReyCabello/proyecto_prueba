package prueba2.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import prueba2.demo.DTO.CaballoDTO;
import prueba2.demo.DTO.ResultadoDTO;
import prueba2.demo.model.Caballo;
import prueba2.demo.model.Resultado;
import prueba2.demo.repository.ResultadoRepository;

@Service
@Transactional

public class ResultadoService {

    @Autowired
    private ResultadoRepository resultadoRepository;

    public List<ResultadoDTO> obtenerTodos() {
         return (List<ResultadoDTO>) resultadoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ResultadoDTO buscarPorId(Integer id) {
        Resultado resultado = resultadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El resultado con ID " + id + "no existe"));
        return convertirADTO(resultado);
    }


    public List<ResultadoDTO> buscarPrimerLugar(Caballo caballo) {
        return resultadoRepository.findByPrimerLugar(caballo).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<ResultadoDTO> buscarSegundoLugar(Caballo caballo) {
        return resultadoRepository.findByPrimerLugar(caballo).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<ResultadoDTO> buscarTercerLugar(Caballo caballo) {
        return resultadoRepository.findByPrimerLugar(caballo).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<ResultadoDTO> buscarCarreraId(Integer carreraId) {
        return resultadoRepository.findByCarreraId(carreraId).stream()
                .map(this::convertirADTO)
                .toList();
    }


    private ResultadoDTO convertirADTO(Resultado resultado) {
        ResultadoDTO dto = new ResultadoDTO();
        dto.setId(resultado.getId());
        dto.setCarreraId(resultado.getCarrera().getId());
        dto.setTipoCarrera(resultado.getCarrera().getTipoCarrera());
        dto.setFechaCarrera(resultado.getCarrera().getFecha());
        dto.setPrimerLugarNombre(resultado.getPrimerLugar().getNombre());
        dto.setSegundoLugarNombre(resultado.getSegundoLugar().getNombre());
        dto.setTercerLugarNombre(resultado.getTercerLugar().getNombre());
        return dto;      
    }

}
