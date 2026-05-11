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

import prueba2.demo.DTO.RegionDTO;
import prueba2.demo.service.RegionService;

@RestController
@RequestMapping("/api/v1/regiones")
public class RegionController {

    
    @Autowired
    private RegionService regionService;


    @GetMapping
    public ResponseEntity<List<RegionDTO>> todasLasRegiones(){
        List<RegionDTO> regiones = regionService.obtenerTodos();
        if (regiones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(regiones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> buscarPorId(@PathVariable Integer id) {
        try {
            RegionDTO region = regionService.buscarPorId(id);
            return new ResponseEntity<>(region, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<RegionDTO> buscarPorNombre(@RequestParam String nombre) {
        try {
            RegionDTO region = regionService.buscarPorNombre(nombre);
            return new ResponseEntity<>(region, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRegion(@PathVariable Integer id) {
        try {
            String resultado = regionService.eliminar(id);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Region no encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
