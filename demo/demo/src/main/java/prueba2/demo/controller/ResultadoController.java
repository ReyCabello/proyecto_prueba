package prueba2.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prueba2.demo.DTO.ResultadoDTO;
import prueba2.demo.service.CaballoService;
import prueba2.demo.service.ResultadoService;


@RestController
@RequestMapping("/api/v1/resultados")

public class ResultadoController {

    @Autowired
    private ResultadoService resultadoService;
    
    @GetMapping
    public ResponseEntity<List<ResultadoDTO>> todosLosResultados() {
        List<ResultadoDTO> resultado = resultadoService.obtenerTodos();
        if(resultado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultadoDTO> buscarPorId(@PathVariable Integer id){
        try {
            ResultadoDTO resultado = resultadoService.buscarPorId(id);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/carrera/{carreraId}")
    public ResponseEntity<List<ResultadoDTO>> buscarPorCarreraId(@PathVariable Integer carreraId) {
        List<ResultadoDTO> resultado = resultadoService.buscarCarreraId(carreraId);
        if(resultado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }
   
}
