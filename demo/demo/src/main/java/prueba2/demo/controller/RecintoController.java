package prueba2.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import prueba2.demo.DTO.RecintoDTO;
import prueba2.demo.service.RecintoService;

@RestController
@RequestMapping("/api/v1/recintos")
public class RecintoController {

    @Autowired
    private RecintoService recintoService;

    @GetMapping
    public ResponseEntity<List<RecintoDTO>> todosLosRecintos() {
        List<RecintoDTO> recintos = recintoService.obtenerTodos();
        if (recintos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(recintos, HttpStatus.OK);  
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecintoDTO> buscarPorId(@PathVariable Integer id) {
        try {
            RecintoDTO recinto = recintoService.buscarPorId(id);
            return new ResponseEntity<>(recinto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<RecintoDTO> buscarPorNombre(@RequestParam String nombre) {
        try {
            RecintoDTO recinto = recintoService.buscarPorNombre(nombre);
            return new ResponseEntity<>(recinto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRecinto(@PathVariable Integer id) {
        try {
            String resultado = recintoService.eliminar(id);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Recinto no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
