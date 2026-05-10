package prueba2.demo.DTO;

import lombok.Data;

@Data
public class RecintoDTO {

    private Integer id;
    private String nombre;
    private String ubicacion;
    private Integer capacidad;
    private Integer cantidadPistas;
    private String comuna;
    private String region;
}
