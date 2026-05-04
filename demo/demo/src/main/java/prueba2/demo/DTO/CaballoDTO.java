package prueba2.demo.DTO;

import lombok.Data;
import prueba2.demo.model.Raza;

@Data
public class CaballoDTO {
    private Integer id;
    private String nombre;
    private Integer edad;
    private Raza raza;
    private String cuadra;
}
