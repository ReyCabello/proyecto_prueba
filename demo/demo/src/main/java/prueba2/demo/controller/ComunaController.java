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

import prueba2.demo.DTO.ComunaDTO;
import prueba2.demo.service.ComunaService;

@RestController
@RequestMapping("/api/v1/comunas")
public class ComunaController {

    @Autowired
    private ComunaService comunaService;
    
    @GetMapping
    public ResponseEntity<List<ComunaDTO>> todasLascomunas() {
        List<ComunaDTO> comunas = comunaService.obtenerTodos();
        if (comunas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comunas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComunaDTO> buscarPorId(@PathVariable Integer id) {
        try {
            ComunaDTO comuna = comunaService.buscarPorId(id);
            return new ResponseEntity<>(comuna, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nmombre/{nombre}")
    public ResponseEntity<ComunaDTO> buscarPorNombre(@RequestParam String nombre) {
        try {
            ComunaDTO comuna = comunaService.buscarPorNombre(nombre);
            return new ResponseEntity<>(comuna, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/region/{regionId}")
    public ResponseEntity<List<ComunaDTO>> buscarPorRegion(@PathVariable Integer regionId) {
        List<ComunaDTO> comunas = comunaService.buscarPorRegion(regionId);
        if (comunas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comunas, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarComuna(@PathVariable Integer id) {
        try {
            String resultado = comunaService.eliminar(id);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }  catch (RuntimeException e) {
            return new ResponseEntity<>("Comuna no encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
