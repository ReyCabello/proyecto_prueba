package prueba2.demo.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false, name = "fecha_de_carrera")
    private LocalDate fecha;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String tipoCarrera;

    @NotBlank(message = "Este campo es obligatorio")
    @ManyToMany
    @JoinTable(
            name="carrera_caballo", 
            joinColumns = @JoinColumn(name="carrera_id"), 
            inverseJoinColumns = @JoinColumn(name="caballo_id")
        )
    private List<Caballo> caballos;


    @NotBlank(message = "Campo obligatorio")
    @ManyToMany
    @JoinTable(
            name="carrera_jinete",
            joinColumns = @JoinColumn(name="carrera_id"),
            inverseJoinColumns = @JoinColumn(name="jinete_id")
        )
    private List<Jinete> jinetes;
}
