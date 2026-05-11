package prueba2.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prueba2.demo.DTO.JineteDTO;
import prueba2.demo.model.Jinete;
import prueba2.demo.service.JineteService;

@RestController
@RequestMapping("/api/v1/jinetes")

public class JineteController {

    @Autowired
    private JineteService jineteService;
    
    @GetMapping
    public ResponseEntity<List<JineteDTO>> todosLosJinetes() {
        List<JineteDTO> jinete = jineteService.obtenerTodos();
        if(jinete.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(jinete, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JineteDTO> buscarPorId(@PathVariable Integer id) {
        try {
            JineteDTO jinete1 = jineteService.buscarPorId(id);
            return new ResponseEntity<>(jinete1, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/experimentados")
    public ResponseEntity<List<JineteDTO>> buscarExperimentados(){
        List<JineteDTO> jinete = jineteService.buscarExperimentados();
        if (jinete.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(jinete, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Jinete> agregarJinete(@RequestBody Jinete jinete) {
        try {
            Jinete guardado = jineteService.guardarJinete(jinete);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Jinete> editarJinete(@PathVariable Integer id, @RequestBody Jinete jinete) {
        try {
            Jinete editado = jineteService.guardarJinete(jinete);
            return new ResponseEntity<>(editado,HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jinete> actualizarJinete(@PathVariable Integer id, @RequestBody Jinete jinete1) {
        try{
            Jinete newJinete1 = jineteService.actualizarJinete(id, jinete1);
            return new ResponseEntity<>(newJinete1, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarJinete(@PathVariable Integer id) {
        String resultado = jineteService.eliminar(id);
        if(resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/edad/{edad}")
    public ResponseEntity<List<JineteDTO>> buscarPorEdad(@PathVariable Integer edad) {
        List<JineteDTO> jinetes = jineteService.buscarPorEdad(edad);
        if (jinetes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(jinetes, HttpStatus.OK);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<JineteDTO>> buscarPorNombre(@PathVariable String nombre) {
        List<JineteDTO> jinetes = jineteService.buscarPorNombre(nombre);
        if (jinetes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(jinetes, HttpStatus.OK);
    }

    

}
