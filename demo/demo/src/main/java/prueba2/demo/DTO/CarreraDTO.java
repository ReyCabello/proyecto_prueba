package prueba2.demo.DTO;

import java.util.List;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CarreraDTO {

    private Integer id;
    private LocalDate fecha;
    private String tipoCarrera;
    private List<String> nombresCaballos;
    private List<String> nombresJinetes;

}
