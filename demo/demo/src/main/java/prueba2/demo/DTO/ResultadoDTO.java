package prueba2.demo.DTO;

import java.time.LocalDate;

import lombok.Data;


@Data
public class ResultadoDTO {

    private Integer id;
    private Integer carreraId;
    private String tipoCarrera;
    private LocalDate fechaCarrera; 
    private String primerLugarNombre;
    private String segundoLugarNombre;
    private String tercerLugarNombre;



}
