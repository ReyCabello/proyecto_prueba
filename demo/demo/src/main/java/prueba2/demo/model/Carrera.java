package prueba2.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @Column(nullable = false)
    private String fecha;

    private String tipo_carrera;

    @NotBlank(message = "Este campo es obligatorio")
    @ManyToOne
    @JoinColumn(name="caballo_id", nullable = false)
    private Caballo caballo;


    @NotBlank(message = "Campo obligatorio")
    @ManyToOne
    @JoinColumn(name="jinete_id", nullable = false)
    private Jinete jinete;
}
