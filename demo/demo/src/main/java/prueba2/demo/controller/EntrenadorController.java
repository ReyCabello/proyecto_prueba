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


import prueba2.demo.DTO.EntrenadorDTO;
import prueba2.demo.model.Entrenador;
import prueba2.demo.service.EntrenadorService;

@RestController
@RequestMapping("/api/v1/Entrenadores")
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    @GetMapping
    public ResponseEntity<List<EntrenadorDTO>> todosLosEntrenadores() {
        List<EntrenadorDTO> entrenadores = entrenadorService.obtenerTodos();
        if(entrenadores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(entrenadores, HttpStatus.OK);
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntrenadorDTO> buscarPorId(@PathVariable Integer id){
        try{
            EntrenadorDTO entrenadoor = entrenadorService.buscarPorId(id);
            return new ResponseEntity<>(entrenadoor, HttpStatus.OK);
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    
    }

    @GetMapping("/experienciaMinima")
    public ResponseEntity<List<EntrenadorDTO>> buscarPorExperienciaMinima(@PathVariable Integer anos_exp) {
        try {
            List<EntrenadorDTO> entrenadores = entrenadorService.buscarPorExperienciaMinima(anos_exp);
            if (entrenadores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
            return new ResponseEntity<>(entrenadores, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
        
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Entrenador> editarEntrenador(@PathVariable Integer id, @RequestBody Entrenador entrenador) {
        try{
            Entrenador editado = entrenadorService.actualizarEntrenador(id, entrenador);
            return new ResponseEntity<>(editado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> actualizarEntrenador(@PathVariable Integer id, @RequestBody Entrenador entrenador) {
        try{
            Entrenador newEntrenador = entrenadorService.actualizarEntrenador(id, entrenador);
            return new ResponseEntity<>(newEntrenador, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntrenador(@PathVariable Integer id) {
        try {
            entrenadorService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       
    }
}

