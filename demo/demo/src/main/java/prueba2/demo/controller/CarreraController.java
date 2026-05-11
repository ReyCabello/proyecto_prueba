package prueba2.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prueba2.demo.DTO.CarreraDTO;
import prueba2.demo.service.CarreraService;

@RestController
@RequestMapping("/api/v1/carreras")

public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @GetMapping
    public ResponseEntity<List<CarreraDTO>> todasLasCarreras() {
        List<CarreraDTO> carreras = carreraService.obtenerTodos();
        if(carreras.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarreraDTO> buscarPorId(@PathVariable Integer id){
        try {
            CarreraDTO carrera1 = carreraService.buscarPorId(id);
            return new ResponseEntity<>(carrera1, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tipoCarrera/{tipoCarrera}")
    public ResponseEntity<List<CarreraDTO>> buscarPorTipoCarrera(@PathVariable String tipoCarrera ) {
        List<CarreraDTO> carrera = carreraService.buscarPorTipoCarrera(tipoCarrera);
        if (carrera.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(carrera, HttpStatus.OK);
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<CarreraDTO>> buscarPorFecha(@PathVariable LocalDate fecha ) {
        List<CarreraDTO> carrera = carreraService.buscarPorFecha(fecha);
        if (carrera.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(carrera, HttpStatus.OK);
    }

}


