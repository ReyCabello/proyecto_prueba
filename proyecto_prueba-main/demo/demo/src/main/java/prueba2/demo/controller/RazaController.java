package prueba2.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import prueba2.demo.DTO.RazaDTO;
import prueba2.demo.model.Raza;
import prueba2.demo.service.RazaService;

@RestController
@RequestMapping("/api/v1/razas")
public class RazaController {

    @Autowired
    private RazaService razaService;

    @GetMapping
    public ResponseEntity<List<RazaDTO>> todasLasRazas() {
       List<RazaDTO> razas = razaService.obtenerTodos();
       if (razas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(razas, HttpStatus.OK);

    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RazaDTO> buscarPorId(@PathVariable Integer id) {
        try {
            RazaDTO raza = razaService.buscarPorId(id);
            return new ResponseEntity<>(raza, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<RazaDTO> buscarPorNombre(@RequestParam String nombre) {
        try {
            RazaDTO raza = razaService.buscarPorNombre(nombre);
            return new ResponseEntity<>(raza, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Raza> actualizarRaza(@PathVariable Integer id, @RequestBody Raza raza) {
        try {
            Raza actualizada = razaService.actualizarRaza(id, raza);
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRaza(@PathVariable Integer id) {
        try {
            String resultado = razaService.eliminar(id);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Raza no encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
