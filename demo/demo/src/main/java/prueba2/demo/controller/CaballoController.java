package prueba2.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prueba2.demo.DTO.CaballoDTO;
import prueba2.demo.model.Caballo;
import prueba2.demo.service.CaballoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/caballos")
public class CaballoController {

    @Autowired
    private CaballoService caballoService;

    @GetMapping
    public ResponseEntity<List<CaballoDTO>> todosLosCaballos() {
        List<CaballoDTO> caballos = caballoService.obtenerTodos();
        if(caballos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(caballos, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CaballoDTO> buscarPorId(@PathVariable Integer id){
        try {
            CaballoDTO caballito = caballoService.buscarPorId(id);
            return new ResponseEntity<>(caballito, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/raza/{raza}")
    public ResponseEntity<List<CaballoDTO>> buscarPorRaza(@PathVariable String raza) {
        List<CaballoDTO> caballos = caballoService.buscarPorRaza(raza);
        if (caballos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(caballos, HttpStatus.OK);
    }

    @GetMapping("/edad/{edad}")
    public ResponseEntity<List<CaballoDTO>> buscarPorEdad(@PathVariable Integer edad) {
        List<CaballoDTO> caballos = caballoService.buscarPorEdad(edad);
        if (caballos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(caballos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Caballo> agregarCaballo(@RequestBody Caballo caballo) {
        try {
            Caballo guardado = caballoService.guardarCaballo(caballo);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Caballo> editarCaballo(@PathVariable Integer id, @RequestBody Caballo caballo) {
        try {
            Caballo editado = caballoService.guardarCaballo(caballo);
            return new ResponseEntity<>(editado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Caballo> actualizarCaballo(@PathVariable Integer id, @RequestBody Caballo caballo) {
        try{
            Caballo newCaballo = caballoService.actualizarCaballo(id, caballo);
            return new ResponseEntity<>(newCaballo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCaballo(@PathVariable Integer id) {
        String resultado = caballoService.eliminar(id);
        if (resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}
