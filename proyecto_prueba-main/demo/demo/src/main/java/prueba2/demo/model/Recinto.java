package prueba2.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "recinto")

public class Recinto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, unique = true)
    private String nombre;

    @NotBlank(message = "La ubicacion es obligatoria")
    @Column(nullable = false)
    private String ubicacion; 

    @NotNull(message = "La capacidad es obligatoria")
    @Column(nullable = false)
    private Integer capacidad;

    @NotNull(message = "La cantidad de pistas es obligatoria")
    @Column(nullable = false, name = "cantidad_pistas")
    private Integer cantidadPistas;

    @ManyToOne
    @JoinColumn(name = "comuna_id", nullable = false)
    private Comuna comuna;

    @OneToMany(mappedBy = "recinto")
    private List<Carrera> carreras;
}
