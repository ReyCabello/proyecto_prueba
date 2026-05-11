package prueba2.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
@Entity
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "caballo_primer_lugar_id", nullable = false)
    private Caballo primerLugar;

    @ManyToOne
    @JoinColumn(name = "caballo_segundo_lugar_id", nullable = false)
    private Caballo segundoLugar;

    @ManyToOne
    @JoinColumn(name = "caballo_tercer_lugar_id", nullable = false)
    private Caballo tercerLugar;

    @ManyToOne
    @JoinColumn(name = "carrera_id", nullable = false)
    private Carrera carrera;
}
