package prueba2.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import prueba2.demo.DTO.CuadraDTO;
import prueba2.demo.model.Cuadra;
import prueba2.demo.service.CuadraService;

@RestController
@RequestMapping("/api/v1/cuadras")

public class CuadraController {

    @Autowired
    private CuadraService cuadraService;

    @GetMapping
    public ResponseEntity<List<CuadraDTO>> todasLasCuadras() {
        List<CuadraDTO> cuadras = cuadraService.obtenerTodos();
        if(cuadras.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cuadras, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CuadraDTO> buscarPorId(@PathVariable Integer id){
        try {
            CuadraDTO cuadraa = cuadraService.buscarPorId(id);
            return new ResponseEntity<>(cuadraa, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Cuadra> editarCuadra(@PathVariable Integer id, @RequestBody Cuadra cuadra) {
        try {
            Cuadra editado = cuadraService.guardarCuadra(cuadra);
            return new ResponseEntity<>(editado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Cuadra> actualizarCuadra(@PathVariable Integer id, @RequestBody Cuadra cuadra) {
        try{
            Cuadra newCuadra = cuadraService.actualizarCuadra(id, cuadra);
            return new ResponseEntity<>(newCuadra, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCuadra(@PathVariable Integer id) {
        String resultado = cuadraService.eliminar(id);
        if (resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

}
